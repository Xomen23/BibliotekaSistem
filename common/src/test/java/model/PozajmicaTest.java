package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link Pozajmica}.
 *
 * @author Petar
 */
class PozajmicaTest {

    private TipClanstva tip;
    private Clan clan;
    private Radnik radnik;
    private Date datumPozajmice;
    private Date rokVracanja;
    private Pozajmica pozajmica;

    @BeforeEach
    void setUp() throws Exception {
        tip = new TipClanstva(1, "Standardni", 500.0, 5);
        clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        datumPozajmice = sdf.parse("2026-06-01");
        rokVracanja = sdf.parse("2026-06-15");

        pozajmica = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 2, 0, radnik, clan, new ArrayList<>());
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, pozajmica.getIdPozajmica());
        assertEquals(datumPozajmice, pozajmica.getDatumPozajmice());
        assertEquals(rokVracanja, pozajmica.getRokVracanja());
        assertEquals("Aktivna", pozajmica.getStatus());
        assertEquals("Licno preuzimanje", pozajmica.getNacinPreuzimanja());
        assertEquals(2, pozajmica.getBrojStavki());
        assertEquals(0, pozajmica.getUkupnaKazna());
        assertEquals(radnik, pozajmica.getRadnik());
        assertEquals(clan, pozajmica.getClan());
        assertNotNull(pozajmica.getStavke());
    }

    @Test
    void testSeteri() {
        Pozajmica p = new Pozajmica();
        List<StavkaPozajmice> stavke = new ArrayList<>();
        p.setIdPozajmica(2);
        p.setDatumPozajmice(datumPozajmice);
        p.setRokVracanja(rokVracanja);
        p.setStatus("Zatvorena");
        p.setNacinPreuzimanja("Dostava");
        p.setBrojStavki(1);
        p.setUkupnaKazna(100);
        p.setRadnik(radnik);
        p.setClan(clan);
        p.setStavke(stavke);

        assertEquals(2, p.getIdPozajmica());
        assertEquals("Zatvorena", p.getStatus());
        assertEquals("Dostava", p.getNacinPreuzimanja());
        assertEquals(1, p.getBrojStavki());
        assertEquals(100, p.getUkupnaKazna());
        assertEquals(radnik, p.getRadnik());
        assertEquals(clan, p.getClan());
        assertEquals(stavke, p.getStavke());
    }

    @Test
    void testEqualsIstiDatumIClan() {
        Pozajmica druga = new Pozajmica(99, datumPozajmice, rokVracanja, "Zatvorena", "Dostava", 5, 50, radnik, clan, new ArrayList<>());
        assertEquals(pozajmica, druga);
    }

    @Test
    void testEqualsRazlicitClan() throws Exception {
        Clan drugiClan = new Clan(2, "Marko", "Markovic", "marko@gmail.com", "0699999999", tip);
        Pozajmica druga = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 2, 0, radnik, drugiClan, new ArrayList<>());
        assertNotEquals(pozajmica, druga);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, pozajmica);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("pozajmica", pozajmica.vratiNazivTabele());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("pozajmica.idPozajmica=1", pozajmica.vratiPrimarniKljuc());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        String ocekivano = "datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, idRadnik, idClan";
        assertEquals(ocekivano, pozajmica.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanjeSadrziIdRadnikaIClana() {
        String rezultat = pozajmica.vratiVrednostiZaUbacivanje();
        assertTrue(rezultat.contains("Aktivna"));
        assertTrue(rezultat.contains("Licno preuzimanje"));
        assertTrue(rezultat.endsWith(radnik.getIdRadnik() + ", " + clan.getIdClan()));
    }

    @Test
    void testVratiVrednostiZaIzmenuSadrziPodatke() {
        String rezultat = pozajmica.vratiVrednostiZaIzmenu();
        assertTrue(rezultat.contains("status='Aktivna'"));
        assertTrue(rezultat.contains("brojStavki=2"));
        assertTrue(rezultat.contains("idRadnik=" + radnik.getIdRadnik()));
        assertTrue(rezultat.contains("idClan=" + clan.getIdClan()));
    }
}
