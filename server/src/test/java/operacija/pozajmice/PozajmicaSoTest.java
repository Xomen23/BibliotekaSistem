package operacija.pozajmice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Clan;
import model.Pozajmica;
import model.Radnik;
import model.TipClanstva;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad pozajmicom.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class PozajmicaSoTest {

    private Pozajmica validPozajmica() throws Exception {
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        Clan clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        Radnik radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datumPozajmice = sdf.parse("2026-06-01");
        Date rokVracanja = sdf.parse("2026-06-15");
        return new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
    }

    // ---------- KreirajPozajmicuSO ----------

    @Test
    void kreirajPozajmicuPredusloviProlaziZaValidnuPozajmicu() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        assertDoesNotThrow(() -> so.preduslovi(validPozajmica()));
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNullObjekat() {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNedostajuciDatumPozajmice() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setDatumPozajmice(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA DATUM POZAJMICE", ex.getMessage());
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNedostajuciRokVracanja() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setRokVracanja(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA ROK VRACANJA", ex.getMessage());
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaPrazanStatus() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setStatus("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA STATUS", ex.getMessage());
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNegativanBrojStavki() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setBrojStavki(-1);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA BROJ STAVKI", ex.getMessage());
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNegativnuKaznu() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setUkupnaKazna(-5);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA UKUPNA KAZNA", ex.getMessage());
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNedostajucegClana() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setClan(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA CLAN", ex.getMessage());
    }

    @Test
    void kreirajPozajmicuPredusloviBacaGreskuZaNedostajucegRadnika() throws Exception {
        KreirajPozajmicuSO so = new KreirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setRadnik(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA RADNIK", ex.getMessage());
    }

    // ---------- AzurirajPozajmicuSO ----------

    @Test
    void azurirajPozajmicuPredusloviProlaziZaValidnuPozajmicu() throws Exception {
        AzurirajPozajmicuSO so = new AzurirajPozajmicuSO();
        assertDoesNotThrow(() -> so.preduslovi(validPozajmica()));
    }

    @Test
    void azurirajPozajmicuPredusloviBacaGreskuZaPrazanNacinPreuzimanja() throws Exception {
        AzurirajPozajmicuSO so = new AzurirajPozajmicuSO();
        Pozajmica p = validPozajmica();
        p.setNacinPreuzimanja("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(p));
        assertEquals("GRESKA NACIN PREUZIMANJA", ex.getMessage());
    }

    @Test
    void azurirajPozajmicuPredusloviBacaGreskuZaNullObjekat() {
        AzurirajPozajmicuSO so = new AzurirajPozajmicuSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    // ---------- UcitajPozajmiceSO ----------

    @Test
    void ucitajPozajmicePredusloviProlaziUvek() throws Exception {
        UcitajPozajmiceSO so = new UcitajPozajmiceSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validPozajmica()));
    }
}
