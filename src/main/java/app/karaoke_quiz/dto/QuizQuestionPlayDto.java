package app.karaoke_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionPlayDto {
    private Long id;
    private Long songId;
    private String text;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String type;
    private String correct;
}