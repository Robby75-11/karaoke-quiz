package app.karaoke_quiz.service;

import app.karaoke_quiz.model.Lyrics;
import app.karaoke_quiz.repository.LyricsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LyricsService {

    private final LyricsRepository lyricsRepository;

    public LyricsService(LyricsRepository lyricsRepository) {
        this.lyricsRepository = lyricsRepository;
    }

    /**
     * Trova i testi di una canzone basandosi sull'ID della canzone.
     * @param songId l'ID della canzone.
     * @return un Optional contenente i testi, se trovati.
     */
    public Optional<Lyrics> getLyricsBySongId(Long songId) {
        // Supponendo che il tuo repository abbia un metodo per cercare per songId.
        // Questo metodo deve essere definito in LyricsRepository.
        return lyricsRepository.findBySongId(songId);
    }
}
