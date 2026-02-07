package TorneoCalcio5.logica;

import java.util.ArrayList;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file ElencoSquadre.java
 * @brief Gestisce la lista delle squadre e il loro ordinamento.
 */
public class ElencoSquadre {

    private ArrayList<Squadra> elenco;
    private ObservableList<Squadra> elencoOrdinato;

    /**
     * @brief Comparatore per punteggio decrescente.
     */
    private class Comparatore implements Comparator<Squadra> {
        @Override
        public int compare(Squadra s1, Squadra s2) {
            return Integer.compare(s2.getPunteggio(), s1.getPunteggio());
        }
    }

    /**
     * @brief Costruttore di default.
     * @post L'elenco viene inizializzato come vuoto.
     */
    public ElencoSquadre() {
        elenco = new ArrayList<>();
        elencoOrdinato = FXCollections.observableArrayList();
    }

    /**
     * @brief Aggiunge una squadra all'elenco.
     * @param[in] s La squadra da aggiungere.
     */
    public void addSquadra(Squadra s) {
        elenco.add(s);
        aggiornaElenco();
    }

    /**
     * @brief Rimuove una squadra.
     * @param[in] s La squadra da rimuovere.
     * @return true se rimossa con successo.
     */
    public boolean removeSquadra(Squadra s) {
        boolean res = elenco.remove(s);
        aggiornaElenco();
        return res;
    }

    /**
     * @brief Modifica una squadra esistente.
     * @param[in] vecchia La squadra originale.
     * @param[in] nuova I nuovi dati da copiare.
     */
    public void modificaSquadra(Squadra vecchia, Squadra nuova) {
        vecchia.setNome(nuova.getNome());
        vecchia.setGiocatori(new ArrayList<>(nuova.getGiocatori()));
        aggiornaElenco();
    }

    /**
     * @brief Ritorna l'elenco ordinato per classifica.
     * @return ObservableList ordinata.
     */
    public ObservableList<Squadra> getElencoOrdinato() {
        return elencoOrdinato;
    }
    
    //public ArrayList<Squadra> getElencoBase() { 
       // return elenco; 
    //}

    
    /**
     * @brief Ricalcola classifica basandosi sui risultati.
     * @param[in] elencoPartite Il riferimento allo storico delle partite da analizzare.
     * @pre L'oggetto @p elencoPartite non deve essere nullo e deve contenere lo storico aggiornato.
     * @post I punteggi di tutte le squadre nell'elenco vengono aggiornati e la lista viene ordinata in base alla classifica.
     */
    public void ricalcolaClassifica(ElencoPartite elencoPartite) {
        for (Squadra s : elenco) s.setPunteggio(0);
        for (Partita p : elencoPartite.getElencoBase()) p.applicaPunti();
        aggiornaElenco();
    }

    /**
     * @brief Riordina internamente la lista per punteggio.
     */
    private void aggiornaElenco() {
        elenco.sort(new Comparatore());
        elencoOrdinato.setAll(elenco);
    }
    
    /**
     * @brief Restituisce l'elenco base delle squadre.
     * @return ArrayList delle squadre.
     */
    public ArrayList<Squadra> getElencoBase() { 
        return elenco;
    }
}