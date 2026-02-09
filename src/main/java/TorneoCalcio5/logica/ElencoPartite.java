package TorneoCalcio5.logica;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Comparator;

/**
 * @file ElencoPartite.java
 * @brief Gestisce lo storico delle partite.
 */
public class ElencoPartite {

    private ArrayList<Partita> elenco;
    private ObservableList<Partita> elencoOsservabile;

    /**
     * @brief Costruttore di default.
     * @post L'elenco viene inizializzato come vuoto.
     */
    public ElencoPartite() {
        elenco = new ArrayList<>();
        elencoOsservabile = FXCollections.observableArrayList();
    }
    
    /**
     * @brief Ordina le partite per data in ordine decrescente (più recenti prima).
    */
    public void ordinaPerData() {
        elenco.sort(Comparator.comparing(Partita::getData).reversed());
        elencoOsservabile.setAll(elenco);
    }

    /**
     * @brief Aggiunge una partita all'elenco.
     * @param[in] p La partita da aggiungere.
     */
    public void addPartita(Partita p) {
        elenco.add(p);
        ordinaPerData();
    }
    
    /**
     * @brief Rimuove una partita dallo storico.
     * @param[in] p La partita da rimuovere.
     * @return true se l'operazione ha successo.
     */
    public boolean removePartita(Partita p) {
        boolean res = elenco.remove(p);
        elencoOsservabile.setAll(elenco);
        return res;
    }

    /**
     * @brief Modifica una partita esistente nello storico.
     * Sostituisce i dati della vecchia partita con quelli della nuova.
     * @param[in] vecchia La partita originale da modificare.
     * @param[in] nuova La partita con i nuovi dati.
     */
    public void modificaPartita(Partita vecchia, Partita nuova) {
        vecchia.setGolCasa(nuova.getGolCasa());
        vecchia.setGolOspite(nuova.getGolOspite());
        vecchia.setData(nuova.getData());
        elencoOsservabile.setAll(elenco);
    }

    /**
     * @brief Restituisce la lista osservabile per la tabella.
     * @return ObservableList delle partite.
     */
    public ObservableList<Partita> getElencoOsservabile() {
        return elencoOsservabile;
    }
    
    /**
     * @brief Restituisce la lista base delle partite.
     * @return ArrayList delle partite.
     */
    public ArrayList<Partita> getElencoBase() {
        return elenco;
    }
}