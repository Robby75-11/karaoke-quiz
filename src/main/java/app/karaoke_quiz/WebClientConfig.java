package app.karaoke_quiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    /**
     * WebClient per chiamare l'API Genius
     */
    @Bean
    public WebClient geniusClient(WebClient.Builder builder,
                                  @Value("${genius.token}") String token) {
        return builder
                .baseUrl("https://api.genius.com")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    /**
     * WebClient per chiamare l'API Deezer via RapidAPI
     */
    @Bean
    public WebClient deezerClient(WebClient.Builder builder,
                                  @Value("${deezer.rapidapi.key}") String apiKey,
                                  @Value("${deezer.rapidapi.host}") String apiHost) {
        return builder
                .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Key", apiKey)
                .defaultHeader("X-RapidAPI-Host", apiHost)
                .build();
    }
}
