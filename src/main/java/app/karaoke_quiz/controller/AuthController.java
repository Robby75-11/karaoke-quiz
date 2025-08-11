package app.karaoke_quiz.controller;

import app.karaoke_quiz.dto.LoginDto;
import app.karaoke_quiz.dto.UserDto;
import app.karaoke_quiz.exception.ValidationException;
import app.karaoke_quiz.model.User;
import app.karaoke_quiz.service.AuthService;
import app.karaoke_quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody @Validated UserDto userDto, BindingResult br) {
        if (br.hasErrors()) {
            throw new ValidationException(br.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage()).reduce("", (a,b) -> a+b));
        }
        return userService.saveUser(userDto); // deve esistere questo metodo
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDto loginDto, BindingResult br) {
        if (br.hasErrors()) {
            throw new ValidationException(br.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage()).reduce("", (a,b) -> a+b));
        }
        return authService.login(loginDto);
    }
}