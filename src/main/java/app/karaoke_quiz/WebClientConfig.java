package app.karaoke_quiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${deezer.rapidapi.key}")
    private String deezerApiKey;

    @Value("${deezer.rapidapi.host}")
    private String deezerApiHost;

    @Bean("deezerRapidApiClient")
    public WebClient deezerRapidApiClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
                .defaultHeader("X-RapidAPI-Key", deezerApiKey)
                .defaultHeader("X-RapidAPI-Host", deezerApiHost)
                .build();
    }

    @Bean("lyricsOvhClient")
    public WebClient lyricsOvhClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.lyrics.ovh/v1")
                .build();
    }
}
