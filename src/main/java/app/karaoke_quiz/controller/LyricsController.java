package app.karaoke_quiz.controller;

import app.karaoke_quiz.service.KaraokeService;
import app.karaoke_quiz.service.LyricsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lyrics")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LyricsController {

    private final LyricsService lyricsService;
    private final KaraokeService karaokeService;

    public LyricsController(LyricsService lyricsService, KaraokeService karaokeService) {
        this.lyricsService = lyricsService;
        this.karaokeService = karaokeService;
    }

    /**
     * Recupera i testi partendo dall'ID locale della canzone.
     * Se non presenti in DB (ad es. quando l'ID è quello di Deezer),
     * può usare il fallback passando titolo e artista come query param
     * e interrogando Genius per ottenere l'URL dei testi (testi simulati).
     *
     * Esempio chiamata frontend:
     *   GET /api/lyrics/{deezerOrLocalId}?title=Wave&artist=FAST%20BOY
     */
    @GetMapping("/{songId}")
    public ResponseEntity<?> getLyricsBySongId(
            @PathVariable Long songId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String artist) {

        // 1) Prova DB locale con songId
        var local = lyricsService.getLyricsBySongId(songId);
        if (local.isPresent()) {
            var l = local.get();
            return ResponseEntity.ok(Map.of(
                    "songId", l.getSong() != null ? l.getSong().getId() : songId,
                    "lyrics", l.getText(),
                    "sync", l.getSyncData()
            ));
        }

        // 2) Fallback: se arrivano title/artist (es. id Deezer NON nel DB), cerca via Genius
        if ((title != null && !title.isBlank()) || (artist != null && !artist.isBlank())) {
            return karaokeService.getLyricsByTitleArtist(title, artist)
                    .map(dto -> ResponseEntity.ok(Map.of(
                            "songId", songId, // non è l'ID locale, ma lo rimandiamo giù
                            "lyrics", dto.getText(),
                            "sync", dto.getSyncData()
                    )))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("error", "Lyrics not found")));
        }

        // 3) Nessuna info utile -> 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Lyrics not found"));
    }
}
