package TorneoCalcio5.logica;

import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;

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
        File f = new File(filename);
        if (!f.exists()) return elenco;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] parti = linea.split(";", -1);
                Squadra s = new Squadra();
                s.setNome(parti[0]);
                s.setPunteggio(Integer.parseInt(parti[1]));
                if (parti.length > 2 && !parti[2].isEmpty()) {
                    String[] rosaArray = parti[2].split(",");
                    s.setGiocatori(new ArrayList<>(Arrays.asList(rosaArray)));
                }
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
                pw.println(p.getSquadraCasa().getNome() + ";" + 
                           p.getGolCasa() + ";" + 
                           p.getGolOspite() + ";" + 
                           p.getSquadraOspite().getNome() + ";" + 
                           p.getData());
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
        File f = new File(filename);
        if (!f.exists()) return elenco;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parti = linea.split(";", -1);
                Partita p = new Partita();
                p.setSquadraCasa(squadre.cercaSquadra(parti[0]));
                p.setGolCasa(Integer.parseInt(parti[1]));
                p.setGolOspite(Integer.parseInt(parti[2]));
                p.setSquadraOspite(squadre.cercaSquadra(parti[3]));
                p.setData(LocalDate.parse(parti[4]));              
                elenco.addPartita(p);
            }
        }
        return elenco;
    }
}