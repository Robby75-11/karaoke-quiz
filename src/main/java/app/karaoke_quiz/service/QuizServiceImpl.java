package app.karaoke_quiz.service;
import app.karaoke_quiz.dto.AnswerDto;
import app.karaoke_quiz.dto.QuizQuestionPlayDto;
import app.karaoke_quiz.dto.QuizResultDto;
import app.karaoke_quiz.model.Question;
import app.karaoke_quiz.repository.QuestionRepository;
import app.karaoke_quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final Random random = new Random();

    /**
     * Seleziona un ID di quiz casuale dal database.
     * @return L'ID di un quiz selezionato casualmente.
     */
    @Override
    public Long getRandomQuizId() {
        List<Long> quizIds = quizRepository.findAllIds();
        if (quizIds.isEmpty()) {
            throw new RuntimeException("Nessun quiz trovato nel database.");
        }
        return quizIds.get(random.nextInt(quizIds.size()));
    }

    /**
     * Recupera le domande per un quiz specifico.
     * @param quizId L'ID del quiz.
     * @return Una lista di DTO con le domande del quiz.
     */
    @Override
    public List<QuizQuestionPlayDto> getQuizQuestions(Long quizId) {
        return questionRepository.findByQuizId(quizId).stream()
                .map(this::toDto)
                .toList();
    }

    /**
     * Elabora le risposte dell'utente e calcola il punteggio.
     * @param quizId L'ID del quiz a cui si riferiscono le risposte.
     * @param answers La lista di risposte dell'utente.
     * @return Un DTO con il risultato del quiz.
     */
    @Override
    public QuizResultDto submit(Long quizId, List<AnswerDto> answers) {
        List<Question> questions = questionRepository.findByQuizId(quizId);
        Map<Long, Question> byId = questions.stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));

        int correct = 0;
        for (AnswerDto a : answers) {
            Question q = byId.get(a.getQuestionId());
            if (q == null) continue;

            String expected = toLetter(q.getCorrect()); // "A"/"B"/"C"/"D"
            String given = a.getAnswer() == null ? null : a.getAnswer().trim();

            if (expected != null && expected.equalsIgnoreCase(given)) {
                correct++;
            }
        }
        return new QuizResultDto(questions.size(), correct);
    }

    private QuizQuestionPlayDto toDto(Question q) {
        String correctLetter = toLetter(q.getCorrect()); // "A"/"B"/"C"/"D"
        return new QuizQuestionPlayDto(
                q.getId(),
                q.getSongId(),
                q.getText(),
                q.getOptionA(),
                q.getOptionB(),
                q.getOptionC(),
                q.getOptionD(),
                q.getType(),
                correctLetter   // ✅ IMPORTANTISSIMO: passiamo la lettera, non "optionA"
        );
    }

    private String toLetter(String value) {
        if (value == null) return null;
        switch (value.trim().toLowerCase()) {
            case "optiona": return "A";
            case "optionb": return "B";
            case "optionc": return "C";
            case "optiond": return "D";
            case "a": case "b": case "c": case "d":
                return value.trim().toUpperCase(); // già lettera
            default: return null;
        }
    }
}