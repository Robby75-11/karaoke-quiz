package app.karaoke_quiz.controller;

import app.karaoke_quiz.model.Lyrics;
import app.karaoke_quiz.service.LyricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/lyrics")
public class LyricsController {

    private final LyricsService lyricsService;

    public LyricsController(LyricsService lyricsService) {
        this.lyricsService = lyricsService;
    }

    /**
     * Recupera i testi di una canzone dato il suo ID.
     * @param songId l'ID della canzone.
     * @return un ResponseEntity contenente i testi o un errore 404.
     */
    @GetMapping("/{songId}")
    public ResponseEntity<Lyrics> getLyricsBySongId(@PathVariable Long songId) {
        // Chiamiamo il servizio per recuperare i testi.
        Optional<Lyrics> lyrics = lyricsService.getLyricsBySongId(songId);

        // Se i testi sono presenti, restituiamo un 200 OK.
        // Altrimenti, restituiamo un 404 Not Found.
        return lyrics.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
