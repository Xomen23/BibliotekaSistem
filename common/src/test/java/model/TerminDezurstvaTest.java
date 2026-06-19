package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link TerminDezurstva}.
 *
 * @author Petar
 */
class TerminDezurstvaTest {

    private TerminDezurstva termin;

    @BeforeEach
    void setUp() {
        termin = new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", "Glavna sala");
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, termin.getIdTerminDezurstva());
        assertEquals("Prepodne", termin.getSmena());
        assertEquals("Dezurstvo na pultu", termin.getOpis());
        assertEquals("Glavna sala", termin.getLokacija());
    }

    @Test
    void testSeteri() {
        TerminDezurstva t = new TerminDezurstva();
        t.setIdTerminDezurstva(2);
        t.setSmena("Popodne");
        t.setOpis("Razvrstavanje knjiga");
        t.setLokacija("Magacin");

        assertEquals(2, t.getIdTerminDezurstva());
        assertEquals("Popodne", t.getSmena());
        assertEquals("Razvrstavanje knjiga", t.getOpis());
        assertEquals("Magacin", t.getLokacija());
    }

    @Test
    void testToString() {
        assertEquals("TerminDezurstva{smena=Prepodne, lokacija=Glavna sala}", termin.toString());
    }

    @Test
    void testEqualsIstaSmenaILokacija() {
        TerminDezurstva drugi = new TerminDezurstva(99, "Prepodne", "Drugi opis", "Glavna sala");
        assertEquals(termin, drugi);
    }

    @Test
    void testEqualsRazlicitaSmena() {
        TerminDezurstva drugi = new TerminDezurstva(1, "Popodne", "Dezurstvo na pultu", "Glavna sala");
        assertNotEquals(termin, drugi);
    }

    @Test
    void testEqualsRazlicitaLokacija() {
        TerminDezurstva drugi = new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", "Magacin");
        assertNotEquals(termin, drugi);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, termin);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("terminDezurstva", termin.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("smena, opis, lokacija", termin.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        assertEquals("'Prepodne', 'Dezurstvo na pultu', 'Glavna sala'", termin.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("terminDezurstva.idTerminDezurstva=1", termin.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        String ocekivano = "smena='Prepodne', opis='Dezurstvo na pultu', lokacija='Glavna sala'";
        assertEquals(ocekivano, termin.vratiVrednostiZaIzmenu());
    }
}
