package operacija.radnici;

import model.Radnik;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad radnikom biblioteke.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class RadnikSoTest {

    private Radnik validRadnik() {
        return new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
    }

    // ---------- KreirajRadnikaSO ----------

    @Test
    void kreirajRadnikaPredusloviProlaziZaValidnogRadnika() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        assertDoesNotThrow(() -> so.preduslovi(validRadnik()));
    }

    @Test
    void kreirajRadnikaPredusloviBacaGreskuZaNullObjekat() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void kreirajRadnikaPredusloviBacaGreskuZaPraznoIme() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        Radnik r = validRadnik();
        r.setIme("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(r));
        assertEquals("GRESKA IME", ex.getMessage());
    }

    @Test
    void kreirajRadnikaPredusloviBacaGreskuZaJmbgPogresneDuzine() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        Radnik r = validRadnik();
        r.setJmbg("12345");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(r));
        assertEquals("GRESKA JMBG", ex.getMessage());
    }

    @Test
    void kreirajRadnikaPredusloviBacaGreskuZaPraznoKorisnickoIme() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        Radnik r = validRadnik();
        r.setKorisnickoIme("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(r));
        assertEquals("GRESKA KORISNICKO IME", ex.getMessage());
    }

    @Test
    void kreirajRadnikaPredusloviBacaGreskuZaPrazanTelefon() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        Radnik r = validRadnik();
        r.setBrojTelefona(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(r));
        assertEquals("GRESKA BROJ TELEFONA", ex.getMessage());
    }

    @Test
    void kreirajRadnikaPredusloviBacaGreskuZaPraznuSifru() {
        KreirajRadnikaSO so = new KreirajRadnikaSO();
        Radnik r = validRadnik();
        r.setSifra("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(r));
        assertEquals("GRESKA SIFRA", ex.getMessage());
    }

    // ---------- AzurirajRadnikaSO ----------

    @Test
    void azurirajRadnikaPredusloviProlaziZaValidnogRadnika() {
        AzurirajRadnikaSO so = new AzurirajRadnikaSO();
        assertDoesNotThrow(() -> so.preduslovi(validRadnik()));
    }

    @Test
    void azurirajRadnikaPredusloviBacaGreskuZaNullObjekat() {
        AzurirajRadnikaSO so = new AzurirajRadnikaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void azurirajRadnikaPredusloviBacaGreskuZaJmbgPogresneDuzine() {
        AzurirajRadnikaSO so = new AzurirajRadnikaSO();
        Radnik r = validRadnik();
        r.setJmbg("predugackijmbgzatest");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(r));
        assertEquals("GRESKA JMBG", ex.getMessage());
    }

    // ---------- ObrisiRadnikaSO ----------

    @Test
    void obrisiRadnikaPredusloviProlaziZaValidnogRadnika() {
        ObrisiRadnikaSO so = new ObrisiRadnikaSO();
        assertDoesNotThrow(() -> so.preduslovi(validRadnik()));
    }

    @Test
    void obrisiRadnikaPredusloviBacaGreskuZaNullObjekat() {
        ObrisiRadnikaSO so = new ObrisiRadnikaSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void obrisiRadnikaPredusloviBacaGreskuZaPogresanTip() {
        ObrisiRadnikaSO so = new ObrisiRadnikaSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije radnik"));
    }

    // ---------- UcitajRadnikeSO ----------

    @Test
    void ucitajRadnikePredusloviProlaziUvek() {
        UcitajRadnikeSO so = new UcitajRadnikeSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validRadnik()));
    }
}
