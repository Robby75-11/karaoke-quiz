package app.karaoke_quiz.service;

import app.karaoke_quiz.dto.ArtistDto;
import app.karaoke_quiz.dto.QuizQuestionPlayDto;
import app.karaoke_quiz.dto.LyricsDto;
import app.karaoke_quiz.dto.SongResponseDto;

import java.util.List;
import java.util.Optional;

public interface KaraokeService {
    List<SongResponseDto> searchSongs(String query);
    Optional<SongResponseDto> getSongById(Long id);
    List<SongResponseDto> getAllSongs();

    Optional<LyricsDto> getLyricsBySongId(Long songId);
    Optional<ArtistDto> getArtistById(Long id);

    Optional<Long> getRandomQuizId();
    List<QuizQuestionPlayDto> getQuizQuestions(Long quizId);
}
