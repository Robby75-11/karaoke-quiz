package app.karaoke_quiz.repository;

import app.karaoke_quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q.id FROM Quiz q")
    List<Long> findAllIds();
}
