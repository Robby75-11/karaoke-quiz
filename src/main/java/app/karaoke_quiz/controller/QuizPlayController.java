package app.karaoke_quiz.controller;

import app.karaoke_quiz.dto.AnswerDto;
import app.karaoke_quiz.dto.QuizQuestionPlayDto;
import app.karaoke_quiz.dto.QuizResultDto;
import app.karaoke_quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizPlayController {

    private final QuizService quizService;

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuizQuestionPlayDto>> getQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizQuestions(quizId));
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizResultDto> submit(
            @PathVariable Long quizId,
            @RequestBody List<AnswerDto> answers
    ) {
        return ResponseEntity.ok(quizService.submit(quizId, answers));
    }
}
