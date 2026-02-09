package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.Partita;
import TorneoCalcio5.logica.ElencoPartite;
import TorneoCalcio5.logica.ElencoSquadre;
import TorneoCalcio5.logica.Squadra;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @file StoricoController.java
 * @brief Gestisce la finestra dello storico partite, inclusa modifica ed eliminazione.
 */
public class StoricoController {

    @FXML private TableView<Partita> tabellaStorico;
    @FXML private TableColumn<Partita, LocalDate> dataCln;
    @FXML private TableColumn<Partita, Squadra> casaCln;
    @FXML private TableColumn<Partita, Integer> golCasaCln;
    @FXML private TableColumn<Partita, Integer> golOspiteCln;
    @FXML private TableColumn<Partita, Squadra> ospiteCln;

    private ElencoPartite elencoPartite;
    private ElencoSquadre elencoSquadre; 

    /**
     * @brief Inizializza la tabella dello storico.
     * @param[in] partite L'elenco delle partite.
     * @param[in] squadre L'elenco delle squadre (serve per il ricalcolo classifica).
     */
    public void inizializzaStorico(ElencoPartite partite, ElencoSquadre squadre) {
        this.elencoPartite = partite;
        this.elencoSquadre = squadre;
        dataCln.setCellValueFactory(new PropertyValueFactory<>("data"));
        casaCln.setCellValueFactory(new PropertyValueFactory<>("squadraCasa"));
        golCasaCln.setCellValueFactory(new PropertyValueFactory<>("golCasa"));
        golOspiteCln.setCellValueFactory(new PropertyValueFactory<>("golOspite"));
        ospiteCln.setCellValueFactory(new PropertyValueFactory<>("squadraOspite"));

        tabellaStorico.setItems(elencoPartite.getElencoOsservabile());
    }

    @FXML
    private void handleModificaPartita(ActionEvent event) {
        Partita selezionata = tabellaStorico.getSelectionModel().getSelectedItem();
        if (selezionata != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaPartitaView.fxml"));
            
            // 1. CREIAMO IL CONTROLLER MANUALMENTE (visto che nel FXML non c'è più)
                ModificaPartitaController controller = new ModificaPartitaController();
            
            // 2. LO IMPOSTIAMO NEL LOADER PRIMA DI CARICARE
                loader.setController(controller);
            
                Parent root = loader.load();
            
            // 3. INIZIALIZZIAMO
                controller.inizializzaModifica(selezionata, elencoSquadre);

                Stage stage = new Stage();
                stage.setTitle("Modifica Partita");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

                if (controller.isModificato()) {
                    aggiornaClassificaETabella();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleEliminaPartita(ActionEvent event) {
        Partita selezionata = tabellaStorico.getSelectionModel().getSelectedItem();
        if (selezionata != null) {
            elencoPartite.removePartita(selezionata);
            aggiornaClassificaETabella();
        }
    }
    
    /**
     * @brief Resetta i punti, riapplica le partite e ordina cronologicamente.
     */
    private void aggiornaClassificaETabella() {
        elencoPartite.ordinaPerData(); 
        elencoSquadre.ricalcolaClassifica(elencoPartite); 
        tabellaStorico.refresh();
    }

    @FXML
    private void handleEsci(ActionEvent event) {
        Stage stage = (Stage) tabellaStorico.getScene().getWindow();
        stage.close();
    }
}