package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.ElencoPartite;
import TorneoCalcio5.logica.ElencoSquadre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * @file HomeController.java
 * @brief Controller principale della finestra Home.
 */
public class HomeController extends TabellaController {

    private ElencoPartite elencoPartite;

    /**
     * @brief Inizializza l'applicazione e le strutture dati.
     */
    public void inizializzaHome() {
    }

    @FXML
    private void handleAggiungiSquadra(ActionEvent event) {
    }

    @FXML
    private void handleSalva(ActionEvent event) throws IOException {
    }

    @FXML
    private void handleCarica(ActionEvent event) throws Exception {
    }

    @FXML
    private void handleNuovaPartita(ActionEvent event) {
    }

    /**
     * @brief Apre la finestra dello storico partite.
     * Passa sia l'elenco partite che l'elenco squadre per permettere ricalcoli.
     */
    @FXML
    private void handleVediStorico(ActionEvent event) {
    }
}