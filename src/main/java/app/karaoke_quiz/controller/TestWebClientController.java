package app.karaoke_quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
public class TestWebClientController {

    private final WebClient deezerRapidApiClient;
    private final WebClient lyricsOvhClient;

    @GetMapping("/test/lyrics")
    public String testLyrics() {
        return lyricsOvhClient.get()
                .uri("/Coldplay/Adventure of a Lifetime")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/test/deezer")
    public String testDeezer() {
        return deezerRapidApiClient.get()
                .uri("/search?q=daft%20punk")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
