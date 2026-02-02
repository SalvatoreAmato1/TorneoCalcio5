package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Partita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificaPartitaController {

    @FXML protected TextField golCasaField;
    @FXML protected TextField golOspiteField;
    
    protected Partita partita;
    protected boolean modificato = false;

    public void inizializzaModifica(Partita p) {
    }

    public Partita getPartita() { return partita; }
    public boolean isModificato() { return modificato; }

    @FXML
    protected void handleConferma(ActionEvent event) {
    }

    protected boolean aggiornaPartita() {
        return true;
    }

    protected void chiudiFinestra() {
    }
}