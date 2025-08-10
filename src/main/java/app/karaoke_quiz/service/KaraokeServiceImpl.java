// app/karaoke_quiz/service/KaraokeServiceImpl.java
package app.karaoke_quiz.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KaraokeServiceImpl implements KaraokeService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final LyricsRepository lyricsRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public List<SongResponseDto> searchSongsByAuthor(String query) {
        // semplice filtro in memoria su author/title/artistName (va benissimo per iniziare)
        String q = query == null ? "" : query.toLowerCase();
        return songRepository.findAll().stream()
                .filter(s ->
                        (s.getAuthor() != null && s.getAuthor().toLowerCase().contains(q)) ||
                                (s.getTitle()  != null && s.getTitle().toLowerCase().contains(q))  ||
                                (s.getArtist()!= null && s.getArtist().getName()!=null &&
                                        s.getArtist().getName().toLowerCase().contains(q))
                )
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<SongResponseDto> getSongById(Long id) {
        return songRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<SongResponseDto> getAllSongs() {
        return songRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Optional<Lyrics> getLyricsBySongId(Long songId) {
        return lyricsRepository.findBySongId(songId);
    }

    @Override
    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public Optional<Quiz> getRandomQuiz() {
        List<Quiz> all = quizRepository.findAll();
        if (all.isEmpty()) return Optional.empty();
        Collections.shuffle(all);
        return Optional.of(all.get(0));
    }

    @Override
    public List<Question> getQuizQuestions(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    private SongResponseDto toDto(Song s) {
        Long artistId = s.getArtist() != null ? s.getArtist().getId() : null;
        String artistName = s.getArtist() != null ? s.getArtist().getName() : null;
        return new SongResponseDto(
                s.getId(),
                s.getTitle(),
                s.getYear(),
                s.getAuthor(),
                s.getAlbum(),
                s.getAudioUrl(),
                s.getCoverImageUrl(),
                artistId,
                artistName
        );
    }
}

