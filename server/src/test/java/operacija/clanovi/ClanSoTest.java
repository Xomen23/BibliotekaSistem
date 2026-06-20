package operacija.clanovi;

import model.Clan;
import model.TipClanstva;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad clanom biblioteke.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 * Testovi se nalaze u istom paketu kao i testirane klase, sto omogucava
 * direktan pristup protected metodi preduslovi.
 *
 * @author Petar
 */
class ClanSoTest {

    private TipClanstva validTip() {
        return new TipClanstva(1, "Standardni", 500.0, 5);
    }

    private Clan validClan() {
        return new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", validTip());
    }

    // ---------- KreirajClanaSO ----------

    @Test
    void kreirajClanaPredusloviProlaziZaValidnogClana() {
        KreirajClanaSO so = new KreirajClanaSO();
        assertDoesNotThrow(() -> so.preduslovi(validClan()));
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaNullObjekat() {
        KreirajClanaSO so = new KreirajClanaSO();
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(null));
        assertTrue(ex.getMessage().contains("clana"));
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaPogresanTip() {
        KreirajClanaSO so = new KreirajClanaSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije clan"));
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaPraznoIme() {
        KreirajClanaSO so = new KreirajClanaSO();
        Clan c = validClan();
        c.setIme("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(c));
        assertEquals("GRESKA IME", ex.getMessage());
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaPraznoPrezime() {
        KreirajClanaSO so = new KreirajClanaSO();
        Clan c = validClan();
        c.setPrezime(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(c));
        assertEquals("GRESKA PREZIME", ex.getMessage());
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaNevalidanEmail() {
        KreirajClanaSO so = new KreirajClanaSO();
        Clan c = validClan();
        c.setEmail("nevalidanEmail");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(c));
        assertEquals("GRESKA EMAIL", ex.getMessage());
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaPrazanTelefon() {
        KreirajClanaSO so = new KreirajClanaSO();
        Clan c = validClan();
        c.setBrojTelefona("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(c));
        assertEquals("GRESKA BROJ TELEFONA", ex.getMessage());
    }

    @Test
    void kreirajClanaPredusloviBacaGreskuZaNedostajuciTipClanstva() {
        KreirajClanaSO so = new KreirajClanaSO();
        Clan c = validClan();
        c.setTipClanstva(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(c));
        assertEquals("GRESKA TIP CLANSTVA", ex.getMessage());
    }

    // ---------- AzurirajClanaSO ----------

    @Test
    void azurirajClanaPredusloviProlaziZaValidnogClana() {
        AzurirajClanaSO so = new AzurirajClanaSO();
        assertDoesNotThrow(() -> so.preduslovi(validClan()));
    }

    @Test
    void azurirajClanaPredusloviBacaGreskuZaNullObjekat() {
        AzurirajClanaSO so = new AzurirajClanaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void azurirajClanaPredusloviBacaGreskuZaNevalidanEmail() {
        AzurirajClanaSO so = new AzurirajClanaSO();
        Clan c = validClan();
        c.setEmail("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(c));
        assertEquals("GRESKA EMAIL", ex.getMessage());
    }

    // ---------- ObrisiClanaSO ----------

    @Test
    void obrisiClanaPredusloviProlaziZaValidnogClana() {
        ObrisiClanaSO so = new ObrisiClanaSO();
        assertDoesNotThrow(() -> so.preduslovi(validClan()));
    }

    @Test
    void obrisiClanaPredusloviBacaGreskuZaNullObjekat() {
        ObrisiClanaSO so = new ObrisiClanaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void obrisiClanaPredusloviBacaGreskuZaPogresanTip() {
        ObrisiClanaSO so = new ObrisiClanaSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije clan"));
    }

    // ---------- UcitajClanaSO ----------

    @Test
    void ucitajClanaPredusloviProlaziUvek() {
        UcitajClanaSO so = new UcitajClanaSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validClan()));
    }
}
