package TorneoCalcio5.logica;

import java.util.ArrayList;
import javafx.collections.ObservableList;

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
    }

    /**
     * @brief Aggiunge una partita all'elenco.
     * @param[in] p La partita da aggiungere.
     */
    public void addPartita(Partita p) {
    }
    
    /**
     * @brief Rimuove una partita dallo storico.
     * @param[in] p La partita da rimuovere.
     * @return true se l'operazione ha successo.
     */
    public boolean removePartita(Partita p) {
        return false;
    }

    /**
     * @brief Modifica una partita esistente nello storico.
     * Sostituisce i dati della vecchia partita con quelli della nuova.
     * @param[in] vecchia La partita originale da modificare.
     * @param[in] nuova La partita con i nuovi dati.
     */
    public void modificaPartita(Partita vecchia, Partita nuova) {
    }

    /**
     * @brief Restituisce la lista osservabile per la tabella.
     * @return ObservableList delle partite.
     */
    public ObservableList<Partita> getElencoOsservabile() {
        return null;
    }
    
    /**
     * @brief Restituisce la lista base delle partite.
     * @return ArrayList delle partite.
     */
    public ArrayList<Partita> getElencoBase() {
        return null;
    }
}