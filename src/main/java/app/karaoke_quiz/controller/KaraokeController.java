package app.karaoke_quiz.controller;

import app.karaoke_quiz.dto.ArtistDto;
import app.karaoke_quiz.dto.QuizQuestionPlayDto;
import app.karaoke_quiz.dto.LyricsDto;
import app.karaoke_quiz.dto.SongResponseDto;
import app.karaoke_quiz.service.KaraokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KaraokeController {

    private final KaraokeService karaokeService;

    // /api/songs/search?query=...
    @GetMapping("/songs/search")
    public ResponseEntity<List<SongResponseDto>> searchSongs(@RequestParam String query) {
        return ResponseEntity.ok(karaokeService.searchSongs(query));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongResponseDto> getSongById(@PathVariable Long id) {
        return karaokeService.getSongById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongResponseDto>> getAllSongs() {
        return ResponseEntity.ok(karaokeService.getAllSongs());
    }

    @GetMapping("/lyrics/{songId}")
    public ResponseEntity<LyricsDto> getLyricsBySongId(@PathVariable Long songId) {
        return karaokeService.getLyricsBySongId(songId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long id) {
        return karaokeService.getArtistById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Quiz: prendi un quiz casuale (id + titolo) e poi le domande
    @GetMapping("/quiz/random")
    public ResponseEntity<Long> getRandomQuizId() {
        return karaokeService.getRandomQuizId()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/quiz/{quizId}/questions")
    public ResponseEntity<List<QuizQuestionPlayDto>> getQuizQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(karaokeService.getQuizQuestions(quizId));
    }
}
