package app.karaoke_quiz.model;

import app.karaoke_quiz.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
