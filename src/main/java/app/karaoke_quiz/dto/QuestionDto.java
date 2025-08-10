package app.karaoke_quiz.dto;

public record QuestionDto(
        Long id,
        Long songId,
        String text,
        String optionA,
        String optionB,
        String optionC,
        String optionD,
        String correct,
        String type
) {}
