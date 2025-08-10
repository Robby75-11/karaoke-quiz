package app.karaoke_quiz.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
   private String author;
   private Integer year;
   private String album;
   private String audioUrl;
   private String coverImageUrl;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
