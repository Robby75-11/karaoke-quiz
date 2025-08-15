package app.karaoke_quiz.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/deezer")
public class DeezerController {

    private final WebClient webClient;
    private final String rapidApiKey;
    private final String rapidApiHost;

    public DeezerController(
            @Qualifier("deezerClient") WebClient webClient,
            @Value("${deezer.rapidapi.key:${DEEZER_RAPIDAPI_KEY}}") String rapidApiKey,
            @Value("${deezer.rapidapi.host:${DEEZER_RAPIDAPI_HOST}}") String rapidApiHost
    ) {
        this.webClient = webClient;
        this.rapidApiKey = rapidApiKey;
        this.rapidApiHost = rapidApiHost;
    }

    /**
     * Proxy di ricerca su Deezer per evitare CORS e nascondere la chiave API.
     */
    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchTracks(@RequestParam("q") String query) {
        return webClient.get()
                .uri(uri -> uri.path("/search").queryParam("q", query).build())
                .header("X-RapidAPI-Key", rapidApiKey)
                .header("X-RapidAPI-Host", rapidApiHost)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        resp -> resp.bodyToMono(String.class).map(RuntimeException::new)
                )
                .toEntity(String.class);
    }
}
