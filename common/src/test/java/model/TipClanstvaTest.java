package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link TipClanstva}.
 *
 * @author Petar
 */
class TipClanstvaTest {

    private TipClanstva tip;

    @BeforeEach
    void setUp() {
        tip = new TipClanstva(1, "Standardni", 500.0, 5);
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, tip.getIdTipClanstva());
        assertEquals("Standardni", tip.getTip());
        assertEquals(500.0, tip.getCena());
        assertEquals(5, tip.getMaksimalanBrojStavki());
    }

    @Test
    void testSeteri() {
        TipClanstva t = new TipClanstva();
        t.setIdTipClanstva(2);
        t.setTip("Premium");
        t.setCena(1500.0);
        t.setMaksimalanBrojStavki(10);

        assertEquals(2, t.getIdTipClanstva());
        assertEquals("Premium", t.getTip());
        assertEquals(1500.0, t.getCena());
        assertEquals(10, t.getMaksimalanBrojStavki());
    }

    @Test
    void testToString() {
        assertEquals("Standardni", tip.toString());
    }

    @Test
    void testEqualsIstiTip() {
        TipClanstva drugi = new TipClanstva(99, "Standardni", 999.0, 99);
        assertEquals(tip, drugi);
    }

    @Test
    void testEqualsRazlicitTip() {
        TipClanstva drugi = new TipClanstva(1, "Premium", 500.0, 5);
        assertNotEquals(tip, drugi);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, tip);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("tipClanstva", tip.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("tip, cena, maksimalanBrojStavki", tip.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        assertEquals("'Standardni', 500.0, 5", tip.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("tipClanstva.idTipClanstva=1", tip.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        assertEquals("tip='Standardni', cena=500.0, maksimalanBrojStavki=5", tip.vratiVrednostiZaIzmenu());
    }
}
