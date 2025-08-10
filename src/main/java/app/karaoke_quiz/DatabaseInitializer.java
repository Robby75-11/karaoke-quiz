package app.karaoke_quiz;

import app.karaoke_quiz.model.*;
import app.karaoke_quiz.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Questa classe si trova nel pacchetto principale dell'applicazione.
@Configuration
class DatabaseInitializer {
    private final SongRepository songRepository;
    private final LyricsRepository lyricsRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ArtistRepository artistRepository;

    public DatabaseInitializer(SongRepository songRepository, LyricsRepository lyricsRepository, QuizRepository quizRepository, QuestionRepository questionRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.lyricsRepository = lyricsRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.artistRepository = artistRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            // Artisti
            Artist johnLennon = new Artist();
            johnLennon.setName("John Lennon");
            artistRepository.save(johnLennon);

            Artist queen = new Artist();
            queen.setName("Queen");
            artistRepository.save(queen);

            // Brani
            Song song1 = new Song();
            song1.setTitle("Imagine");
            song1.setAuthor("John Lennon");
            song1.setArtist(johnLennon);
            song1.setAudioUrl("http://example.com/imagine.mp3");
            song1.setCoverImageUrl("http://example.com/imagine_cover.jpg");
            songRepository.save(song1);

            Song song2 = new Song();
            song2.setTitle("Bohemian Rhapsody");
            song2.setAuthor("Queen");
            song2.setArtist(queen);
            song2.setAudioUrl("http://example.com/bohemian.mp3");
            song2.setCoverImageUrl("http://example.com/bohemian_cover.jpg");
            songRepository.save(song2);

            // Testi con dati di sincronizzazione di esempio (formato JSON)
            Lyrics lyrics1 = new Lyrics();
            lyrics1.setSong(song1);
            lyrics1.setText("Imagine there's no heaven...\nIt's easy if you try...");
            lyrics1.setSyncData("[{\"time\": 2000, \"text\": \"Imagine there's no heaven...\"}, {\"time\": 5000, \"text\": \"It's easy if you try...\"}]");
            lyricsRepository.save(lyrics1);

            Lyrics lyrics2 = new Lyrics();
            lyrics2.setSong(song2);
            lyrics2.setText("Is this the real life? Is this just fantasy?...\nCaught in a landslide, no escape from reality...");
            lyrics2.setSyncData("[{\"time\": 1000, \"text\": \"Is this the real life?\"}, {\"time\": 3000, \"text\": \"Is this just fantasy?...\"}, {\"time\": 6000, \"text\": \"Caught in a landslide, no escape from reality...\"}]");
            lyricsRepository.save(lyrics2);

            // Quiz e domande
            // Crea un nuovo oggetto Quiz e lo salva nel database
            Quiz quiz = new Quiz();
            quiz.setTitle("Quiz Rock Leggendario");
            quizRepository.save(quiz);

            // Crea la prima domanda e la associa al quiz
            Question question1 = new Question();
            question1.setQuiz(quiz); // Collega la domanda al quiz appena creato
            question1.setSongId(song2.getId()); // Associa la domanda a una canzone specifica
            question1.setText("Chi ha scritto 'Bohemian Rhapsody'?");
            question1.setOptionA("Freddie Mercury");
            question1.setOptionB("John Deacon");
            question1.setOptionC("Brian May");
            question1.setOptionD("Roger Taylor");
            question1.setCorrect("A");
            question1.setType("AUTORE");
            questionRepository.save(question1); // Salva la domanda nel database
        };
    }
}
