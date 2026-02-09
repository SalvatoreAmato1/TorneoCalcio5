package TorneoCalcio5.logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartitaTest {
    private Squadra casa;
    private Squadra ospite;
    private Partita p;

    @BeforeEach
    public void setUp() {
        casa = new Squadra();
        casa.setNome("Team A");
        ospite = new Squadra();
        ospite.setNome("Team B");
        p = new Partita();
        p.setSquadraCasa(casa);
        p.setSquadraOspite(ospite);
    }

    @Test
    public void testApplicaPuntiVittoriaCasa() {
        p.setGolCasa(3);
        p.setGolOspite(1);
        p.applicaPunti();
        assertEquals(3, casa.getPunteggio());
        assertEquals(0, ospite.getPunteggio());
    }

    @Test
    public void testApplicaPuntiPareggio() {
        p.setGolCasa(2);
        p.setGolOspite(2);
        p.applicaPunti();
        assertEquals(1, casa.getPunteggio());
        assertEquals(1, ospite.getPunteggio());
    }

    @Test
    public void testCasoLimiteSquadreNulle() {
        Partita pNull = new Partita();
        assertDoesNotThrow(() -> pNull.applicaPunti());
    }
}