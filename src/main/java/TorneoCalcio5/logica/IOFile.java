package TorneoCalcio5.logica;

import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

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
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Squadra s : elenco.getElencoBase()) {
                pw.println(s.getNome() + ";" + s.getPunteggio() + ";" + String.join(",", s.getGiocatori()));
            }
        }
    }

    /**
     * @brief Carica le squadre da file.
     * @param[in] filename Il nome del file.
     * @return L'elenco caricato.
     */
    public static ElencoSquadre caricaSquadre(String filename) throws Exception {
        ElencoSquadre elenco = new ElencoSquadre();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parti = linea.split(";");
                Squadra s = new Squadra();
                s.setNome(parti[0]);
                s.setPunteggio(Integer.parseInt(parti[1]));
                if (parti.length > 2) s.setGiocatori(Arrays.asList(parti[2].split(",")));
                elenco.addSquadra(s);
            }
        }
        return elenco;
    }

    /**
     * @brief Salva lo storico delle partite su file.
     * @param[in] elenco L'elenco delle partite da salvare.
     * @param[in] filename Il nome del file.
     * @pre L'oggetto @p elenco non deve essere nullo.
     * @post Il file viene sovrascritto con i dati aggiornati.
     */
    public static void salvaPartite(ElencoPartite elenco, String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Partita p : elenco.getElencoBase()) {
                pw.println(p.getSquadraCasa().getNome() + ";" + p.getGolCasa() + ";" + 
                           p.getGolOspite() + ";" + p.getSquadraOspite().getNome() + ";" + p.getData());
            }
        }
    }

    /**
     * @brief Carica lo storico delle partite da file.
     * @param[in] filename Il nome del file.
     * @param[in] squadre L'elenco squadre per collegare i nomi agli oggetti reali.
     * @return L'elenco delle partite caricato.
     */
    public static ElencoPartite caricaPartite(String filename, ElencoSquadre squadre) throws Exception {
        ElencoPartite elenco = new ElencoPartite();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parti = linea.split(";");
                Partita p = new Partita();
                for (Squadra s : squadre.getElencoBase()) {
                    if (s.getNome().equals(parti[0])) p.setSquadraCasa(s);
                    if (s.getNome().equals(parti[3])) p.setSquadraOspite(s);
                }
                p.setGolCasa(Integer.parseInt(parti[1]));
                p.setGolOspite(Integer.parseInt(parti[2]));
                p.setData(LocalDate.parse(parti[4]));
                elenco.addPartita(p);
            }
        }
        return elenco;
    }
}