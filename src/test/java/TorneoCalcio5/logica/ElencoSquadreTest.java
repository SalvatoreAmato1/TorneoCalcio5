package TorneoCalcio5.logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElencoSquadreTest {

    @Test
    public void testOrdinamentoClassifica() {
        ElencoSquadre elenco = new ElencoSquadre();
        Squadra s1 = new Squadra(); s1.setNome("Squadra B"); s1.setPunteggio(10);
        Squadra s2 = new Squadra(); s2.setNome("Squadra A"); s2.setPunteggio(20);
        
        elenco.addSquadra(s1);
        elenco.addSquadra(s2);
        
        // La prima squadra nell'elenco ordinato deve essere quella con più punti (s2)
        assertEquals("Squadra A", elenco.getElencoOrdinato().get(0).getNome());
    }

    @Test
    public void testCercaSquadraCaseInsensitive() {
        ElencoSquadre elenco = new ElencoSquadre();
        Squadra s = new Squadra(); s.setNome("Napoli");
        elenco.addSquadra(s);
        
        assertNotNull(elenco.cercaSquadra("napoli"));
        assertNotNull(elenco.cercaSquadra("NAPOLI"));
        assertNull(elenco.cercaSquadra("Roma"));
    }
}