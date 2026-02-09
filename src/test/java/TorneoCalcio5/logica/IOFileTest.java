package TorneoCalcio5.logica;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.io.File;

public class IOFileTest {

    @TempDir
    Path tempDir; // Crea una cartella temporanea per i file di test

    @Test
    public void testIntegrazioneSalvaECarica() throws Exception {
        String fileSq = tempDir.resolve("squadre.csv").toString();
        String filePa = tempDir.resolve("partite.csv").toString();

        // 1. Setup dati
        ElencoSquadre sqOriginali = new ElencoSquadre();
        Squadra s1 = new Squadra(); s1.setNome("Milan");
        sqOriginali.addSquadra(s1);

        ElencoPartite paOriginali = new ElencoPartite();
        Partita p = new Partita();
        p.setSquadraCasa(s1);
        p.setSquadraOspite(s1); // Partita di test
        paOriginali.addPartita(p);

        // 2. Esecuzione salvataggio
        IOFile.salvaSquadre(sqOriginali, fileSq);
        IOFile.salvaPartite(paOriginali, filePa);

        // 3. Esecuzione caricamento
        ElencoSquadre sqCaricate = IOFile.caricaSquadre(fileSq);
        ElencoPartite paCaricate = IOFile.caricaPartite(filePa, sqCaricate);

        // 4. Verifiche
        assertEquals(1, sqCaricate.getElencoBase().size());
        assertEquals("Milan", sqCaricate.getElencoBase().get(0).getNome());
        assertEquals(1, paCaricate.getElencoBase().size());
        assertEquals("Milan", paCaricate.getElencoBase().get(0).getSquadraCasa().getNome());
    }
}