package TorneoCalcio5.logica;

import java.io.IOException;

/**
 * @file IOFile.java
 * @brief Gestione Input/Output su file CSV.
 */
public class IOFile {

    /**
     * @brief Salva le squadre su file.
     * @param[in] elenco L'elenco da salvare.
     * @param[in] filename Il nome del file.
     */
    public static void salvaSquadre(ElencoSquadre elenco, String filename) throws IOException {
    }

    /**
     * @brief Carica le squadre da file.
     * @param[in] filename Il nome del file.
     * @return L'elenco caricato.
     */
    public static ElencoSquadre caricaSquadre(String filename) throws Exception {
        return null;
    }

    /**
     * @brief Salva lo storico delle partite su file.
     * @param[in] elenco L'elenco delle partite da salvare.
     * @param[in] filename Il nome del file.
     * @pre L'oggetto @p elenco non deve essere nullo.
     * @post Il file viene sovrascritto con i dati aggiornati.
     */
    public static void salvaPartite(ElencoPartite elenco, String filename) throws IOException {
    }

    /**
     * @brief Carica lo storico delle partite da file.
     * @param[in] filename Il nome del file.
     * @param[in] squadre L'elenco squadre per collegare i nomi agli oggetti reali.
     * @return L'elenco delle partite caricato.
     */
    public static ElencoPartite caricaPartite(String filename, ElencoSquadre squadre) throws Exception {
        return null;
    }
}