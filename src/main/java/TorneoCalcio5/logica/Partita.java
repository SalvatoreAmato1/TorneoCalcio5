package TorneoCalcio5.logica;

import java.time.LocalDate;

/**
 * @file Partita.java
 * @brief Classe che rappresenta una partita.
 */
public class Partita {

    private Squadra casa;
    private Squadra ospite;
    private int golCasa;
    private int golOspite;
    private LocalDate data;

    /**
     * @brief Costruttore di default.
     * @post I campi vengono inizializzati come null o zero.
     */
    public Partita() {
    }

    /**
     * @brief Viene inizializzata la squadra di casa.
     * @param[in] casa La squadra di casa.
     */
    public void setSquadraCasa(Squadra casa) {
    }

    /**
     * @brief Restituisce la squadra di casa.
     * @return La squadra di casa.
     */
    public Squadra getSquadraCasa() {
        return null;
    }

    /**
     * @brief Viene inizializzata la squadra ospite.
     * @param[in] ospite La squadra ospite.
     */
    public void setSquadraOspite(Squadra ospite) {
    }

    /**
     * @brief Restituisce la squadra ospite.
     * @return La squadra ospite.
     */
    public Squadra getSquadraOspite() {
        return null;
    }

    /**
     * @brief Viene inizializzato il numero di gol della squadra di casa.
     * @param[in] gol Il numero di gol.
     */
    public void setGolCasa(int gol) {

    }

    /**
     * @brief Restituisce i gol della squadra di casa.
     * @return Il numero di gol.
     */
    public int getGolCasa() {
        return 0;
    }

    /**
     * @brief Viene inizializzato il numero di gol della squadra ospite.
     * @param[in] gol Il numero di gol.
     */
    public void setGolOspite(int gol) {

    }

    /**
     * @brief Restituisce i gol della squadra ospite.
     * @return Il numero di gol.
     */
    public int getGolOspite() {
        return 0;
    }

    /**
     * @brief Viene inizializzata la data della partita.
     * @param[in] data La data.
     */
    public void setData(LocalDate data) {

    }

    /**
     * @brief Restituisce la data della partita.
     * @return La data.
     */
    public LocalDate getData() {
        return null;
    }

    /**
     * @brief Calcola i punti e aggiorna le squadre.
     * Assegna 3 punti per la vittoria, 1 per il pareggio.
     * @pre Le squadre 'casa' e 'ospite' devono essere state correttamente inizializzate.
     * @post Il punteggio degli oggetti Squadra viene incrementato in base all'esito del match.
     */
    public void applicaPunti() {

    }
}