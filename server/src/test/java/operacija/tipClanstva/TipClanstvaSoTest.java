package operacija.tipClanstva;

import model.TipClanstva;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad tipom clanstva.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class TipClanstvaSoTest {

    private TipClanstva validTip() {
        return new TipClanstva(1, "Standardni", 500.0, 5);
    }

    // ---------- KreirajTipClanstvaSO ----------

    @Test
    void kreirajTipPredusloviProlaziZaValidanTip() {
        KreirajTipClanstvaSO so = new KreirajTipClanstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(validTip()));
    }

    @Test
    void kreirajTipPredusloviBacaGreskuZaNullObjekat() {
        KreirajTipClanstvaSO so = new KreirajTipClanstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void kreirajTipPredusloviBacaGreskuZaPrazanNazivTipa() {
        KreirajTipClanstvaSO so = new KreirajTipClanstvaSO();
        TipClanstva t = validTip();
        t.setTip("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA TIP", ex.getMessage());
    }

    @Test
    void kreirajTipPredusloviBacaGreskuZaNegativnuCenu() {
        KreirajTipClanstvaSO so = new KreirajTipClanstvaSO();
        TipClanstva t = validTip();
        t.setCena(-10);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA CENA", ex.getMessage());
    }

    @Test
    void kreirajTipPredusloviBacaGreskuZaNulaMaksimalanBrojStavki() {
        KreirajTipClanstvaSO so = new KreirajTipClanstvaSO();
        TipClanstva t = validTip();
        t.setMaksimalanBrojStavki(0);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA MaxBrStavki", ex.getMessage());
    }

    // ---------- AzurirajTipClanstvaSO ----------

    @Test
    void azurirajTipPredusloviProlaziZaValidanTip() {
        AzurirajTipClanstvaSO so = new AzurirajTipClanstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(validTip()));
    }

    @Test
    void azurirajTipPredusloviBacaGreskuZaNegativnuCenu() {
        AzurirajTipClanstvaSO so = new AzurirajTipClanstvaSO();
        TipClanstva t = validTip();
        t.setCena(0);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA CENA", ex.getMessage());
    }

    @Test
    void azurirajTipPredusloviBacaGreskuZaNullObjekat() {
        AzurirajTipClanstvaSO so = new AzurirajTipClanstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    // ---------- ObrisiTipClanstvaSO ----------

    @Test
    void obrisiTipPredusloviProlaziZaValidanTip() {
        ObrisiTipClanstvaSO so = new ObrisiTipClanstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(validTip()));
    }

    @Test
    void obrisiTipPredusloviBacaGreskuZaNullObjekat() {
        ObrisiTipClanstvaSO so = new ObrisiTipClanstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void obrisiTipPredusloviBacaGreskuZaPogresanTip() {
        ObrisiTipClanstvaSO so = new ObrisiTipClanstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije tip clanstva"));
    }

    // ---------- UcitajTipClanstvaSO ----------

    @Test
    void ucitajTipPredusloviProlaziUvek() {
        UcitajTipClanstvaSO so = new UcitajTipClanstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validTip()));
    }
}
