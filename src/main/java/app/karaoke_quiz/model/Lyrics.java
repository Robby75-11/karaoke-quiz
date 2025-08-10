package app.karaoke_quiz.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lyrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @Lob
    private String text;

    @Lob
    private String syncData;// JSON per sincronizzazione (opzionale)
   }