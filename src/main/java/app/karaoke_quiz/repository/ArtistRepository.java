package app.karaoke_quiz.repository;

import app.karaoke_quiz.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
