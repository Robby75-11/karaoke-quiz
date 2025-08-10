package app.karaoke_quiz.service;

import app.karaoke_quiz.dto.UserDto;
import app.karaoke_quiz.enumeration.Role;
import app.karaoke_quiz.model.User;
import app.karaoke_quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo per registrare un nuovo utente.
    public void registerNewUser(String username, String password, String role) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Codifica la password prima di salvarla
        newUser.setRole(Role.valueOf(role.toUpperCase()).name()); // Assegna il ruolo

        userRepository.save(newUser);
    }

    // 👉 Metodo che il tuo controller si aspetta
    public User saveUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User u = new User();
        u.setUsername(userDto.getUsername());
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        u.setRole(userDto.getRole() != null ? userDto.getRole().name() : Role.USER.name());
        return userRepository.save(u);
    }
}

