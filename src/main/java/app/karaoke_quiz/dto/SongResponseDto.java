package app.karaoke_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseDto {
    private Long id;
    private String title;
    private Integer year;
    private String author;
    private String album;
    private String audioUrl;
    private String coverImageUrl;
    private Long artistId;
    private String artistName;
}