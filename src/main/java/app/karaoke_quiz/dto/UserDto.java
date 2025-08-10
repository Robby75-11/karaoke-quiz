package app.karaoke_quiz.dto;

import app.karaoke_quiz.enumeration.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Role role;
}