package app.karaoke_quiz.controller;

import org.springframework.beans.factory.annotation.Qualifier;
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

    // ✅ Iniettiamo il WebClient configurato in WebClientConfig
    public DeezerController(@Qualifier("deezerClient") WebClient deezerClient) {
        this.deezerClient = deezerClient;
    }

    /**
     * Endpoint per la ricerca di brani su Deezer.
     * Agisce come proxy per evitare di esporre la chiave API e risolvere i problemi di CORS.
     *
     * @param query La stringa di ricerca.
     * @return Il JSON della risposta da Deezer.
     */
    @GetMapping("/search")
    public Mono<ResponseEntity<String>> searchTracks(@RequestParam("q") String query) {
        return deezerClient.get()
                .uri(uri -> uri.path("/search").queryParam("q", query).build())
                .retrieve()
                .toEntity(String.class);
    }
}