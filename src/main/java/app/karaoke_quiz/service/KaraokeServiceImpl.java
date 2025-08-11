// app/karaoke_quiz/service/KaraokeServiceImpl.java
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
import app.karaoke_quiz.repository.QuestionRepository;
import app.karaoke_quiz.repository.QuizRepository;
import app.karaoke_quiz.repository.SongRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KaraokeServiceImpl implements KaraokeService {

    // Dipendenze del repository per i dati locali
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    // ✅ Nuova dipendenza per le chiamate API
    private final WebClient webClient;

    // ✅ Inserisci qui il tuo Client Access Token dell'API di Genius
    private static final String GENIUS_API_TOKEN = "LA_TUA_CHIAVE_API_DI_GENIUS";

    // ✅ La logica per la ricerca rimane invariata, usa i repository locali
    @Override
    public List<SongResponseDto> searchSongs(String query) {
        String q = query == null ? "" : query.toLowerCase();
        return songRepository.findAll().stream()
                .filter(s ->
                        (s.getAuthor() != null && s.getAuthor().toLowerCase().contains(q)) ||
                                (s.getTitle()  != null && s.getTitle().toLowerCase().contains(q))  ||
                                (s.getArtist()!= null && s.getArtist().getName()!=null &&
                                        s.getArtist().getName().toLowerCase().contains(q))
                )
                .map(this::toSongDto)
                .toList();
    }

    // ✅ Metodi per recupero dati da repository locali
    @Override
    public Optional<SongResponseDto> getSongById(Long id) {
        return songRepository.findById(id).map(this::toSongDto);
    }

    @Override
    public List<SongResponseDto> getAllSongs() {
        return songRepository.findAll().stream().map(this::toSongDto).toList();
    }

    // ✅ Metodo che è stato modificato per usare l'API di Genius
    @Override
    public Optional<LyricsDto> getLyricsBySongId(Long songId) {
        try {
            // 1. Cerca il brano nel tuo repository locale
            Optional<Song> optionalSong = songRepository.findById(songId);
            if (optionalSong.isEmpty()) {
                return Optional.empty();
            }
            Song song = optionalSong.get();

            String title = song.getTitle();
            String artistName = song.getArtist() != null ? song.getArtist().getName() : song.getAuthor();

            // 2. Cerca il brano su Genius API
            String geniusSearchUrl = "https://api.genius.com/search?q=" + URLEncoder.encode(title + " " + artistName, StandardCharsets.UTF_8);

            Mono<JsonNode> geniusSearchResponseMono = webClient.get()
                    .uri(geniusSearchUrl)
                    .header("Authorization", "Bearer " + GENIUS_API_TOKEN)
                    .retrieve()
                    .bodyToMono(JsonNode.class);

            JsonNode geniusSearchResponse = geniusSearchResponseMono.block();
            JsonNode hit = geniusSearchResponse.at("/response/hits/0/result");

            if (hit.isMissingNode() || hit.get("path").isMissingNode()) {
                System.err.println("Nessun risultato trovato su Genius per il brano: " + title);
                return Optional.empty();
            }

            String lyricsPath = hit.get("path").asText();
            String fullLyricsUrl = "https://genius.com" + lyricsPath;

            // 3. Simulare il recupero dei testi
            // L'API di Genius non fornisce i testi direttamente, ma solo l'URL della pagina.
            // Per questo esempio, simuliamo i testi, ma dovresti fare lo scraping.
            String simulatedLyrics = "Testi simulati per il brano '" + title + "' di " + artistName + ".\n\n" +
                    "Se vuoi i testi reali, dovrai implementare uno scraping web dell'URL: " + fullLyricsUrl;

            return Optional.of(new LyricsDto(songId, simulatedLyrics, "[]"));

        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei testi da Genius API: " + e.getMessage());
            return Optional.empty();
        }
    }

    // ✅ Metodi per recupero dati da repository locali
    @Override
    public Optional<ArtistDto> getArtistById(Long id) {
        return artistRepository.findById(id).map(this::toArtistDto);
    }

    @Override
    public Optional<Long> getRandomQuizId() {
        List<Quiz> all = quizRepository.findAll();
        if (all.isEmpty()) return Optional.empty();
        Collections.shuffle(all);
        return Optional.of(all.get(0).getId());
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

    private LyricsDto toLyricsDto(Lyrics l) {
        Long songId = (l.getSong() != null) ? l.getSong().getId() : null;
        return new LyricsDto(songId, l.getText(), l.getSyncData());
    }

    private ArtistDto toArtistDto(Artist a) {
        return new ArtistDto(a.getId(), a.getName(), a.getImageUrl(), a.getBio());
    }

    private QuizQuestionPlayDto toQuizQuestionPlayDto(Question q) {
        return new QuizQuestionPlayDto(
                q.getId(), q.getSongId(), q.getText(),
                q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD(), q.getType()
        );
    }
}
