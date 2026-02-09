package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.ElencoSquadre;
import TorneoCalcio5.logica.Squadra;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * @file TabellaController.java
 * @brief Classe base che gestisce la visualizzazione tabellare e la selezione.
 */
public class TabellaController {

    @FXML protected TableView<Squadra> tabella;
    @FXML protected TableColumn<Squadra, String> nomeCln;
    @FXML protected TableColumn<Squadra, Integer> puntiCln;
    
    protected ElencoSquadre elenco;

    /**
     * @brief Inizializza la tabella collegandola all'elenco.
     * @param[in] lista L'elenco dati.
     */
    protected void inizializzaTabella(ElencoSquadre lista) {
        this.elenco = lista;
        
        nomeCln.setCellValueFactory(new PropertyValueFactory<>("nome"));

        puntiCln.setCellValueFactory(new PropertyValueFactory<>("punteggio"));
        
        tabella.setItems(elenco.getElencoOrdinato());
    }

    /**
     * @brief Aggiorna i dati mostrati nella tabella.
     */
    protected void aggiornaTabella() {
        tabella.refresh();
    }

    /**
     * @brief Gestisce l'evento di selezione di una riga nella tabella.
     * Recupera l'oggetto selezionato e apre la finestra di dettaglio.
     * @throws IOException Se il caricamento del file FXML dei dettagli fallisce.
     */
    @FXML
    protected void handleSquadraSelezionata() throws IOException {
        Squadra selezionata = tabella.getSelectionModel().getSelectedItem();
        if (selezionata != null) {
            mostraDettagli(selezionata);
        }
    }

    /**
     * @brief Apre la finestra di dettaglio di una squadra.
     * @param[in] s La squadra da visualizzare.
     */
    protected void mostraDettagli(Squadra s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TorneoCalcio5/interfacciagrafica/VisualizzaSquadraView.fxml"));
        Parent root = loader.load();  
        VisualizzaSquadraController controller = loader.getController();
        controller.inizializzaVisualizza(s, elenco);
        mostraFinestra("Dettagli Squadra: " + s.getNome(), root);
        if (controller.isEliminato()) {
            elenco.removeSquadra(s);
            aggiornaTabella();
        } 
        else if (controller.isModificato()) {
            aggiornaTabella();
        }
    }

    /**
     * @brief Metodo helper per la creazione e visualizzazione di una finestra modale.
     * @param[in] titolo Il titolo da assegnare alla finestra.
     * @param[in] root Il nodo radice della scena caricata dal file FXML.
     */
    protected void mostraFinestra(String titolo, Parent root) {
        Stage stage = new Stage();
        stage.setTitle(titolo);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); 
        stage.showAndWait();
    }
}