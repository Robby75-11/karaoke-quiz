package app.karaoke_quiz;
import app.karaoke_quiz.model.*;
import app.karaoke_quiz.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

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
            Artist vascoRossi = new Artist();
            vascoRossi.setName("Vasco Rossi");
            artistRepository.save(vascoRossi);

            Artist pinoDaniele = new Artist();
            pinoDaniele.setName("Pino Daniele");
            artistRepository.save(pinoDaniele);

            Artist ottoottootto = new Artist(); // 883
            ottoottootto.setName("883");
            artistRepository.save(ottoottootto);

            Artist antonelloVenditti = new Artist();
            antonelloVenditti.setName("Antonello Venditti");
            artistRepository.save(antonelloVenditti);

            Artist annalisa = new Artist();
            annalisa.setName("Annalisa");
            artistRepository.save(annalisa);

            Artist gigiDAlessio = new Artist();
            gigiDAlessio.setName("Gigi D'Alessio");
            artistRepository.save(gigiDAlessio);

            Artist queen = new Artist();
            queen.setName("Queen");
            artistRepository.save(queen);

            // Brani
            Song songVasco1 = new Song();
            songVasco1.setTitle("Ma cosa vuoi che sia una canzone");
            songVasco1.setAuthor("Vasco Rossi");
            songVasco1.setArtist(vascoRossi);
            songRepository.save(songVasco1);

            Song songVasco2 = new Song();
            songVasco2.setTitle("Sally");
            songVasco2.setAuthor("Vasco Rossi");
            songVasco2.setArtist(vascoRossi);
            songRepository.save(songVasco2);

            Song songVasco3 = new Song();
            songVasco3.setTitle("Senza parole");
            songVasco3.setAuthor("Vasco Rossi");
            songVasco3.setArtist(vascoRossi);
            songRepository.save(songVasco3);

            Song songPino1 = new Song();
            songPino1.setTitle("Nero a metà");
            songPino1.setAuthor("Pino Daniele");
            songPino1.setArtist(pinoDaniele);
            songRepository.save(songPino1);

            Song songPino2 = new Song();
            songPino2.setTitle("Quando");
            songPino2.setAuthor("Pino Daniele");
            songPino2.setArtist(pinoDaniele);
            songRepository.save(songPino2);

            Song songPino3 = new Song();
            songPino3.setTitle("Yes I know my way");
            songPino3.setAuthor("Pino Daniele");
            songPino3.setArtist(pinoDaniele);
            songRepository.save(songPino3);

            Song songOttoottootto1 = new Song();
            songOttoottootto1.setTitle("Hanno ucciso l'Uomo Ragno");
            songOttoottootto1.setAuthor("Max Pezzali");
            songOttoottootto1.setArtist(ottoottootto);
            songRepository.save(songOttoottootto1);

            Song songVenditti1 = new Song();
            songVenditti1.setTitle("Notte prima degli esami");
            songVenditti1.setAuthor("Antonello Venditti");
            songVenditti1.setArtist(antonelloVenditti);
            songRepository.save(songVenditti1);

            Song songAnnalisa1 = new Song();
            songAnnalisa1.setTitle("Bellissima");
            songAnnalisa1.setAuthor("Annalisa Scarrone");
            songAnnalisa1.setArtist(annalisa);
            songRepository.save(songAnnalisa1);

            Song songGigi1 = new Song();
            songGigi1.setTitle("Non dirgli mai");
            songGigi1.setAuthor("Gigi D'Alessio");
            songGigi1.setArtist(gigiDAlessio);
            songRepository.save(songGigi1);

            Song songQueen1 = new Song();
            songQueen1.setTitle("Bohemian Rhapsody");
            songQueen1.setAuthor("Freddie Mercury");
            songQueen1.setArtist(queen);
            songRepository.save(songQueen1);


            // QUIZ 1: Vasco Rossi
            Quiz quizVasco = new Quiz();
            quizVasco.setTitle("Quiz su Vasco Rossi");
            quizRepository.save(quizVasco);

            Question qVasco1 = new Question();
            qVasco1.setQuiz(quizVasco);
            qVasco1.setSongId(songVasco1.getId());
            qVasco1.setText("Qual è l'album d'esordio di Vasco Rossi?");
            qVasco1.setOptionA("Ma cosa vuoi che sia una canzone");
            qVasco1.setOptionB("Non siamo mica gli americani!");
            qVasco1.setOptionC("Colpa d'Alfredo");
            qVasco1.setOptionD("Siamo solo noi");
            qVasco1.setCorrect("A");
            qVasco1.setType("ALBUM");
            questionRepository.save(qVasco1);

            Question qVasco2 = new Question();
            qVasco2.setQuiz(quizVasco);
            qVasco2.setSongId(songVasco2.getId());
            qVasco2.setText("Quale canzone di Vasco Rossi contiene il verso 'Devi solo fare attenzione a non cadere giù...'?");
            qVasco2.setOptionA("Albachiara");
            qVasco2.setOptionB("Senza parole");
            qVasco2.setOptionC("Sally");
            qVasco2.setOptionD("Vivere");
            qVasco2.setCorrect("C");
            qVasco2.setType("TESTO");
            questionRepository.save(qVasco2);

            Question qVasco3 = new Question();
            qVasco3.setQuiz(quizVasco);
            qVasco3.setSongId(songVasco3.getId());
            qVasco3.setText("Quale album contiene la canzone 'Senza parole'?");
            qVasco3.setOptionA("Vado al massimo");
            qVasco3.setOptionB("Bollicine");
            qVasco3.setOptionC("Siamo solo noi");
            qVasco3.setOptionD("Vivere o niente");
            qVasco3.setCorrect("D");
            qVasco3.setType("ALBUM");
            questionRepository.save(qVasco3);


            // QUIZ 2: Pino Daniele
            Quiz quizPino = new Quiz();
            quizPino.setTitle("Quiz su Pino Daniele");
            quizRepository.save(quizPino);

            Question qPino1 = new Question();
            qPino1.setQuiz(quizPino);
            qPino1.setSongId(songPino1.getId());
            qPino1.setText("In che anno è stato pubblicato l'album 'Nero a metà' di Pino Daniele?");
            qPino1.setOptionA("1977");
            qPino1.setOptionB("1978");
            qPino1.setOptionC("1980");
            qPino1.setOptionD("1981");
            qPino1.setCorrect("B");
            qPino1.setType("ANNO");
            questionRepository.save(qPino1);

            Question qPino2 = new Question();
            qPino2.setQuiz(quizPino);
            qPino2.setSongId(songPino2.getId());
            qPino2.setText("Quale canzone è stata scritta da Pino Daniele per Massimo Troisi?");
            qPino2.setOptionA("Je so' pazzo");
            qPino2.setOptionB("Quando");
            qPino2.setOptionC("Napule è");
            qPino2.setOptionD("A me me piace 'o blues");
            qPino2.setCorrect("B");
            qPino2.setType("COLONNA_SONORA");
            questionRepository.save(qPino2);

            Question qPino3 = new Question();
            qPino3.setQuiz(quizPino);
            qPino3.setSongId(songPino3.getId());
            qPino3.setText("Quale strumento suona Pino Daniele nella canzone 'Yes I know my way'?");
            qPino3.setOptionA("Chitarra acustica");
            qPino3.setOptionB("Basso elettrico");
            qPino3.setOptionC("Pianoforte");
            qPino3.setOptionD("Sassofono");
            qPino3.setCorrect("B");
            qPino3.setType("STRUMENTO");
            questionRepository.save(qPino3);

            // QUIZ 3: 883
            Quiz quiz883 = new Quiz();
            quiz883.setTitle("Quiz sugli 883");
            quizRepository.save(quiz883);

            Question q883_1 = new Question();
            q883_1.setQuiz(quiz883);
            q883_1.setSongId(songOttoottootto1.getId());
            q883_1.setText("Chi ha ucciso l'Uomo Ragno, secondo la canzone?");
            q883_1.setOptionA("L'assassino");
            q883_1.setOptionB("Nessuno");
            q883_1.setOptionC("Le iene");
            q883_1.setOptionD("Lui stesso");
            q883_1.setCorrect("C");
            q883_1.setType("TESTO");
            questionRepository.save(q883_1);

            // QUIZ 4: Antonello Venditti
            Quiz quizVenditti = new Quiz();
            quizVenditti.setTitle("Quiz su Antonello Venditti");
            quizRepository.save(quizVenditti);

            Question qVenditti1 = new Question();
            qVenditti1.setQuiz(quizVenditti);
            qVenditti1.setSongId(songVenditti1.getId());
            qVenditti1.setText("Quale frase non è presente nella canzone 'Notte prima degli esami'?");
            qVenditti1.setOptionA("Gli amici sono una cosa sola");
            qVenditti1.setOptionB("Notte di lacrime e preghiere");
            qVenditti1.setOptionC("La storia siamo noi");
            qVenditti1.setOptionD("E la vita continua");
            qVenditti1.setCorrect("C");
            qVenditti1.setType("TESTO");
            questionRepository.save(qVenditti1);

            // QUIZ 5: Annalisa
            Quiz quizAnnalisa = new Quiz();
            quizAnnalisa.setTitle("Quiz su Annalisa");
            quizRepository.save(quizAnnalisa);

            Question qAnnalisa1 = new Question();
            qAnnalisa1.setQuiz(quizAnnalisa);
            qAnnalisa1.setSongId(songAnnalisa1.getId());
            qAnnalisa1.setText("Chi è il co-autore del brano 'Bellissima'?");
            qAnnalisa1.setOptionA("Davide Simonetta");
            qAnnalisa1.setOptionB("Dario Faini");
            qAnnalisa1.setOptionC("Alessandro Raina");
            qAnnalisa1.setOptionD("Paolo Antonacci");
            qAnnalisa1.setCorrect("D");
            qAnnalisa1.setType("AUTORE");
            questionRepository.save(qAnnalisa1);

            // QUIZ 6: Gigi D'Alessio
            Quiz quizGigi = new Quiz();
            quizGigi.setTitle("Quiz su Gigi D'Alessio");
            quizRepository.save(quizGigi);

            Question qGigi1 = new Question();
            qGigi1.setQuiz(quizGigi);
            qGigi1.setSongId(songGigi1.getId());
            qGigi1.setText("Quale canzone di Gigi D'Alessio è stata presentata a Sanremo nel 2000?");
            qGigi1.setOptionA("Mon amour");
            qGigi1.setOptionB("Non dirgli mai");
            qGigi1.setOptionC("Un cuore malato");
            qGigi1.setOptionD("Tu che ne sai");
            qGigi1.setCorrect("B");
            qGigi1.setType("SANREMO");
            questionRepository.save(qGigi1);

            // QUIZ 7: Queen
            Quiz quizQueen = new Quiz();
            quizQueen.setTitle("Quiz sui Queen");
            quizRepository.save(quizQueen);

            Question qQueen1 = new Question();
            qQueen1.setQuiz(quizQueen);
            qQueen1.setSongId(songQueen1.getId());
            qQueen1.setText("Chi ha scritto il brano 'Bohemian Rhapsody'?");
            qQueen1.setOptionA("Brian May");
            qQueen1.setOptionB("Freddie Mercury");
            qQueen1.setOptionC("Roger Taylor");
            qQueen1.setOptionD("John Deacon");
            qQueen1.setCorrect("B");
            qQueen1.setType("AUTORE");
            questionRepository.save(qQueen1);
        };
    }
}
