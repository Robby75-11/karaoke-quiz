package app.karaoke_quiz.service;

import app.karaoke_quiz.dto.AnswerDto;
import app.karaoke_quiz.dto.QuizQuestionPlayDto;
import app.karaoke_quiz.dto.QuizResultDto;

import java.util.List;


/**
 * Interfaccia per il servizio di gestione dei quiz.
 * Definisce i metodi per interagire con i quiz, come ottenere domande casuali
 * e inviare le risposte.
 */
public interface QuizService {
    Long getRandomQuizId();
    List<QuizQuestionPlayDto> getQuizQuestions(Long quizId);
    QuizResultDto submit(Long quizId, List<AnswerDto> answers);
}
