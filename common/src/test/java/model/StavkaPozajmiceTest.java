package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link StavkaPozajmice}.
 *
 * @author Petar
 */
class StavkaPozajmiceTest {

    private Pozajmica pozajmica;
    private Knjiga knjiga;
    private StavkaPozajmice stavka;

    @BeforeEach
    void setUp() throws Exception {
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        Clan clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        Radnik radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datumPozajmice = sdf.parse("2026-06-01");
        Date rokVracanja = sdf.parse("2026-06-15");

        pozajmica = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
        knjiga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "978-86-7102-303-3", "Roman");

        stavka = new StavkaPozajmice(1, pozajmica, null, false, 0, knjiga);
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, stavka.getRb());
        assertEquals(pozajmica, stavka.getPozajmica());
        assertNull(stavka.getDatumVracanja());
        assertFalse(stavka.isVraceno());
        assertEquals(0, stavka.getKazna());
        assertEquals(knjiga, stavka.getKnjiga());
    }

    @Test
    void testSeteri() throws Exception {
        StavkaPozajmice s = new StavkaPozajmice();
        Date datumVracanja = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-20");
        s.setRb(2);
        s.setPozajmica(pozajmica);
        s.setDatumVracanja(datumVracanja);
        s.setVraceno(true);
        s.setKazna(50);
        s.setKnjiga(knjiga);

        assertEquals(2, s.getRb());
        assertEquals(datumVracanja, s.getDatumVracanja());
        assertTrue(s.isVraceno());
        assertEquals(50, s.getKazna());
    }

    @Test
    void testEqualsIstiRbIPozajmica() {
        StavkaPozajmice druga = new StavkaPozajmice(1, pozajmica, null, true, 100, knjiga);
        assertEquals(stavka, druga);
    }

    @Test
    void testEqualsRazlicitRb() {
        StavkaPozajmice druga = new StavkaPozajmice(2, pozajmica, null, false, 0, knjiga);
        assertNotEquals(stavka, druga);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, stavka);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("stavkaPozajmice", stavka.vratiNazivTabele());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("stavkaPozajmice.idPozajmica=1 AND stavkaPozajmice.rb=1", stavka.vratiPrimarniKljuc());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("idPozajmica, rb, datumVracanja, vraceno, kazna, idKnjiga", stavka.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanjeKadaNijeVraceno() {
        String ocekivano = pozajmica.getIdPozajmica() + ", 1, NULL, 0, 0, " + knjiga.getIdKnjiga();
        assertEquals(ocekivano, stavka.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanjeKadaJesteVraceno() throws Exception {
        Date datumVracanja = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-10");
        StavkaPozajmice vracena = new StavkaPozajmice(1, pozajmica, datumVracanja, true, 0, knjiga);
        String rezultat = vracena.vratiVrednostiZaUbacivanje();
        assertTrue(rezultat.contains("2026-06-10"));
        assertTrue(rezultat.contains(", 1, "));
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        String rezultat = stavka.vratiVrednostiZaIzmenu();
        assertTrue(rezultat.contains("vraceno= 0"));
        assertTrue(rezultat.contains("kazna=0"));
        assertTrue(rezultat.contains("idknjiga=" + knjiga.getIdKnjiga()));
    }
}
