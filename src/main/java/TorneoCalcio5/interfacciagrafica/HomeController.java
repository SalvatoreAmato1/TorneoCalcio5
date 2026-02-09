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
        Parent root = loader.load();
        
        InserimentoSquadraController ctrl = loader.getController();
        ctrl.inizializzaAggiungi(elenco);
        
        mostraFinestra("Nuova Squadra", root);
        
        if (ctrl.isAggiunto()) {
            elenco.addSquadra(ctrl.getSquadra());
            aggiornaTabella();
        }
    }

    @FXML
    private void handleSalva(ActionEvent event) throws IOException {
        try {
            // Passa 'elenco' (oggetto ElencoSquadre) e 'elencoPartite' (oggetto ElencoPartite)
            // NON i getElencoBase(), perché i metodi in IOFile accettano gli oggetti contenitore
            IOFile.salvaSquadre(elenco, "squadre.csv");
            IOFile.salvaPartite(elencoPartite, "partite.csv");
        } catch (IOException e) {
            System.err.println("Errore nel salvataggio: " + e.getMessage());
        }
    }

    @FXML
    private void handleCarica(ActionEvent event) throws Exception {
        try {
            ElencoSquadre nuoveSquadre = IOFile.caricaSquadre("squadre.csv");
            elenco.getElencoBase().clear();
            elenco.getElencoBase().addAll(nuoveSquadre.getElencoBase());
            ElencoPartite nuovePartite = IOFile.caricaPartite("partite.csv", elenco);
            elencoPartite.getElencoBase().clear();
            elencoPartite.getElencoBase().addAll(nuovePartite.getElencoBase());
            elenco.ricalcolaClassifica(elencoPartite);
            aggiornaTabella();
            System.out.println("Dati caricati con successo.");
        } catch (Exception e) {
            System.err.println("Errore nel caricamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNuovaPartita(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaPartitaView.fxml"));
        Parent root = loader.load();
        
        InserimentoPartitaController ctrl = loader.getController();
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