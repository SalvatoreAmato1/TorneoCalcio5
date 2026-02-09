package TorneoCalcio5.interfacciagrafica;

import TorneoCalcio5.logica.ElencoPartite;
import TorneoCalcio5.logica.ElencoSquadre;
import TorneoCalcio5.logica.Squadra;
import TorneoCalcio5.logica.IOFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.stage.FileChooser;
import java.io.File;

/**
 * @file HomeController.java
 * @brief Controller principale della finestra Home.
 */
public class HomeController extends TabellaController {

    private ElencoPartite elencoPartite;

    /**
     * @brief Inizializza l'applicazione e le strutture dati.
     */
    public void inizializzaHome(ElencoSquadre squadre, ElencoPartite partite) {
        this.elencoPartite = partite;
        inizializzaTabella(squadre);
    }

    @FXML
    private void handleAggiungiSquadra(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaSquadraView.fxml"));
    
    // Creiamo manualmente l'istanza del controller FIGLIO
    InserimentoSquadraController ctrl = new InserimentoSquadraController();
    loader.setController(ctrl); // Diciamo al loader di usare questo oggetto
    
    Parent root = loader.load();
    ctrl.inizializzaAggiungi(elenco);
    
    mostraFinestra("Nuova Squadra", root);
    
    if (ctrl.isAggiunto()) {
        elenco.addSquadra(ctrl.getSquadra());
        aggiornaTabella();
    }
    }

    @FXML
    private void handleSalva(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salva Archivio Torneo (verranno creati due file)");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
    
    // Suggeriamo un nome base
        fileChooser.setInitialFileName("torneo");
        File file = fileChooser.showSaveDialog(tabella.getScene().getWindow());
    
        if (file != null) {
            try {
            // Rimuoviamo l'estensione se l'utente l'ha scritta, per gestire noi i suffissi
                String absolutePath = file.getAbsolutePath();
                String basePath = absolutePath.endsWith(".csv") ? absolutePath.substring(0, absolutePath.length() - 4) : absolutePath;

            // Salviamo i due file con nomi correlati
                IOFile.salvaSquadre(elenco, basePath + "_squadre.csv");
                IOFile.salvaPartite(elencoPartite, basePath + "_partite.csv");

                System.out.println("Dati salvati correttamente come: " + basePath + "_squadre/partite.csv");
            } catch (IOException e) {
                System.err.println("Errore nel salvataggio: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleCarica(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Carica Archivio Torneo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*_squadre.csv"));
    
        File file = fileChooser.showOpenDialog(tabella.getScene().getWindow());
    
        if (file != null) {
            try {
                String pathSquadre = file.getAbsolutePath();
            
                ElencoSquadre caricate = IOFile.caricaSquadre(pathSquadre);
                elenco.getElencoBase().clear();
                elenco.getElencoBase().addAll(caricate.getElencoBase());

                String pathPartite = pathSquadre.replace("_squadre.csv", "_partite.csv");
                File fPartite = new File(pathPartite);
            
                if (fPartite.exists()) {
                    ElencoPartite caricateP = IOFile.caricaPartite(pathPartite, elenco);
                    elencoPartite.getElencoBase().clear();
                    elencoPartite.getElencoBase().addAll(caricateP.getElencoBase());
                    elencoPartite.ordinaPerData(); 
                } else {
                    elencoPartite.getElencoBase().clear();
                }

                elenco.ricalcolaClassifica(elencoPartite);
                aggiornaTabella();
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleNuovaPartita(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaPartitaView.fxml"));
    
    // 1. Creiamo MANUALMENTE il controller figlio (Inserimento)
    InserimentoPartitaController ctrl = new InserimentoPartitaController();
    
    // 2. Diciamo al loader di usare questo controller specifico
    loader.setController(ctrl); 
    
    Parent root = loader.load();
    
    // 3. Inizializziamo
    ctrl.inizializzaAggiungi(elenco); 
    
    mostraFinestra("Inserisci Risultato", root);
    
    if (ctrl.isAggiunto()) {
        elencoPartite.addPartita(ctrl.getPartita());
        elenco.ricalcolaClassifica(elencoPartite); 
        aggiornaTabella();
    }
    }

    /**
     * @brief Apre la finestra dello storico partite.
     * Passa sia l'elenco partite che l'elenco squadre per permettere ricalcoli.
     */
    @FXML
    private void handleVediStorico(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Storico.fxml"));
        Parent root = loader.load(); 
        StoricoController ctrl = loader.getController();
        ctrl.inizializzaStorico(elencoPartite, elenco);
        mostraFinestra("Storico Partite", root);
        aggiornaTabella();
        
    }
}