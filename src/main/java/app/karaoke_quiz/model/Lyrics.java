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

    @Column(length = 5000)
    private String text;

    @Column(columnDefinition = "TEXT")
    private String syncData;// JSON per sincronizzazione (opzionale)
   }