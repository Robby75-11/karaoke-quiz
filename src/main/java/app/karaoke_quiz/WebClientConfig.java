package app.karaoke_quiz;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

// WebClientConfig.java
@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("geniusClient")
    public WebClient geniusClient(WebClient.Builder b,
                                  @Value("${genius.token:}") String token) {
        return b.baseUrl("https://api.genius.com")
                .defaultHeader(org.springframework.http.HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    @Bean
    @Qualifier("deezerClient")
    public WebClient deezerClient(WebClient.Builder b) {
        return b.baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
                .build();
    }

}
