package operacija.knjige;

import model.Knjiga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad knjigom u fondu biblioteke.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class KnjigaSoTest {

    private Knjiga validKnjiga() {
        return new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
    }

    // ---------- KreirajKnjiguSO ----------

    @Test
    void kreirajKnjiguPredusloviProlaziZaValidnuKnjigu() {
        KreirajKnjiguSO so = new KreirajKnjiguSO();
        assertDoesNotThrow(() -> so.preduslovi(validKnjiga()));
    }

    @Test
    void kreirajKnjiguPredusloviBacaGreskuZaNullObjekat() {
        KreirajKnjiguSO so = new KreirajKnjiguSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void kreirajKnjiguPredusloviBacaGreskuZaPraznNaziv() {
        KreirajKnjiguSO so = new KreirajKnjiguSO();
        Knjiga k = validKnjiga();
        k.setNaziv("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(k));
        assertEquals("GRESKA NAZIV", ex.getMessage());
    }

    @Test
    void kreirajKnjiguPredusloviBacaGreskuZaPraznogAutora() {
        KreirajKnjiguSO so = new KreirajKnjiguSO();
        Knjiga k = validKnjiga();
        k.setAutor(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(k));
        assertEquals("GRESKA AUTOR", ex.getMessage());
    }

    @Test
    void kreirajKnjiguPredusloviBacaGreskuZaIsbnPogresneDuzine() {
        KreirajKnjiguSO so = new KreirajKnjiguSO();
        Knjiga k = validKnjiga();
        k.setIsbn("123");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(k));
        assertEquals("GRESKA ISBN", ex.getMessage());
    }

    @Test
    void kreirajKnjiguPredusloviBacaGreskuZaPrazanZanr() {
        KreirajKnjiguSO so = new KreirajKnjiguSO();
        Knjiga k = validKnjiga();
        k.setZanr("");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(k));
        assertEquals("GRESKA ZANR", ex.getMessage());
    }

    // ---------- AzurirajKnjiguSO ----------

    @Test
    void azurirajKnjiguPredusloviProlaziZaValidnuKnjigu() {
        AzurirajKnjiguSO so = new AzurirajKnjiguSO();
        assertDoesNotThrow(() -> so.preduslovi(validKnjiga()));
    }

    @Test
    void azurirajKnjiguPredusloviBacaGreskuZaIsbnPogresneDuzine() {
        AzurirajKnjiguSO so = new AzurirajKnjiguSO();
        Knjiga k = validKnjiga();
        k.setIsbn("previseDugackiIsbnBrojZaTestiranje");
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(k));
        assertEquals("GRESKA ISBN", ex.getMessage());
    }

    @Test
    void azurirajKnjiguPredusloviBacaGreskuZaNullObjekat() {
        AzurirajKnjiguSO so = new AzurirajKnjiguSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    // ---------- ObrisiKnjiguSO ----------

    @Test
    void obrisiKnjiguPredusloviProlaziZaValidnuKnjigu() {
        ObrisiKnjiguSO so = new ObrisiKnjiguSO();
        assertDoesNotThrow(() -> so.preduslovi(validKnjiga()));
    }

    @Test
    void obrisiKnjiguPredusloviBacaGreskuZaNullObjekat() {
        ObrisiKnjiguSO so = new ObrisiKnjiguSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void obrisiKnjiguPredusloviBacaGreskuZaPogresanTip() {
        ObrisiKnjiguSO so = new ObrisiKnjiguSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije knjiga"));
    }

    // ---------- UcitajKnjigeSO ----------

    @Test
    void ucitajKnjigePredusloviProlaziUvek() {
        UcitajKnjigeSO so = new UcitajKnjigeSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validKnjiga()));
    }
}
