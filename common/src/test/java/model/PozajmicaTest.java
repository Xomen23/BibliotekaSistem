package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class PozajmicaTest {

    private Pozajmica pozajmica;
    private Radnik radnik;
    private Clan clan;
    private TipClanstva tip;
    private Date datumPozajmice;
    private Date rokVracanja;

    @BeforeEach
    void setUp() throws Exception {
        tip = new TipClanstva(1, "Standardni", 500.0, 5);
        clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        datumPozajmice = sdf.parse("2026-06-01");
        rokVracanja = sdf.parse("2026-06-15");
        pozajmica = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, pozajmica.getIdPozajmica());
        assertEquals(datumPozajmice, pozajmica.getDatumPozajmice());
        assertEquals(rokVracanja, pozajmica.getRokVracanja());
        assertEquals("Aktivna", pozajmica.getStatus());
        assertEquals("Licno preuzimanje", pozajmica.getNacinPreuzimanja());
        assertEquals(1, pozajmica.getBrojStavki());
        assertEquals(0, pozajmica.getUkupnaKazna());
        assertEquals(radnik, pozajmica.getRadnik());
        assertEquals(clan, pozajmica.getClan());
        assertNotNull(pozajmica.getStavke());
    }

    @Test
    void setDatumPozajmiceBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> pozajmica.setDatumPozajmice(null));
    }

    @Test
    void setDatumPozajmiceSetujePravilno() throws Exception {
        Date noviDatum = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-01");
        pozajmica.setDatumPozajmice(noviDatum);
        assertEquals(noviDatum, pozajmica.getDatumPozajmice());
    }

    @Test
    void setRokVracanjaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> pozajmica.setRokVracanja(null));
    }

    @Test
    void setRokVracanjaSetujePravilno() throws Exception {
        Date noviRok = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-15");
        pozajmica.setRokVracanja(noviRok);
        assertEquals(noviRok, pozajmica.getRokVracanja());
    }

    @Test
    void setStatusBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> pozajmica.setStatus(null));
    }

    @Test
    void setStatusBacaGreskuZaPrazno() {
        assertThrows(Exception.class, () -> pozajmica.setStatus(""));
    }

    @Test
    void setStatusSetujePravilno() throws Exception {
        pozajmica.setStatus("Zatvorena");
        assertEquals("Zatvorena", pozajmica.getStatus());
    }

    @Test
    void setNacinPreuzimanjaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> pozajmica.setNacinPreuzimanja(null));
    }

    @Test
    void setNacinPruzimanjaBacaGreskuZaPrazno() {
        assertThrows(Exception.class, () -> pozajmica.setNacinPreuzimanja(""));
    }

    @Test
    void setNacinPreuzimanjaSetujePravilno() throws Exception {
        pozajmica.setNacinPreuzimanja("Dostava");
        assertEquals("Dostava", pozajmica.getNacinPreuzimanja());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -100})
    void setBrojStavkiBacaGreskuZaNegativno(int broj) {
        assertThrows(Exception.class, () -> pozajmica.setBrojStavki(broj));
    }

    @Test
    void setBrojStavkiSetujePravilno() throws Exception {
        pozajmica.setBrojStavki(3);
        assertEquals(3, pozajmica.getBrojStavki());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -100})
    void setUkupnaKaznaBacaGreskuZaNegativno(int kazna) {
        assertThrows(Exception.class, () -> pozajmica.setUkupnaKazna(kazna));
    }

    @Test
    void setUkupnaKaznaSetujePravilno() throws Exception {
        pozajmica.setUkupnaKazna(200);
        assertEquals(200, pozajmica.getUkupnaKazna());
    }

    @Test
    void setRadnikBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> pozajmica.setRadnik(null));
    }

    @Test
    void setRadnikSetujePravilno() throws Exception {
        Radnik noviRadnik = new Radnik(2, "Ana", "Anic", "0202995654321", "aanic", "0652222222", "sifra");
        pozajmica.setRadnik(noviRadnik);
        assertEquals(noviRadnik, pozajmica.getRadnik());
    }

    @Test
    void setClanBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> pozajmica.setClan(null));
    }

    @Test
    void setClanSetujePravilno() throws Exception {
        Clan noviClan = new Clan(2, "Marko", "Markovic", "marko@mail.com", "0699999999", tip);
        pozajmica.setClan(noviClan);
        assertEquals(noviClan, pozajmica.getClan());
    }

    @Test
    void equalsVracaTrueZaIstiDatumIClan() throws Exception {
        Pozajmica druga = new Pozajmica(99, datumPozajmice, rokVracanja, "Zatvorena", "Dostava", 5, 50, radnik, clan, new ArrayList<>());
        assertEquals(pozajmica, druga);
    }

    @Test
    void equalsVracaFalseZaRazlicitClan() throws Exception {
        Clan drugiClan = new Clan(2, "Marko", "Markovic", "marko@mail.com", "0699999999", tip);
        Pozajmica druga = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, drugiClan, new ArrayList<>());
        assertNotEquals(pozajmica, druga);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, pozajmica);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(pozajmica, pozajmica);
    }

    @Test
    void vratiNazivTabeleVracaPozajmica() {
        assertEquals("pozajmica", pozajmica.vratiNazivTabele());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("pozajmica.idPozajmica=1", pozajmica.vratiPrimarniKljuc());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, idRadnik, idClan", pozajmica.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanjeNijeNull() {
        assertNotNull(pozajmica.vratiVrednostiZaUbacivanje());
        assertFalse(pozajmica.vratiVrednostiZaUbacivanje().isEmpty());
    }

    @Test
    void vratiVrednostiZaIzmenusNijeNull() {
        assertNotNull(pozajmica.vratiVrednostiZaIzmenu());
        assertFalse(pozajmica.vratiVrednostiZaIzmenu().isEmpty());
    }
}
