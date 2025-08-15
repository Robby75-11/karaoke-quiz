package app.karaoke_quiz;

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
    @Bean
    public WebClient deezerClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.deezer.com")
                .build();
    }
}