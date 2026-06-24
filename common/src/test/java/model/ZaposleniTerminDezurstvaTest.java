package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ZaposleniTerminDezurstvaTest {

    private ZaposleniTerminDezurstva zapis;
    private Radnik radnik;
    private TerminDezurstva termin;
    private Date datum;

    @BeforeEach
    void setUp() throws Exception {
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        termin = new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", "Glavna sala");
        datum = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-19");
        zapis = new ZaposleniTerminDezurstva(radnik, termin, datum, true);
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(radnik, zapis.getRadnik());
        assertEquals(termin, zapis.getTerminDezurstva());
        assertEquals(datum, zapis.getDatumDezurstva());
        assertTrue(zapis.isPrisutnost());
    }

    @Test
    void setRadnikBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> zapis.setRadnik(null));
    }

    @Test
    void setRadnikSetujePravilno() throws Exception {
        Radnik noviRadnik = new Radnik(2, "Ana", "Anic", "0202995654321", "aanic", "0652222222", "sifra");
        zapis.setRadnik(noviRadnik);
        assertEquals(noviRadnik, zapis.getRadnik());
    }

    @Test
    void setTerminDezurstvaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> zapis.setTerminDezurstva(null));
    }

    @Test
    void setTerminDezurstvaSetujePravilno() throws Exception {
        TerminDezurstva noviTermin = new TerminDezurstva(2, "Popodne", "Razvrstavanje", "Magacin");
        zapis.setTerminDezurstva(noviTermin);
        assertEquals(noviTermin, zapis.getTerminDezurstva());
    }

    @Test
    void setDatumDezurstvaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> zapis.setDatumDezurstva(null));
    }

    @Test
    void setDatumDezurstvaSetujePravilno() throws Exception {
        Date noviDatum = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-01");
        zapis.setDatumDezurstva(noviDatum);
        assertEquals(noviDatum, zapis.getDatumDezurstva());
    }

    @Test
    void setPrisutnostSetujePravilno() {
        zapis.setPrisutnost(false);
        assertFalse(zapis.isPrisutnost());
    }

    @Test
    void equalsVracaTrueZaIstiRadnikTerminIDatum() throws Exception {
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(radnik, termin, datum, false);
        assertEquals(zapis, drugi);
    }

    @Test
    void equalsVracaFalseZaRazlicitRadnik() throws Exception {
        Radnik drugiRadnik = new Radnik(2, "Ana", "Anic", "0202995654321", "aanic", "0652222222", "sifra");
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(drugiRadnik, termin, datum, true);
        assertNotEquals(zapis, drugi);
    }

    @Test
    void equalsVracaFalseZaRazlicitTermin() throws Exception {
        TerminDezurstva drugiTermin = new TerminDezurstva(2, "Popodne", "Opis", "Magacin");
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(radnik, drugiTermin, datum, true);
        assertNotEquals(zapis, drugi);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2026-07-01", "2026-08-01", "2026-09-01"})
    void equalsVracaFalseZaRazlicitDatum(String datumStr) throws Exception {
        Date drugiDatum = new SimpleDateFormat("yyyy-MM-dd").parse(datumStr);
        ZaposleniTerminDezurstva drugi = new ZaposleniTerminDezurstva(radnik, termin, drugiDatum, true);
        assertNotEquals(zapis, drugi);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, zapis);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(zapis, zapis);
    }

    @Test
    void vratiNazivTabeleVracaZaposleniTerminDezurstva() {
        assertEquals("zaposleniTerminDezurstva", zapis.vratiNazivTabele());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("idRadnik, idTerminDezurstva, datumDezurstva, prisutnost", zapis.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanje() {
        String ocekivano = radnik.getIdRadnik() + ", " + termin.getIdTerminDezurstva() + ", '" + datum + "', true";
        assertEquals(ocekivano, zapis.vratiVrednostiZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        assertEquals("prisutnost= true", zapis.vratiVrednostiZaIzmenu());
    }
}
