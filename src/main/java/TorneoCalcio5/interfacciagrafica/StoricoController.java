package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Partita;
import TorneoCalcio5.logica.ElencoPartite;
import TorneoCalcio5.logica.ElencoSquadre;
import TorneoCalcio5.logica.Squadra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.time.LocalDate;

/**
 * @file StoricoController.java
 * @brief Gestisce la finestra dello storico partite, inclusa modifica ed eliminazione.
 */
public class StoricoController {

    @FXML private TableView<Partita> tabellaStorico;
    @FXML private TableColumn<Partita, LocalDate> dataCln;
    @FXML private TableColumn<Partita, Squadra> casaCln;
    @FXML private TableColumn<Partita, Integer> golCasaCln;
    @FXML private TableColumn<Partita, Integer> golOspiteCln;
    @FXML private TableColumn<Partita, Squadra> ospiteCln;

    private ElencoPartite elencoPartite;
    private ElencoSquadre elencoSquadre; 

    /**
     * @brief Inizializza la tabella dello storico.
     * @param[in] partite L'elenco delle partite.
     * @param[in] squadre L'elenco delle squadre (serve per il ricalcolo classifica).
     */
    public void inizializzaStorico(ElencoPartite partite, ElencoSquadre squadre) {
    }

    @FXML
    private void handleModificaPartita(ActionEvent event) {
    }

    @FXML
    private void handleEliminaPartita(ActionEvent event) {
    }

    @FXML
    private void handleEsci(ActionEvent event) {
    }
}