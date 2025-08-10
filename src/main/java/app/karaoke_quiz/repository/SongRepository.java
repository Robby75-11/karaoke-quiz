package app.karaoke_quiz.repository;

import app.karaoke_quiz.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAuthorContainingIgnoreCase(String author);
}
