package app.karaoke_quiz.service;

import app.karaoke_quiz.dto.ArtistDto;
import app.karaoke_quiz.dto.LyricsDto;
import app.karaoke_quiz.dto.QuizQuestionPlayDto;
import app.karaoke_quiz.dto.SongResponseDto;
import app.karaoke_quiz.model.Artist;
import app.karaoke_quiz.model.Lyrics;
import app.karaoke_quiz.model.Question;
import app.karaoke_quiz.model.Quiz;
import app.karaoke_quiz.model.Song;
import app.karaoke_quiz.repository.ArtistRepository;
import app.karaoke_quiz.repository.LyricsRepository;
import app.karaoke_quiz.repository.QuestionRepository;
import app.karaoke_quiz.repository.QuizRepository;
import app.karaoke_quiz.repository.SongRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KaraokeServiceImpl implements KaraokeService {

    @Qualifier("geniusClient")
    private final WebClient geniusClient;

    @Qualifier("deezerClient")
    private final WebClient deezerClient;

    private static final Logger log = LoggerFactory.getLogger(KaraokeServiceImpl.class);

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final LyricsRepository lyricsRepository;

    @Override
    public List<SongResponseDto> searchSongs(String query) {
        String q = query == null ? "" : query.toLowerCase();
        return songRepository.findAll().stream()
                .filter(s ->
                        (s.getAuthor() != null && s.getAuthor().toLowerCase().contains(q)) ||
                                (s.getTitle() != null && s.getTitle().toLowerCase().contains(q)) ||
                                (s.getArtist() != null && s.getArtist().getName() != null &&
                                        s.getArtist().getName().toLowerCase().contains(q))
                )
                .map(this::toSongDto)
                .toList();
    }

    @Override
    public Optional<SongResponseDto> getSongById(Long id) {
        return songRepository.findById(id).map(this::toSongDto);
    }

    @Override
    public List<SongResponseDto> getAllSongs() {
        return songRepository.findAll().stream().map(this::toSongDto).toList();
    }

    @Override
    public Optional<LyricsDto> getLyricsBySongId(Long songId) {
        // Cache locale
        var local = lyricsRepository.findBySongId(songId);
        if (local.isPresent()) {
            var l = local.get();
            return Optional.of(new LyricsDto(songId, l.getText(), l.getSyncData()));
        }

        // Recupera info brano
        var songOpt = songRepository.findById(songId);
        if (songOpt.isEmpty()) return Optional.empty();
        var song = songOpt.get();

        String title = Optional.ofNullable(song.getTitle()).orElse("");
        String artistName = Optional.ofNullable(song.getArtist())
                .map(Artist::getName)
                .orElse(Optional.ofNullable(song.getAuthor()).orElse(""));

        // Chiamata a Genius
        try {
            JsonNode genius = geniusClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search")
                            .queryParam("q", title + " " + artistName)
                            .build())
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            JsonNode hit = genius != null ? genius.at("/response/hits/0/result") : null;
            if (hit == null || hit.isMissingNode() || hit.get("path") == null) {
                return Optional.empty();
            }

            String fullLyricsUrl = "https://genius.com" + hit.get("path").asText();
            String simulated = "Testi simulati per '" + title + "' di " + artistName +
                    ".\nGenius fornisce solo metadati/URL, non il testo completo.\n" +
                    "Pagina: " + fullLyricsUrl;

            // Salva in cache
            Lyrics l = new Lyrics();
            l.setSong(song);
            l.setText(simulated);
            l.setSyncData("[]");
            lyricsRepository.save(l);

            return Optional.of(new LyricsDto(songId, simulated, "[]"));

        } catch (Exception e) {
            log.error("Errore chiamando Genius: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<LyricsDto> getLyricsByTitleArtist(String title, String artistName) {
        try {
            JsonNode genius = geniusClient.get()
                    .uri(u -> u.path("/search").queryParam("q", (title == null ? "" : title) + " " + (artistName == null ? "" : artistName)).build())
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            JsonNode hit = genius != null ? genius.at("/response/hits/0/result") : null;
            if (hit == null || hit.isMissingNode() || hit.get("path") == null) {
                return Optional.empty();
            }

            String fullLyricsUrl = "https://genius.com" + hit.get("path").asText();
            String simulated = "Testi simulati per '" + title + "' di " + artistName +
                    ".\nGenius fornisce solo metadati/URL, non il testo completo.\nPagina: " + fullLyricsUrl;

            return Optional.of(new LyricsDto(null, simulated, "[]"));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ArtistDto> getArtistById(Long id) {
        return artistRepository.findById(id).map(this::toArtistDto);
    }

    @Override
    public Optional<Long> getRandomQuizId() {
        List<Quiz> all = quizRepository.findAll();
        if (all.isEmpty()) return Optional.empty();
        Collections.shuffle(all);
        return Optional.of(all.getFirst().getId());
    }

    @Override
    public List<QuizQuestionPlayDto> getQuizQuestions(Long quizId) {
        return questionRepository.findByQuizId(quizId).stream()
                .map(this::toQuizQuestionPlayDto)
                .toList();
    }

    // ---------- mappers ----------
    private SongResponseDto toSongDto(Song s) {
        Long artistId = s.getArtist() != null ? s.getArtist().getId() : null;
        String artistName = s.getArtist() != null ? s.getArtist().getName() : null;
        return new SongResponseDto(
                s.getId(), s.getTitle(), s.getYear(), s.getAuthor(), s.getAlbum(),
                s.getAudioUrl(), s.getCoverImageUrl(), artistId, artistName
        );
    }

    private ArtistDto toArtistDto(Artist a) {
        return new ArtistDto(a.getId(), a.getName(), a.getImageUrl(), a.getBio());
    }

    private String toLetter(String correctColumn) {
        if (correctColumn == null) return null;
        return switch (correctColumn.trim().toLowerCase()) {
            case "optiona", "a" -> "A";
            case "optionb", "b" -> "B";
            case "optionc", "c" -> "C";
            case "optiond", "d" -> "D";
            default -> null;
        };
    }

    private QuizQuestionPlayDto toQuizQuestionPlayDto(Question q) {
        return new QuizQuestionPlayDto(
                q.getId(),
                q.getSongId(),
                q.getText(),
                q.getOptionA(),
                q.getOptionB(),
                q.getOptionC(),
                q.getOptionD(),
                q.getType(),
                toLetter(q.getCorrect())
        );
    }
}
