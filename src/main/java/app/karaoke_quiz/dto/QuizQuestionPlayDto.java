package app.karaoke_quiz.dto;

public record QuizQuestionPlayDto(
        Long id,
        Long songId,
        String text,
        String optionA,
        String optionB,
        String optionC,
        String optionD,
        String type
) {}