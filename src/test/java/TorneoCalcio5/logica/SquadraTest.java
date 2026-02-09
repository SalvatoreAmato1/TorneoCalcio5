package TorneoCalcio5.logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class SquadraTest {

    @Test
    public void testInizializzazioneDefault() {
        Squadra s = new Squadra();
        assertEquals("", s.getNome());
        assertEquals(0, s.getPunteggio());
        assertTrue(s.getGiocatori().isEmpty());
    }

    @Test
    public void testAggiuntaPunti() {
        Squadra s = new Squadra();
        s.aggiungiPunti(3);
        assertEquals(3, s.getPunteggio());
        s.aggiungiPunti(1);
        assertEquals(4, s.getPunteggio());
    }

    @Test
    public void testSetRosaGiocatori() {
        Squadra s = new Squadra();
        List<String> rosa = Arrays.asList("Mario;Rossi;1990-01-01", "Luigi;Verdi;1992-05-10");
        s.setGiocatori(rosa);
        assertEquals(2, s.getGiocatori().size());
        assertEquals("Mario;Rossi;1990-01-01", s.getGiocatori().get(0));
    }
}