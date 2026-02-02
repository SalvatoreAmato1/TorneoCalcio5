package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Squadra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;

/**
 * @file VisualizzaSquadraController.java
 * @brief Gestisce la visualizzazione dei dettagli di una squadra.
 */
public class VisualizzaSquadraController {

    @FXML private Label nomeLabel;
    @FXML private Label punteggioLabel;
    @FXML private ListView<String> listaGiocatoriView;

    private Squadra squadra;
    private boolean modificato = false;
    private boolean eliminato = false;

    /**
     * @brief Inizializza gli elementi grafici con i dati della squadra.
     * @param[in] s La squadra da visualizzare.
     */
    public void inizializzaVisualizza(Squadra s) {
    }

    @FXML
    private void handleModifica(ActionEvent event) throws IOException {
    }

    @FXML
    private void handleElimina(ActionEvent event) {
    }

    @FXML
    private void handleEsci(ActionEvent event) {
    }

    public boolean isModificato() {
        return false;
    }

    public boolean isEliminato() {
        return false;
    }

    public Squadra getSquadra() {
        return null;
    }

    private void chiudiFinestra() {
    }
}