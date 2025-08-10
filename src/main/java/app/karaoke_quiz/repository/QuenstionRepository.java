package app.karaoke_quiz.repository;

import app.karaoke_quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuenstionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySongId(Long songId);
}
