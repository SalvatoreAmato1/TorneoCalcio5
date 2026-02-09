package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Partita;
import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.ElencoSquadre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ModificaPartitaController {

    @FXML protected TextField txtGolCasa; 
    @FXML protected TextField txtGolOspite;
    @FXML protected DatePicker datePickerPartita;
    @FXML protected ComboBox<Squadra> comboCasa;
    @FXML protected ComboBox<Squadra> comboOspite;
    
    protected Partita partita;
    protected boolean modificato = false;
    @FXML
    private Label lblSquadraCasa;
    @FXML
    private Label lblSquadraOspite;
    @FXML
    private Button btnConferma;
    
    

    public void inizializzaModifica(Partita p, ElencoSquadre squadre) {
        this.partita = p;
        comboCasa.setItems(squadre.getElencoOrdinato());
        comboOspite.setItems(squadre.getElencoOrdinato());
        comboCasa.setValue(p.getSquadraCasa());
        comboOspite.setValue(p.getSquadraOspite());
        txtGolCasa.setText(String.valueOf(p.getGolCasa()));
        txtGolOspite.setText(String.valueOf(p.getGolOspite()));
        datePickerPartita.setValue(p.getData());
        btnConferma.disableProperty().bind(
            txtGolCasa.textProperty().isEmpty()
            .or(txtGolOspite.textProperty().isEmpty())
            .or(comboCasa.valueProperty().isNull())
            .or(comboOspite.valueProperty().isNull())
            .or(datePickerPartita.valueProperty().isNull())
        );
    }

    public Partita getPartita() { 
        return partita; }
    
    public boolean isModificato() { 
        return modificato; }
    
    @FXML
    protected void handleConferma(ActionEvent event) {
        if (aggiornaPartita()) {
            this.modificato = true;
            chiudiFinestra();
        }
    }
    
    protected boolean aggiornaPartita() {
        try {
            Squadra casa = comboCasa.getValue();
            Squadra ospite = comboOspite.getValue();

            // Errore: le squadre non possono essere uguali
            if (casa.equals(ospite)) {
                mostraErrore("Le squadre selezionate devono essere diverse.");
                return false;
            }

            // Errore: il punteggio deve essere numerico
            int golC = Integer.parseInt(txtGolCasa.getText());
            int golO = Integer.parseInt(txtGolOspite.getText());

            // Se tutto è corretto, applica le modifiche alla partita
            partita.setSquadraCasa(casa);
            partita.setSquadraOspite(ospite);
            partita.setGolCasa(golC);
            partita.setGolOspite(golO);
            partita.setData(datePickerPartita.getValue());
            
            return true;
        } catch (NumberFormatException e) {
            mostraErrore("Il punteggio deve essere un numero intero.");
            return false;
        }
    }

    
    /**
     * @brief Metodo di utilità per mostrare avvisi di errore.
     */
    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di Validazione");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
    
    protected void chiudiFinestra() {
        ((Stage) txtGolCasa.getScene().getWindow()).close();
    }

    @FXML
    private void handleAnnulla(ActionEvent event) {
        chiudiFinestra();
    }
}