package app.karaoke_quiz.service;

import app.karaoke_quiz.model.User;
import app.karaoke_quiz.dto.LoginDto;
import app.karaoke_quiz.exception.NotFoundException;
import app.karaoke_quiz.exception.UnAuthorizedException;
import app.karaoke_quiz.repository.UserRepository;
import app.karaoke_quiz.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String login(LoginDto loginDto)  {
       User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new NotFoundException("Username non trovato"));

        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return jwtTool.createToken(user);
        } else {
            throw new UnAuthorizedException("Credenziali non valide");
        }
    }


}
