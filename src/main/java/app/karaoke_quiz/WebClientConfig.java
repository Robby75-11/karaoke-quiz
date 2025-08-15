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
    @Bean(name = "lyricsOvhClient")
    public WebClient lyricsOvhClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.lyrics.ovh/v1")
                .build();
    }

    /**
     * WebClient per Deezer ufficiale
     */
    @Bean(name = "deezerRapidApiClient")
    public WebClient deezerRapidApiClient(WebClient.Builder builder) {
        return builder.baseUrl("https://deezerdevs-deezer.p.rapidapi.com").build();

    }
}