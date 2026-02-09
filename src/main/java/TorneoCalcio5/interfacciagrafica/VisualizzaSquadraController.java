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
        this.elenco = elenco;
        
        // Imposta le label con nome e punti
        nomeLabel.setText("Squadra: " + s.getNome());
        punteggioLabel.setText("Punti in classifica: " + s.getPunteggio());
    
        // Pulisce e popola la ListView
        listaGiocatoriView.setItems(FXCollections.observableArrayList());
        
        for (String g : s.getGiocatori()) {
            // I dati sono salvati come "Nome;Cognome;Data"
            String[] dati = g.split(";", -1);
            
            // Costruiamo la stringa riga per riga
            StringBuilder sb = new StringBuilder();
            
            // Aggiungiamo Nome e Cognome (se presenti)
            if (dati.length >= 2) {
                sb.append(dati[0]).append(" ").append(dati[1]);
            }
            
            // Aggiungiamo la data di nascita (indice 2) se presente
            if (dati.length > 2 && !dati[2].isEmpty()) {
                // Formattiamo leggermente per renderlo più leggibile
                String dataFormattata = dati[2].replace("-", "/");
                sb.append(" (Nato il: ").append(dataFormattata).append(")");
            }
            
            // Aggiungiamo alla lista solo se la riga non è rimasta vuota
            if (sb.length() > 0) {
                listaGiocatoriView.getItems().add(sb.toString());
            }
        }
        
        // Se la squadra non ha giocatori, aggiungiamo un messaggio di cortesia
        if (listaGiocatoriView.getItems().isEmpty()) {
            listaGiocatoriView.getItems().add("Nessun giocatore registrato in questa squadra.");
        }
    }

    @FXML
    private void handleModifica(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaSquadraView.fxml"));
        ModificaSquadraController ctrl = new ModificaSquadraController();
        loader.setController(ctrl);
    
        Parent root = loader.load();
        ctrl.inizializzaModifica(squadra, elenco);
    
        Stage stage = new Stage();
        stage.setTitle("Modifica Squadra");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    
        if (ctrl.isModificato()) {
            this.modificato = true;
            inizializzaVisualizza(squadra, elenco); // Aggiorna la vista corrente
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