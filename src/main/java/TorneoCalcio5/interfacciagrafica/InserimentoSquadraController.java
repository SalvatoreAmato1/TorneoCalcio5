package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.ElencoSquadre; //
import javafx.event.ActionEvent;

/**
 * @file InserimentoSquadraController.java
 * @brief Gestisce l'aggiunta estendendo Modifica.
 */
public class InserimentoSquadraController extends ModificaSquadraController {

    private boolean aggiunto = false;

    /**
     * @brief Inizializza la finestra vuota passando l'elenco per i controlli.
     * @param[in] elenco L'elenco attuale delle squadre.
     */
    public void inizializzaAggiungi(ElencoSquadre elenco) {
        super.inizializzaModifica(new Squadra(), elenco);
    }

    @Override
    protected void handleConferma(ActionEvent event) {
        if (super.aggiornaSquadra()) {
            this.aggiunto = true; 
            this.modificato = true;
            super.chiudiFinestra();
        }
    }

    /**
     * @brief Verifica se la partita è stata aggiunta.
     * @return true se l'operazione è stata confermata.
     */
    public boolean isAggiunto() { 
        return aggiunto; 
    }
}