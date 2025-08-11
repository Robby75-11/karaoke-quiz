package app.karaoke_quiz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Classe di configurazione per definire i bean dell'applicazione.
 * In questo caso, definiamo un bean per WebClient, necessario per le chiamate API REST.
 */
@Configuration
public class WebClientConfig {

    /**
     * Crea e configura un bean di WebClient.
     * WebClient è una classe di Spring WebFlux, utilizzata per effettuare chiamate HTTP
     * in modo reattivo e non bloccante.
     * @param builder un builder pre-configurato fornito da Spring.
     * @return un'istanza di WebClient.
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
