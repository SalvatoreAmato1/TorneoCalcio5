package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.ElencoSquadre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import TorneoCalcio5.logica.Squadra;

/**
 * @file InserimentoPartitaController.java
 * @brief Gestisce l'inserimento di una nuova partita.
 */
public class InserimentoPartitaController extends ModificaPartitaController {

    @FXML private ComboBox<Squadra> comboCasa;
    @FXML private ComboBox<Squadra> comboOspite;
    
    private boolean aggiunto = false;

    /**
     * @brief Inizializza la finestra di inserimento.
     * @param[in] elenco L'elenco squadre per popolare le combobox.
     */
    public void inizializzaAggiungi(ElencoSquadre elenco) {
    }

    @Override
    protected void handleConferma(ActionEvent event) {
    }
    
    public boolean isAggiunto() {
        return false;
    }
}