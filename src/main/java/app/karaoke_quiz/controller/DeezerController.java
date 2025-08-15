package app.karaoke_quiz.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/deezer")
public class DeezerController {


    private final WebClient deezerClient;

    public DeezerController(@Qualifier("deezerRapidApiClient") WebClient deezerClient) {
        this.deezerClient = deezerClient;
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchTracks(@RequestParam("q") String query) {
        return deezerClient.get()
                .uri(uri -> uri.path("/search").queryParam("q", query).build())
                .retrieve()
                .toEntity(String.class);
    }
}