package TorneoCalcio5.logica;

import java.util.List;

/**
 * @file Squadra.java
 * @brief Classe che rappresenta una squadra.
 */
public class Squadra {

    private String nome;
    private List<String> giocatori;
    private int punteggio;

    /**
     * @brief Costruttore di default.
     * @post I campi vengono inizializzati come vuoti o a zero.
     */
    public Squadra() {
    }

    /**
     * @brief Viene inizializzato il nome della squadra.
     * @param[in] nome Il nome da assegnare.
     */
    public void setNome(String nome) {
    }

    /**
     * @brief Restituisce il nome.
     * @return Il nome della squadra.
     */
    public String getNome() {
        return null;
    }

    /**
     * @brief Viene inizializzata la lista dei giocatori.
     * Sovrascrive la lista attuale con quella fornita.
     * @param[in] nuovaRosa La nuova lista di giocatori.
     */
    public void setGiocatori(List<String> nuovaRosa) {
    }

    /**
     * @brief Restituisce la lista dei giocatori.
     * @return La lista dei giocatori.
     */
    public List<String> getGiocatori() {
        return null;
    }

    /**
     * @brief Viene aggiornato il punteggio aggiungendo i punti.
     * @param[in] p I punti da sommare.
     */
    public void aggiungiPunti(int p) {
    }

    /**
     * @brief Viene inizializzato il punteggio.
     * Utile per reset o caricamento.
     * @param[in] p Il punteggio da impostare.
     */
    public void setPunteggio(int p) {
        // TODO: Assegnazione
    }

    /**
     * @brief Restituisce il punteggio.
     * @return Il punteggio attuale.
     */
    public int getPunteggio() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}