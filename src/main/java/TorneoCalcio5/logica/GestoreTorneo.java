package TorneoCalcio5.logica;

import TorneoCalcio5.interfacciagrafica.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @file GestoreTorneo.java
 * @brief Classe principale per l'avvio dell'applicazione.
 */
public class GestoreTorneo extends Application {

    /**
     * @brief Avvia l'applicazione JavaFX.
     * @param[in] stage Lo stage principale.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TorneoCalcio5/interfacciagrafica/HomeView.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        ElencoSquadre squadre = new ElencoSquadre();
        ElencoPartite partite = new ElencoPartite();
        controller.inizializzaHome(squadre, partite);
        stage.setTitle("Gestione Torneo Calcio a 5");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @brief Metodo main di ingresso dell'applicazione.
     * @param[in] args Argomenti da linea di comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
}