package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.ElencoSquadre;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import java.io.IOException;

/**
 * @file TabellaController.java
 * @brief Classe base che gestisce la visualizzazione tabellare e la selezione.
 */
public class TabellaController {

    @FXML protected TableView<Squadra> tabella;
    @FXML protected TableColumn<Squadra, String> nomeCln;
    @FXML protected TableColumn<Squadra, Integer> puntiCln;
    
    protected ElencoSquadre elenco;

    /**
     * @brief Inizializza la tabella collegandola all'elenco.
     * @param[in] lista L'elenco dati.
     */
    protected void inizializzaTabella(ElencoSquadre lista) {
    }

    /**
     * @brief Aggiorna i dati mostrati nella tabella.
     */
    protected void aggiornaTabella() {
    }

    @FXML
    protected void handleSquadraSelezionata() throws IOException {
    }

    /**
     * @brief Apre la finestra di dettaglio di una squadra.
     * @param[in] s La squadra da visualizzare.
     */
    protected void mostraDettagli(Squadra s) throws IOException {
    }

    protected void mostraFinestra(String titolo, Parent root) {
    }
}