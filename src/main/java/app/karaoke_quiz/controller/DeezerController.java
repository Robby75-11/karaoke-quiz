package app.karaoke_quiz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/deezer")
public class DeezerController {

    // ✅ Inietta la chiave API dal file application.properties
    @Value("${deezer.rapidapi.key}")
    private String rapidApiKey;

    @Value("${deezer.rapidapi.host}")
    private String rapidApiHost;

    private final WebClient webClient;

    public DeezerController(WebClient.Builder webClientBuilder) {
        // ✅ Aggiorna il WebClient per usare l'host corretto
        this.webClient = webClientBuilder.baseUrl("https://deezerdevs-deezer.p.rapidapi.com").build();
    }

    /**
     * Endpoint per la ricerca di brani su Deezer.
     * Agisce come proxy per evitare di esporre la chiave API e risolvere i problemi di CORS.
     *
     * @param query La stringa di ricerca.
     * @return Il JSON della risposta da Deezer.
     */
    @GetMapping("/search")
    public Mono<String> searchTracks(@RequestParam("q") String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", query)
                        .build())
                .header("X-RapidAPI-Key", rapidApiKey) // ✅ Aggiunge l'header con la chiave API
                .header("X-RapidAPI-Host", rapidApiHost) // ✅ Aggiunge l'header con l'host
                .retrieve()
                .bodyToMono(String.class);
    }
}