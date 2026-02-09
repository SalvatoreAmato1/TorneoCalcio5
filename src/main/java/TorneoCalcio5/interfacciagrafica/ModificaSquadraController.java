package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.ElencoSquadre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * @file ModificaSquadraController.java
 * @brief Gestisce la finestra di modifica.
 */
public class ModificaSquadraController {

    @FXML private TextField nomeField;
    @FXML private TextField txtNome1, txtCognome1, txtNome2, txtCognome2, txtNome3, txtCognome3, txtNome4, txtCognome4, txtNome5, txtCognome5;
    @FXML private DatePicker dpData1, dpData2, dpData3, dpData4, dpData5;
    @FXML private Button btnConferma;

    private List<TextField> nomiGiocatori;
    private List<TextField> cognomiGiocatori;
    private List<DatePicker> dateGiocatori;
    
    protected Squadra squadra;
    
    /**
     * @brief Riferimento all'elenco squadre per il controllo duplicati.
     */
    protected ElencoSquadre elenco;
    
    protected boolean modificato = false;
    
    @FXML
    public void initialize() {
        nomiGiocatori = Arrays.asList(txtNome1, txtNome2, txtNome3, txtNome4, txtNome5);
        cognomiGiocatori = Arrays.asList(txtCognome1, txtCognome2, txtCognome3, txtCognome4, txtCognome5);
        dateGiocatori = Arrays.asList(dpData1, dpData2, dpData3, dpData4, dpData5);
        btnConferma.setDisable(true);
        nomeField.textProperty().addListener((obs, oldVal, newVal) -> validaCampi());
        for (int i = 0; i < 5; i++) {
        nomiGiocatori.get(i).textProperty().addListener((obs, oldVal, newVal) -> validaCampi());
        cognomiGiocatori.get(i).textProperty().addListener((obs, oldVal, newVal) -> validaCampi());
        dateGiocatori.get(i).valueProperty().addListener((obs, oldVal, newVal) -> validaCampi());
        }
    }
        
    /**
     * @brief Inizializza la finestra con i dati della squadra e l'elenco per i 
     * controlli.
     * @param[in] s La squadra da modificare.
     * @param[in] elenco L'elenco completo delle squadre (per verificare nomi 
     * doppi).
     */
    public void inizializzaModifica(Squadra s, ElencoSquadre elenco) {
        this.squadra = s;
        this.elenco = elenco;
        nomeField.setText(s.getNome());
        
        List<String> rosa = s.getGiocatori();
        for (int i = 0; i < rosa.size() && i < nomiGiocatori.size(); i++) {
            // Dividiamo la stringa "Nome;Cognome;Data" come nel progetto Rubrica
            String[] dati = rosa.get(i).split(";", -1);
            
            if (dati.length > 0) nomiGiocatori.get(i).setText(dati[0]);
            if (dati.length > 1) cognomiGiocatori.get(i).setText(dati[1]);
            if (dati.length > 2 && !dati[2].isEmpty()) {
                dateGiocatori.get(i).setValue(LocalDate.parse(dati[2]));
            }
        }
    }

    public Squadra getSquadra() { 
        return squadra; }
    
    public boolean isModificato() { 
        return modificato; }

    @FXML
    protected void handleAggiungiGiocatore(ActionEvent e) {
    }

    @FXML
    protected void handleConferma(ActionEvent event) {
        if (aggiornaSquadra()) {
            modificato = true;
            chiudiFinestra();
        }
    }

    @FXML
    protected void handleEsci(ActionEvent event) {
        chiudiFinestra();
    }

    protected boolean aggiornaSquadra() {
        String nuovoNome = nomeField.getText().trim();
        if (nuovoNome.isEmpty()) return false;
        if (!nuovoNome.equalsIgnoreCase(squadra.getNome())) {
            if (elenco.cercaSquadra(nuovoNome) != null) {
                mostraErrore("Errore Nome", "Esiste già una squadra chiamata '" + nuovoNome + "'.");
                return false;
            }
        }
        squadra.setNome(nuovoNome);
        List<String> nuovaRosa = new ArrayList<>();
        for (int i = 0; i < nomiGiocatori.size(); i++) {
            String n = nomiGiocatori.get(i).getText().trim();
            String c = cognomiGiocatori.get(i).getText().trim();
            LocalDate d = dateGiocatori.get(i).getValue();
            if (!n.isEmpty() || !c.isEmpty()) {
                nuovaRosa.add(n + ";" + c + ";" + (d != null ? d.toString() : ""));
            }
        }
        squadra.setGiocatori(nuovaRosa);
        return true;
    }

    protected void chiudiFinestra() {
        ((Stage) nomeField.getScene().getWindow()).close();
    }

    /**
     * @brief Metodo helper per mostrare messaggi di errore a video.
     * @param[in] titolo Il titolo della finestra di errore.
     * @param[in] msg Il messaggio descrittivo dell'errore.
     */
    protected void mostraErrore(String titolo, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    private void validaCampi() {
        boolean nomeSquadraOk = !nomeField.getText().trim().isEmpty();
        boolean giocatoriOk = true;
        for (int i = 0; i < 5; i++) {
            if (nomiGiocatori.get(i).getText().trim().isEmpty() || 
                cognomiGiocatori.get(i).getText().trim().isEmpty() || 
                dateGiocatori.get(i).getValue() == null) {
                giocatoriOk = false;
                break; 
        }
    }
    btnConferma.setDisable(!(nomeSquadraOk && giocatoriOk));
}
}