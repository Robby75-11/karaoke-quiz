package app.karaoke_quiz.dto;

public record SongResponseDto(
        Long id,
        String title,
        Integer year,
        String author,
        String album,
        String audioUrl,
        String coverImageUrl,
        Long artistId,
        String artistName
) {}