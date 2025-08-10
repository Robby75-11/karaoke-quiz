package app.karaoke_quiz.dto;

record SongRequestDto(
        String title,
        Integer year,
        String author,
        String album,
        String audioUrl,
        String coverImageUrl,
        Long artistId
) {}