package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class StavkaPozajmiceTest {

    private StavkaPozajmice stavka;
    private Pozajmica pozajmica;
    private Knjiga knjiga;

    @BeforeEach
    void setUp() throws Exception {
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        Clan clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        Radnik radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        Date datumPozajmice = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-01");
        Date rokVracanja = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-15");
        pozajmica = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
        knjiga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
        stavka = new StavkaPozajmice(1, pozajmica, null, false, 0, knjiga);
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, stavka.getRb());
        assertEquals(pozajmica, stavka.getPozajmica());
        assertNull(stavka.getDatumVracanja());
        assertFalse(stavka.isVraceno());
        assertEquals(0, stavka.getKazna());
        assertEquals(knjiga, stavka.getKnjiga());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void setRbBacaGreskuZaNultuIliNegativnu(int rb) {
        assertThrows(Exception.class, () -> stavka.setRb(rb));
    }

    @Test
    void setRbSetujePravilno() throws Exception {
        stavka.setRb(3);
        assertEquals(3, stavka.getRb());
    }

    @Test
    void setPozajmicaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> stavka.setPozajmica(null));
    }

    @Test
    void setPozajmicaSetujePravilno() throws Exception {
        stavka.setPozajmica(pozajmica);
        assertEquals(pozajmica, stavka.getPozajmica());
    }

    @Test
    void setDatumVracanjaSetujePravilnoSaNull() {
        stavka.setDatumVracanja(null);
        assertNull(stavka.getDatumVracanja());
    }

    @Test
    void setDatumVracanjaSetujePravilnoSaDatumom() throws Exception {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-20");
        stavka.setDatumVracanja(datum);
        assertEquals(datum, stavka.getDatumVracanja());
    }

    @Test
    void setVracenoSetujePravilno() {
        stavka.setVraceno(true);
        assertTrue(stavka.isVraceno());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -100})
    void setKaznaBacaGreskuZaNegativnu(int kazna) {
        assertThrows(Exception.class, () -> stavka.setKazna(kazna));
    }

    @Test
    void setKaznaSetujePravilno() throws Exception {
        stavka.setKazna(100);
        assertEquals(100, stavka.getKazna());
    }

    @Test
    void setKnjigaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> stavka.setKnjiga(null));
    }

    @Test
    void setKnjigaSetujePravilno() throws Exception {
        Knjiga novaKnjiga = new Knjiga(2, "Prokleta avlija", "Ivo Andric", "9780000000002", "Novela");
        stavka.setKnjiga(novaKnjiga);
        assertEquals(novaKnjiga, stavka.getKnjiga());
    }

    @Test
    void equalsVracaTrueZaIstiRbIPozajmicu() throws Exception {
        StavkaPozajmice druga = new StavkaPozajmice(1, pozajmica, null, true, 100, knjiga);
        assertEquals(stavka, druga);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    void equalsVracaFalseZaRazlicitRb(int rb) throws Exception {
        StavkaPozajmice druga = new StavkaPozajmice(rb, pozajmica, null, false, 0, knjiga);
        assertNotEquals(stavka, druga);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, stavka);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(stavka, stavka);
    }

    @Test
    void vratiNazivTabeleVracaStavkaPozajmice() {
        assertEquals("stavkaPozajmice", stavka.vratiNazivTabele());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("stavkaPozajmice.idPozajmica=1 AND stavkaPozajmice.rb=1", stavka.vratiPrimarniKljuc());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("idPozajmica, rb, datumVracanja, vraceno, kazna, idKnjiga", stavka.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanjeKadaNijeVraceno() {
        String rezultat = stavka.vratiVrednostiZaUbacivanje();
        assertTrue(rezultat.contains("NULL"));
        assertTrue(rezultat.contains(String.valueOf(knjiga.getIdKnjiga())));
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        String rezultat = stavka.vratiVrednostiZaIzmenu();
        assertTrue(rezultat.contains("kazna=0"));
        assertTrue(rezultat.contains("idknjiga=" + knjiga.getIdKnjiga()));
    }
}
