package TorneoCalcio5.logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ElencoPartiteTest {

    @Test
    public void testAggiuntaEOrdinamentoCronologico() {
        ElencoPartite storico = new ElencoPartite();
        
        Partita pVecchia = new Partita();
        pVecchia.setData(LocalDate.of(2023, 1, 1));
        
        Partita pRecente = new Partita();
        pRecente.setData(LocalDate.of(2024, 1, 1));
        
        storico.addPartita(pVecchia);
        storico.addPartita(pRecente);
        
        assertEquals(pRecente, storico.getElencoOsservabile().get(0));
    }

    @Test
    public void testRimozionePartita() {
        ElencoPartite storico = new ElencoPartite();
        Partita p = new Partita();
        storico.addPartita(p);
        
        boolean rimosso = storico.removePartita(p);
        assertTrue(rimosso);
        assertEquals(0, storico.getElencoBase().size());
    }
}