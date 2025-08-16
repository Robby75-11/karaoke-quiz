package app.karaoke_quiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient deezerRapidApiClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.deezer.com") // URL base Deezer
                .build();
    }

    @Bean
    public WebClient lyricsOvhClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.lyrics.ovh/v1") // URL base Lyrics.ovh
                .build();
    }
}