package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.ElencoSquadre;
import TorneoCalcio5.logica.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.time.LocalDate;

/**
 * @file InserimentoPartitaController.java
 * @brief Gestisce l'inserimento di una nuova partita.
 */
public class InserimentoPartitaController extends ModificaPartitaController {

    
    private boolean aggiunto = false;

    /**
     * @brief Inizializza la finestra di inserimento.
     * @param[in] elenco L'elenco squadre per popolare le combobox.
     */
    public void inizializzaAggiungi(ElencoSquadre elenco) {
        this.partita = new Partita();
        super.inizializzaModifica(this.partita, elenco);
        datePickerPartita.setValue(LocalDate.now());
    }
    
    @FXML
    @Override
    protected void handleConferma(ActionEvent event) {
        if (aggiornaPartita()) {
            this.aggiunto = true;
            this.modificato = true;
            chiudiFinestra();
        }
    }
    
    public boolean isAggiunto() {
        return aggiunto;
    }
}