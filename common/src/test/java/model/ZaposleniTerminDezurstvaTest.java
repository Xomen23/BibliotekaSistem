package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link ZaposleniTerminDezurstva}.
 *
 * @author Petar
 */
class ZaposleniTerminDezurstvaTest {

    private Radnik radnik;
    private TerminDezurstva termin;
    private Date datum;
    private ZaposleniTerminDezurstva zapis;

    @BeforeEach
    void setUp() throws Exception {
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        termin = new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", "Glavna sala");
        datum = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-19");
        zapis = new ZaposleniTerminDezurstva(radnik, termin, datum, true);
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(radnik, zapis.getRadnik());
        assertEquals(termin, zapis.getTerminDezurstva());
        assertEquals(datum, zapis.getDatumDezurstva());
        assertTrue(zapis.isPrisutnost());
    }

    @Test
    void testSeteri() {
        ZaposleniTerminDezurstva z = new ZaposleniTerminDezurstva();
        z.setRadnik(radnik);
        z.setTerminDezurstva(termin);
        z.setDatumDezurstva(datum);
        z.setPrisutnost(false);

        assertEquals(radnik, z.getRadnik());
        assertEquals(termin, z.getTerminDezurstva());
        assertEquals(datum, z.getDatumDezurstva());
        assertFalse(z.isPrisutnost());
    }

    @Test
    void testEqualsIstiRadnikTerminIDatum() {
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(radnik, termin, datum, false);
        assertEquals(zapis, drugi);
    }

    @Test
    void testEqualsRazlicitRadnik() {
        Radnik drugiRadnik = new Radnik(2, "Ana", "Stanojevic", "0202995654321", "astanojevic", "0652222222", "lozinka");
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(drugiRadnik, termin, datum, true);
        assertNotEquals(zapis, drugi);
    }

    @Test
    void testEqualsRazlicitTermin() {
        TerminDezurstva drugiTermin = new TerminDezurstva(2, "Popodne", "Drugi opis", "Magacin");
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(radnik, drugiTermin, datum, true);
        assertNotEquals(zapis, drugi);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, zapis);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("zaposleniTerminDezurstva", zapis.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("idRadnik, idTerminDezurstva, datumDezurstva, prisutnost", zapis.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        String ocekivano = "zaposleniTerminDezurstva.idRadnik=" + radnik.getIdRadnik()
                + " AND zaposleniTerminDezurstva.idTerminDezurstva=" + termin.getIdTerminDezurstva()
                + " AND zaposleniTerminDezurstva.datumDezurstva=" + datum;
        assertEquals(ocekivano, zapis.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        String ocekivano = radnik.getIdRadnik() + ", " + termin.getIdTerminDezurstva() + ", '" + datum + "', true";
        assertEquals(ocekivano, zapis.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        assertEquals("prisutnost= true", zapis.vratiVrednostiZaIzmenu());
    }
}
