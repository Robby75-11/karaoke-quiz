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

    public DatabaseInitializer(
            SongRepository songRepository,
            LyricsRepository lyricsRepository,
            QuizRepository quizRepository,
            QuestionRepository questionRepository,
            ArtistRepository artistRepository
    ) {
        this.songRepository = songRepository;
        this.lyricsRepository = lyricsRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.artistRepository = artistRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (questionRepository.count() > 0 || quizRepository.count() > 0 || songRepository.count() > 0) {
                return;
            }
            // ====== Artisti esistenti ======
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

            // ====== Brani esistenti ======
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

            Song songOttoottootto2 = new Song();
            songOttoottootto2.setTitle("Non me la menare");
            songOttoottootto2.setAuthor("Max Pezzali");
            songOttoottootto2.setArtist(ottoottootto);
            songRepository.save(songOttoottootto2);

            Song songOttoottootto3 = new Song();
            songOttoottootto3.setTitle("Come Mai");
            songOttoottootto3.setAuthor("Max Pezzali");
            songOttoottootto3.setArtist(ottoottootto);
            songRepository.save(songOttoottootto3);

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

            Song songQueen2 = new Song();
            songQueen2.setTitle("Queen");
            songQueen2.setAuthor("Freddie Mercury");
            songQueen2.setArtist(queen);
            songRepository.save(songQueen2);

            // ====== QUIZ esistenti ======
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
            qVasco2.setText("Quale canzone di Vasco Rossi è più 'recente'?");
            qVasco2.setOptionA("Albachiara");
            qVasco2.setOptionB("Senza parole");
            qVasco2.setOptionC("Sally");
            qVasco2.setOptionD("Vivere");
            qVasco2.setCorrect("B");
            qVasco2.setType("TESTO");
            questionRepository.save(qVasco2);

            Question qVasco3 = new Question();
            qVasco3.setQuiz(quizVasco);
            qVasco3.setSongId(songVasco3.getId());
            qVasco3.setText("Quale album contiene la canzone 'Senza parole'?");
            qVasco3.setOptionA("Vado al massimo");
            qVasco3.setOptionB("Senza parole");
            qVasco3.setOptionC("Siamo solo noi");
            qVasco3.setOptionD("Vivere o niente");
            qVasco3.setCorrect("D");
            qVasco3.setType("ALBUM");
            questionRepository.save(qVasco3);

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
            qPino3.setOptionB("Chitarra elettrica");
            qPino3.setOptionC("Pianoforte");
            qPino3.setOptionD("Sassofono");
            qPino3.setCorrect("B");
            qPino3.setType("STRUMENTO");
            questionRepository.save(qPino3);

            Quiz quiz883 = new Quiz();
            quiz883.setTitle("Quiz sugli 883");
            quizRepository.save(quiz883);

            Question q883_1 = new Question();
            q883_1.setQuiz(quiz883);
            q883_1.setSongId(songOttoottootto1.getId());
            q883_1.setText("Di che album fa parte Come mai?");
            q883_1.setOptionA("Hanno ucciso l'Uomo Ragno");
            q883_1.setOptionB("Nessuno di questi");
            q883_1.setOptionC("Nord Sud Ovest Est");
            q883_1.setOptionD("Grazie Mille");
            q883_1.setCorrect("C");
            q883_1.setType("TESTO");
            questionRepository.save(q883_1);

            Question q883_2 = new Question();
            q883_2.setQuiz(quiz883);
            q883_2.setSongId(songOttoottootto2.getId());
            q883_2.setText("Qual è il titolo del singolo di debutto degli 883?");
            q883_2.setOptionA("Hanno ucciso l'Uomo Ragno");
            q883_2.setOptionB("Non me la menare");
            q883_2.setOptionC("Nord Sud Ovest Est");
            q883_2.setOptionD("Grazie Mille");
            q883_2.setCorrect("B");
            q883_2.setType("TESTO");
            questionRepository.save(q883_2);

            Question q883_3 = new Question();
            q883_3.setQuiz(quiz883);
            q883_3.setSongId(songOttoottootto3.getId());
            q883_3.setText("Qual è il nome del singolo degli 883 che parla di una storia d'amore adolescenziale");
            q883_3.setOptionA("Hanno ucciso l'Uomo Ragno");
            q883_3.setOptionB("Non me la menare");
            q883_3.setOptionC("Nord Sud Ovest Est");
            q883_3.setOptionD("Come Mai");
            q883_3.setCorrect("D");
            q883_3.setType("TESTO");
            questionRepository.save(q883_3);

            Quiz quizVenditti = new Quiz();
            quizVenditti.setTitle("Quiz su Antonello Venditti");
            quizRepository.save(quizVenditti);

            Question qVenditti1 = new Question();
            qVenditti1.setQuiz(quizVenditti);
            qVenditti1.setSongId(songVenditti1.getId());
            qVenditti1.setText("Chi Canta 'Notte prima degli esami'?");
            qVenditti1.setOptionA("Lucio Dalla");
            qVenditti1.setOptionB("Albano");
            qVenditti1.setOptionC("Antonello Venditti");
            qVenditti1.setOptionD("Renato Zero");
            qVenditti1.setCorrect("C");
            qVenditti1.setType("TESTO");
            questionRepository.save(qVenditti1);

            Quiz quizAnnalisa = new Quiz();
            quizAnnalisa.setTitle("Quiz su Annalisa");
            quizRepository.save(quizAnnalisa);

            Question qAnnalisa1 = new Question();
            qAnnalisa1.setQuiz(quizAnnalisa);
            qAnnalisa1.setSongId(songAnnalisa1.getId());
            qAnnalisa1.setText("Chi canta il brano 'Bellissima'?");
            qAnnalisa1.setOptionA("Giorgia");
            qAnnalisa1.setOptionB("Annalisa");
            qAnnalisa1.setOptionC("Eros Ramazzotti");
            qAnnalisa1.setOptionD("Biagio Antonacci");
            qAnnalisa1.setCorrect("B");
            qAnnalisa1.setType("AUTORE");
            questionRepository.save(qAnnalisa1);

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

            Question qQueen2 = new Question();
            qQueen2.setQuiz(quizQueen);
            qQueen2.setSongId(songQueen1.getId());
            qQueen2.setText("Qual è il nome del famoso gruppo rock britannico che ha avuto Freddie Mercury come frontman?");
            qQueen2.setOptionA("Queen");
            qQueen2.setOptionB("U2");
            qQueen2.setOptionC("Duran Duran");
            qQueen2.setOptionD("Gans Roses");
            qQueen2.setCorrect("A");
            qQueen2.setType("AUTORE");
            questionRepository.save(qQueen2);

            // =============================================================
            // ====== NUOVI ARTISTI + BRANI + 3 QUIZ CIASCUNO ==========
            // The Kolors
            Artist theKolors = new Artist();
            theKolors.setName("The Kolors");
            artistRepository.save(theKolors);

            Song songKolors1 = new Song();
            songKolors1.setTitle("Italodisco");
            songKolors1.setAuthor("The Kolors");
            songKolors1.setArtist(theKolors);
            songRepository.save(songKolors1);

            Song songKolors2 = new Song();
            songKolors2.setTitle("Everytime");
            songKolors2.setAuthor("The Kolors");
            songKolors2.setArtist(theKolors);
            songRepository.save(songKolors2);

            Quiz quizKolors = new Quiz();
            quizKolors.setTitle("Quiz sui The Kolors");
            quizRepository.save(quizKolors);

            Question qKolors1 = new Question();
            qKolors1.setQuiz(quizKolors);
            qKolors1.setSongId(songKolors1.getId());
            qKolors1.setText("Chi canta 'Italodisco'?");
            qKolors1.setOptionA("The Kolors");
            qKolors1.setOptionB("Maneskin");
            qKolors1.setOptionC("Boomdabash");
            qKolors1.setOptionD("The Giornalisti");
            qKolors1.setCorrect("A");
            qKolors1.setType("AUTORE");
            questionRepository.save(qKolors1);

            Question qKolors2 = new Question();
            qKolors2.setQuiz(quizKolors);
            qKolors2.setSongId(songKolors1.getId());
            qKolors2.setText("In che anno è uscita 'Italodisco'?");
            qKolors2.setOptionA("2021");
            qKolors2.setOptionB("2022");
            qKolors2.setOptionC("2023");
            qKolors2.setOptionD("2024");
            qKolors2.setCorrect("C");
            qKolors2.setType("ANNO");
            questionRepository.save(qKolors2);

            Question qKolors3 = new Question();
            qKolors3.setQuiz(quizKolors);
            qKolors3.setSongId(songKolors2.getId());
            qKolors3.setText("Quale di questi è un brano dei The Kolors?");
            qKolors3.setOptionA("Everytime");
            qKolors3.setOptionB("Zitti e buoni");
            qKolors3.setOptionC("Ricordami");
            qKolors3.setOptionD("Pamplona");
            qKolors3.setCorrect("A");
            qKolors3.setType("TESTO");
            questionRepository.save(qKolors3);

            // Fedez
            Artist fedez = new Artist();
            fedez.setName("Fedez");
            artistRepository.save(fedez);

            Song songFedez1 = new Song();
            songFedez1.setTitle("Magnifico");
            songFedez1.setAuthor("Fedez");
            songFedez1.setArtist(fedez);
            songRepository.save(songFedez1);

            Song songFedez2 = new Song();
            songFedez2.setTitle("Prima di ogni cosa");
            songFedez2.setAuthor("Fedez");
            songFedez2.setArtist(fedez);
            songRepository.save(songFedez2);

            Quiz quizFedez = new Quiz();
            quizFedez.setTitle("Quiz su Fedez");
            quizRepository.save(quizFedez);

            Question qFedez1 = new Question();
            qFedez1.setQuiz(quizFedez);
            qFedez1.setSongId(songFedez1.getId());
            qFedez1.setText("Chi canta 'Magnifico'?");
            qFedez1.setOptionA("Sfera Ebbasta");
            qFedez1.setOptionB("Fedez");
            qFedez1.setOptionC("J-Ax");
            qFedez1.setOptionD("Tiziano Ferro");
            qFedez1.setCorrect("B");
            qFedez1.setType("AUTORE");
            questionRepository.save(qFedez1);

            Question qFedez2 = new Question();
            qFedez2.setQuiz(quizFedez);
            qFedez2.setSongId(songFedez1.getId());
            qFedez2.setText("Con chi è in featuring 'Magnifico'?");
            qFedez2.setOptionA("Francesca Michielin");
            qFedez2.setOptionB("Elisa");
            qFedez2.setOptionC("Noemi");
            qFedez2.setOptionD("Emma");
            qFedez2.setCorrect("A");
            qFedez2.setType("AUTORE");
            questionRepository.save(qFedez2);

            Question qFedez3 = new Question();
            qFedez3.setQuiz(quizFedez);
            qFedez3.setSongId(songFedez2.getId());
            qFedez3.setText("In che anno è uscita 'Prima di ogni cosa'?");
            qFedez3.setOptionA("2016");
            qFedez3.setOptionB("2017");
            qFedez3.setOptionC("2018");
            qFedez3.setOptionD("2019");
            qFedez3.setCorrect("C");
            qFedez3.setType("ANNO");
            questionRepository.save(qFedez3);

            // Madonna
            Artist madonna = new Artist();
            madonna.setName("Madonna");
            artistRepository.save(madonna);

            Song songMadonna1 = new Song();
            songMadonna1.setTitle("Like a Prayer");
            songMadonna1.setAuthor("Madonna");
            songMadonna1.setArtist(madonna);
            songRepository.save(songMadonna1);

            Song songMadonna2 = new Song();
            songMadonna2.setTitle("Vogue");
            songMadonna2.setAuthor("Madonna");
            songMadonna2.setArtist(madonna);
            songRepository.save(songMadonna2);

            Quiz quizMadonna = new Quiz();
            quizMadonna.setTitle("Quiz su Madonna");
            quizRepository.save(quizMadonna);

            Question qMadonna1 = new Question();
            qMadonna1.setQuiz(quizMadonna);
            qMadonna1.setSongId(songMadonna1.getId());
            qMadonna1.setText("Chi canta 'Like a Prayer'?");
            qMadonna1.setOptionA("Madonna");
            qMadonna1.setOptionB("Cher");
            qMadonna1.setOptionC("Kylie Minogue");
            qMadonna1.setOptionD("Céline Dion");
            qMadonna1.setCorrect("A");
            qMadonna1.setType("AUTORE");
            questionRepository.save(qMadonna1);

            Question qMadonna2 = new Question();
            qMadonna2.setQuiz(quizMadonna);
            qMadonna2.setSongId(songMadonna2.getId());
            qMadonna2.setText("Quale di questi è un brano di Madonna?");
            qMadonna2.setOptionA("Vogue");
            qMadonna2.setOptionB("Poker Face");
            qMadonna2.setOptionC("Toxic");
            qMadonna2.setOptionD("Believe");
            qMadonna2.setCorrect("A");
            qMadonna2.setType("TESTO");
            questionRepository.save(qMadonna2);

            Question qMadonna3 = new Question();
            qMadonna3.setQuiz(quizMadonna);
            qMadonna3.setSongId(songMadonna1.getId());
            qMadonna3.setText("In che anno è uscita 'Like a Prayer'?");
            qMadonna3.setOptionA("1987");
            qMadonna3.setOptionB("1988");
            qMadonna3.setOptionC("1989");
            qMadonna3.setOptionD("1990");
            qMadonna3.setCorrect("C");
            qMadonna3.setType("ANNO");
            questionRepository.save(qMadonna3);

            // Jovanotti
            Artist jovanotti = new Artist();
            jovanotti.setName("Jovanotti");
            artistRepository.save(jovanotti);

            Song songJova1 = new Song();
            songJova1.setTitle("Serenata Rap");
            songJova1.setAuthor("Jovanotti");
            songJova1.setArtist(jovanotti);
            songRepository.save(songJova1);

            Song songJova2 = new Song();
            songJova2.setTitle("A te");
            songJova2.setAuthor("Jovanotti");
            songJova2.setArtist(jovanotti);
            songRepository.save(songJova2);

            Quiz quizJova = new Quiz();
            quizJova.setTitle("Quiz su Jovanotti");
            quizRepository.save(quizJova);

            Question qJova1 = new Question();
            qJova1.setQuiz(quizJova);
            qJova1.setSongId(songJova1.getId());
            qJova1.setText("Chi canta 'Serenata Rap'?");
            qJova1.setOptionA("Fabri Fibra");
            qJova1.setOptionB("Jovanotti");
            qJova1.setOptionC("Articolo 31");
            qJova1.setOptionD("Neffa");
            qJova1.setCorrect("B");
            qJova1.setType("AUTORE");
            questionRepository.save(qJova1);

            Question qJova2 = new Question();
            qJova2.setQuiz(quizJova);
            qJova2.setSongId(songJova2.getId());
            qJova2.setText("Quale di questi è un brano di Jovanotti?");
            qJova2.setOptionA("A te");
            qJova2.setOptionB("Sbalzi d'amore");
            qJova2.setOptionC("Ti scatterò una foto");
            qJova2.setOptionD("Una su un milione");
            qJova2.setCorrect("A");
            qJova2.setType("TESTO");
            questionRepository.save(qJova2);

            Question qJova3 = new Question();
            qJova3.setQuiz(quizJova);
            qJova3.setSongId(songJova2.getId());
            qJova3.setText("In che anno è uscita 'A te'?");
            qJova3.setOptionA("2006");
            qJova3.setOptionB("2007");
            qJova3.setOptionC("2008");
            qJova3.setOptionD("2009");
            qJova3.setCorrect("C");
            qJova3.setType("ANNO");
            questionRepository.save(qJova3);

            // Los Locos
            Artist losLocos = new Artist();
            losLocos.setName("Los Locos");
            artistRepository.save(losLocos);

            Song songLocos1 = new Song();
            songLocos1.setTitle("El Meneaito");
            songLocos1.setAuthor("Los Locos");
            songLocos1.setArtist(losLocos);
            songRepository.save(songLocos1);

            Song songLocos2 = new Song();
            songLocos2.setTitle("Macarena (cover)");
            songLocos2.setAuthor("Los Locos");
            songLocos2.setArtist(losLocos);
            songRepository.save(songLocos2);

            Quiz quizLocos = new Quiz();
            quizLocos.setTitle("Quiz sui Los Locos");
            quizRepository.save(quizLocos);

            Question qLocos1 = new Question();
            qLocos1.setQuiz(quizLocos);
            qLocos1.setSongId(songLocos1.getId());
            qLocos1.setText("Chi ha portato al successo in Italia 'El Meneaito'?");
            qLocos1.setOptionA("Los Locos");
            qLocos1.setOptionB("Los del Río");
            qLocos1.setOptionC("Gipsy Kings");
            qLocos1.setOptionD("Righeira");
            qLocos1.setCorrect("A");
            qLocos1.setType("AUTORE");
            questionRepository.save(qLocos1);

            Question qLocos2 = new Question();
            qLocos2.setQuiz(quizLocos);
            qLocos2.setSongId(songLocos2.getId());
            qLocos2.setText("Quale di questi è un brano/cover dei Los Locos?");
            qLocos2.setOptionA("Macarena (cover)");
            qLocos2.setOptionB("Lambada");
            qLocos2.setOptionC("Aserejé");
            qLocos2.setOptionD("Dragostea Din Tei");
            qLocos2.setCorrect("A");
            qLocos2.setType("TESTO");
            questionRepository.save(qLocos2);

            Question qLocos3 = new Question();
            qLocos3.setQuiz(quizLocos);
            qLocos3.setSongId(songLocos2.getId());
            qLocos3.setText("In che anno uscì la loro versione di 'Macarena' in Italia?");
            qLocos3.setOptionA("1994");
            qLocos3.setOptionB("1995");
            qLocos3.setOptionC("1996");
            qLocos3.setOptionD("1997");
            qLocos3.setCorrect("C");
            qLocos3.setType("ANNO");
            questionRepository.save(qLocos3);

            // Gianni Morandi
            Artist gianniMorandi = new Artist();
            gianniMorandi.setName("Gianni Morandi");
            artistRepository.save(gianniMorandi);

            Song songMorandi1 = new Song();
            songMorandi1.setTitle("Fatti mandare dalla mamma");
            songMorandi1.setAuthor("Gianni Morandi");
            songMorandi1.setArtist(gianniMorandi);
            songRepository.save(songMorandi1);

            Song songMorandi2 = new Song();
            songMorandi2.setTitle("C'era un ragazzo che come me amava i Beatles e i Rolling Stones");
            songMorandi2.setAuthor("Gianni Morandi");
            songMorandi2.setArtist(gianniMorandi);
            songRepository.save(songMorandi2);

            Quiz quizMorandi = new Quiz();
            quizMorandi.setTitle("Quiz su Gianni Morandi");
            quizRepository.save(quizMorandi);

            Question qMorandi1 = new Question();
            qMorandi1.setQuiz(quizMorandi);
            qMorandi1.setSongId(songMorandi1.getId());
            qMorandi1.setText("Chi canta 'Fatti mandare dalla mamma'?");
            qMorandi1.setOptionA("Adriano Celentano");
            qMorandi1.setOptionB("Gianni Morandi");
            qMorandi1.setOptionC("Bobby Solo");
            qMorandi1.setOptionD("Gianni Togni");
            qMorandi1.setCorrect("B");
            qMorandi1.setType("AUTORE");
            questionRepository.save(qMorandi1);

            Question qMorandi2 = new Question();
            qMorandi2.setQuiz(quizMorandi);
            qMorandi2.setSongId(songMorandi2.getId());
            qMorandi2.setText("Quale di questi è un brano di Gianni Morandi?");
            qMorandi2.setOptionA("C'era un ragazzo che come me amava i Beatles e i Rolling Stones");
            qMorandi2.setOptionB("Vent'anni");
            qMorandi2.setOptionC("4/3/1943");
            qMorandi2.setOptionD("Sally");
            qMorandi2.setCorrect("A");
            qMorandi2.setType("TESTO");
            questionRepository.save(qMorandi2);

            Question qMorandi3 = new Question();
            qMorandi3.setQuiz(quizMorandi);
            qMorandi3.setSongId(songMorandi1.getId());
            qMorandi3.setText("In che anno è uscita 'Fatti mandare dalla mamma'?");
            qMorandi3.setOptionA("1960");
            qMorandi3.setOptionB("1962");
            qMorandi3.setOptionC("1964");
            qMorandi3.setOptionD("1966");
            qMorandi3.setCorrect("B");
            qMorandi3.setType("ANNO");
            questionRepository.save(qMorandi3);

            // Al Bano
            Artist alBano = new Artist();
            alBano.setName("Al Bano");
            artistRepository.save(alBano);

            Song songAlbano1 = new Song();
            songAlbano1.setTitle("Felicità");
            songAlbano1.setAuthor("Al Bano & Romina Power");
            songAlbano1.setArtist(alBano);
            songRepository.save(songAlbano1);

            Song songAlbano2 = new Song();
            songAlbano2.setTitle("Nel Sole");
            songAlbano2.setAuthor("Al Bano");
            songAlbano2.setArtist(alBano);
            songRepository.save(songAlbano2);

            Quiz quizAlbano = new Quiz();
            quizAlbano.setTitle("Quiz su Al Bano");
            quizRepository.save(quizAlbano);

            Question qAlbano1 = new Question();
            qAlbano1.setQuiz(quizAlbano);
            qAlbano1.setSongId(songAlbano1.getId());
            qAlbano1.setText("Chi canta 'Felicità' insieme ad Al Bano?");
            qAlbano1.setOptionA("Iva Zanicchi");
            qAlbano1.setOptionB("Romina Power");
            qAlbano1.setOptionC("Mina");
            qAlbano1.setOptionD("Rita Pavone");
            qAlbano1.setCorrect("B");
            qAlbano1.setType("AUTORE");
            questionRepository.save(qAlbano1);

            Question qAlbano2 = new Question();
            qAlbano2.setQuiz(quizAlbano);
            qAlbano2.setSongId(songAlbano2.getId());
            qAlbano2.setText("Quale di questi è un brano di Al Bano?");
            qAlbano2.setOptionA("Nel Sole");
            qAlbano2.setOptionB("Caruso");
            qAlbano2.setOptionC("Meraviglioso");
            qAlbano2.setOptionD("Ti lascerò");
            qAlbano2.setCorrect("A");
            qAlbano2.setType("TESTO");
            questionRepository.save(qAlbano2);

            Question qAlbano3 = new Question();
            qAlbano3.setQuiz(quizAlbano);
            qAlbano3.setSongId(songAlbano1.getId());
            qAlbano3.setText("A quale Festival di Sanremo partecipò 'Felicità'?");
            qAlbano3.setOptionA("1981");
            qAlbano3.setOptionB("1982");
            qAlbano3.setOptionC("1984");
            qAlbano3.setOptionD("1985");
            qAlbano3.setCorrect("B");
            qAlbano3.setType("SANREMO");
            questionRepository.save(qAlbano3);

            // Céline Dion
            Artist celine = new Artist();
            celine.setName("Céline Dion");
            artistRepository.save(celine);

            Song songCeline1 = new Song();
            songCeline1.setTitle("My Heart Will Go On");
            songCeline1.setAuthor("Céline Dion");
            songCeline1.setArtist(celine);
            songRepository.save(songCeline1);

            Song songCeline2 = new Song();
            songCeline2.setTitle("The Power of Love");
            songCeline2.setAuthor("Céline Dion");
            songCeline2.setArtist(celine);
            songRepository.save(songCeline2);

            Quiz quizCeline = new Quiz();
            quizCeline.setTitle("Quiz su Céline Dion");
            quizRepository.save(quizCeline);

            Question qCeline1 = new Question();
            qCeline1.setQuiz(quizCeline);
            qCeline1.setSongId(songCeline1.getId());
            qCeline1.setText("Chi canta 'My Heart Will Go On'?");
            qCeline1.setOptionA("Whitney Houston");
            qCeline1.setOptionB("Céline Dion");
            qCeline1.setOptionC("Mariah Carey");
            qCeline1.setOptionD("Madonna");
            qCeline1.setCorrect("B");
            qCeline1.setType("AUTORE");
            questionRepository.save(qCeline1);

            Question qCeline2 = new Question();
            qCeline2.setQuiz(quizCeline);
            qCeline2.setSongId(songCeline1.getId());
            qCeline2.setText("Per quale film è colonna sonora 'My Heart Will Go On'?");
            qCeline2.setOptionA("Titanic");
            qCeline2.setOptionB("Pretty Woman");
            qCeline2.setOptionC("Ghost");
            qCeline2.setOptionD("The Bodyguard");
            qCeline2.setCorrect("A");
            qCeline2.setType("COLONNA_SONORA");
            questionRepository.save(qCeline2);

            Question qCeline3 = new Question();
            qCeline3.setQuiz(quizCeline);
            qCeline3.setSongId(songCeline1.getId());
            qCeline3.setText("In che anno vinse l'Oscar 'My Heart Will Go On'?");
            qCeline3.setOptionA("1997");
            qCeline3.setOptionB("1998");
            qCeline3.setOptionC("1999");
            qCeline3.setOptionD("2000");
            qCeline3.setCorrect("B");
            qCeline3.setType("ANNO");
            questionRepository.save(qCeline3);

            // Robbie Williams
            Artist robbie = new Artist();
            robbie.setName("Robbie Williams");
            artistRepository.save(robbie);

            Song songRobbie1 = new Song();
            songRobbie1.setTitle("Angels");
            songRobbie1.setAuthor("Robbie Williams");
            songRobbie1.setArtist(robbie);
            songRepository.save(songRobbie1);

            Song songRobbie2 = new Song();
            songRobbie2.setTitle("Feel");
            songRobbie2.setAuthor("Robbie Williams");
            songRobbie2.setArtist(robbie);
            songRepository.save(songRobbie2);

            Quiz quizRobbie = new Quiz();
            quizRobbie.setTitle("Quiz su Robbie Williams");
            quizRepository.save(quizRobbie);

            Question qRobbie1 = new Question();
            qRobbie1.setQuiz(quizRobbie);
            qRobbie1.setSongId(songRobbie1.getId());
            qRobbie1.setText("Chi canta 'Angels'?");
            qRobbie1.setOptionA("Robbie Williams");
            qRobbie1.setOptionB("George Michael");
            qRobbie1.setOptionC("Gary Barlow");
            qRobbie1.setOptionD("Ronan Keating");
            qRobbie1.setCorrect("A");
            qRobbie1.setType("AUTORE");
            questionRepository.save(qRobbie1);

            Question qRobbie2 = new Question();
            qRobbie2.setQuiz(quizRobbie);
            qRobbie2.setSongId(songRobbie2.getId());
            qRobbie2.setText("Quale di questi è un brano di Robbie Williams?");
            qRobbie2.setOptionA("Viva Forever");
            qRobbie2.setOptionB("Feel");
            qRobbie2.setOptionC("Back for Good");
            qRobbie2.setOptionD("Wonderwall");
            qRobbie2.setCorrect("B");
            qRobbie2.setType("TESTO");
            questionRepository.save(qRobbie2);

            Question qRobbie3 = new Question();
            qRobbie3.setQuiz(quizRobbie);
            qRobbie3.setSongId(songRobbie1.getId());
            qRobbie3.setText("Da quale gruppo proveniva Robbie Williams prima della carriera solista?");
            qRobbie3.setOptionA("Take That");
            qRobbie3.setOptionB("Westlife");
            qRobbie3.setOptionC("Boyzone");
            qRobbie3.setOptionD("Blue");
            qRobbie3.setCorrect("A");
            qRobbie3.setType("AUTORE");
            questionRepository.save(qRobbie3);
        };
    }
}
