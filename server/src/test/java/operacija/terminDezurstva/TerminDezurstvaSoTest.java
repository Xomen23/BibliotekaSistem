package operacija.terminDezurstva;

import model.TerminDezurstva;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad terminom dezurstva.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class TerminDezurstvaSoTest {

    private TerminDezurstva validTermin() {
        return new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", "Glavna sala");
    }

    // ---------- UbaciTerminDezurstvaSO ----------

    @Test
    void ubaciTerminPredusloviProlaziZaValidanTermin() {
        UbaciTerminDezurstvaSO so = new UbaciTerminDezurstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(validTermin()));
    }

    @Test
    void ubaciTerminPredusloviBacaGreskuZaNullObjekat() {
        UbaciTerminDezurstvaSO so = new UbaciTerminDezurstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void ubaciTerminPredusloviBacaGreskuZaPraznuSmenu() {
        UbaciTerminDezurstvaSO so = new UbaciTerminDezurstvaSO();
        TerminDezurstva t = validTermin();
        t.setSmena("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA SMENA", ex.getMessage());
    }

    @Test
    void ubaciTerminPredusloviBacaGreskuZaPrazanOpis() {
        UbaciTerminDezurstvaSO so = new UbaciTerminDezurstvaSO();
        TerminDezurstva t = validTermin();
        t.setOpis(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA OPIS", ex.getMessage());
    }

    @Test
    void ubaciTerminPredusloviBacaGreskuZaPraznuLokaciju() {
        UbaciTerminDezurstvaSO so = new UbaciTerminDezurstvaSO();
        TerminDezurstva t = validTermin();
        t.setLokacija("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA LOKACIJA", ex.getMessage());
    }

    // ---------- AzurirajTerminDezurstvaSO ----------

    @Test
    void azurirajTerminPredusloviProlaziZaValidanTermin() {
        AzurirajTerminDezurstvaSO so = new AzurirajTerminDezurstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(validTermin()));
    }

    @Test
    void azurirajTerminPredusloviBacaGreskuZaNullObjekat() {
        AzurirajTerminDezurstvaSO so = new AzurirajTerminDezurstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void azurirajTerminPredusloviBacaGreskuZaPraznuLokaciju() {
        AzurirajTerminDezurstvaSO so = new AzurirajTerminDezurstvaSO();
        TerminDezurstva t = validTermin();
        t.setLokacija("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(t));
        assertEquals("GRESKA LOKACIJA", ex.getMessage());
    }

    // ---------- ObrisiTerminDezurstvaSO ----------

    @Test
    void obrisiTerminPredusloviProlaziZaValidanTermin() {
        ObrisiTerminDezurstvaSO so = new ObrisiTerminDezurstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(validTermin()));
    }

    @Test
    void obrisiTerminPredusloviBacaGreskuZaNullObjekat() {
        ObrisiTerminDezurstvaSO so = new ObrisiTerminDezurstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void obrisiTerminPredusloviBacaGreskuZaPogresanTip() {
        ObrisiTerminDezurstvaSO so = new ObrisiTerminDezurstvaSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije termin"));
    }

    // ---------- UcitajTerminDezurstvaSO ----------

    @Test
    void ucitajTerminPredusloviProlaziUvek() {
        UcitajTerminDezurstvaSO so = new UcitajTerminDezurstvaSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validTermin()));
    }
}
