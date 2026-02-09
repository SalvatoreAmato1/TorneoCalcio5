package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.ElencoSquadre;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private ElencoSquadre elenco;
    private boolean modificato = false;
    private boolean eliminato = false;

    /**
     * @brief Inizializza gli elementi grafici con i dati della squadra.
     * @param[in] s La squadra da visualizzare.
     */
    public void inizializzaVisualizza(Squadra s,ElencoSquadre elenco) {
        this.squadra = s;
        this.elenco = elenco; // Ora assegna correttamente l'oggetto passato
        nomeLabel.setText(s.getNome());
        punteggioLabel.setText("Punti in classifica: " + s.getPunteggio());
    
        listaGiocatoriView.setItems(FXCollections.observableArrayList());
        for (String g : s.getGiocatori()) {
            String[] dati = g.split(";", -1);
            if (dati.length >= 2) {
                listaGiocatoriView.getItems().add(dati[0] + " " + dati[1]);
            }
        }
    }

    @FXML
    private void handleModifica(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaSquadraView.fxml"));
        Parent root = loader.load();
        
        ModificaSquadraController ctrl = loader.getController();
        ctrl.inizializzaModifica(squadra, elenco);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        
        if (ctrl.isModificato()) {
            this.modificato = true;
            inizializzaVisualizza(squadra, elenco); 
        }
    }

    @FXML
    private void handleElimina(ActionEvent event) {
        this.eliminato = true;
        chiudiFinestra();
    }

    @FXML
    private void handleEsci(ActionEvent event) {
        chiudiFinestra();
    }

    public boolean isModificato() {
        return modificato;
    }

    public boolean isEliminato() {
        return eliminato;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    private void chiudiFinestra() {
        ((Stage) nomeLabel.getScene().getWindow()).close();
    }
}