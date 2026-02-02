package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.ElencoSquadre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * @file ModificaSquadraController.java
 * @brief Gestisce la finestra di modifica.
 */
public class ModificaSquadraController {

    @FXML protected TextField nomeField;
    @FXML protected ListView<String> listaGiocatoriView;
    @FXML protected TextField nuovoGiocatoreField;

    protected Squadra squadra;
    
    /**
     * @brief Riferimento all'elenco squadre per il controllo duplicati.
     */
    protected ElencoSquadre elenco;
    
    protected boolean modificato = false;

    /**
     * @brief Inizializza la finestra con i dati della squadra e l'elenco per i 
     * controlli.
     * @param[in] s La squadra da modificare.
     * @param[in] elenco L'elenco completo delle squadre (per verificare nomi 
     * doppi).
     */
    public void inizializzaModifica(Squadra s, ElencoSquadre elenco) {
    }

    public Squadra getSquadra() { return squadra; }
    public boolean isModificato() { return modificato; }

    @FXML
    protected void handleAggiungiGiocatore(ActionEvent e) {
    }

    @FXML
    protected void handleConferma(ActionEvent event) {
    }

    @FXML
    protected void handleEsci(ActionEvent event) {
    }

    protected boolean aggiornaSquadra() {
        return false;
    }

    protected void chiudiFinestra() {
    }

    /**
     * @brief Metodo helper per mostrare messaggi di errore a video.
     * @param[in] titolo Il titolo della finestra di errore.
     * @param[in] msg Il messaggio descrittivo dell'errore.
     */
    protected void mostraErrore(String titolo, String msg) { 
    }
}