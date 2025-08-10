package app.karaoke_quiz.repository;

import app.karaoke_quiz.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

}
