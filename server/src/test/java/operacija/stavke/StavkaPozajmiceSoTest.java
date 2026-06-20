package operacija.stavke;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Clan;
import model.Knjiga;
import model.Pozajmica;
import model.Radnik;
import model.StavkaPozajmice;
import model.TipClanstva;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemske operacije nad stavkom pozajmice.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class StavkaPozajmiceSoTest {

    private Pozajmica pozajmica;
    private Knjiga knjiga;

    private StavkaPozajmice validStavka() throws Exception {
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        Clan clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        Radnik radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date datumPozajmice = sdf.parse("2026-06-01");
        Date rokVracanja = sdf.parse("2026-06-15");
        pozajmica = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
        knjiga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
        return new StavkaPozajmice(1, pozajmica, null, false, 100, knjiga);
    }

    // ---------- KreirajStavkuPozajmiceSO ----------

    @Test
    void kreirajStavkuPredusloviProlaziZaValidnuStavku() throws Exception {
        KreirajStavkuPozajmiceSO so = new KreirajStavkuPozajmiceSO();
        assertDoesNotThrow(() -> so.preduslovi(validStavka()));
    }

    @Test
    void kreirajStavkuPredusloviBacaGreskuZaNullObjekat() {
        KreirajStavkuPozajmiceSO so = new KreirajStavkuPozajmiceSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void kreirajStavkuPredusloviBacaGreskuZaNedostajucuPozajmicu() throws Exception {
        KreirajStavkuPozajmiceSO so = new KreirajStavkuPozajmiceSO();
        StavkaPozajmice sp = validStavka();
        sp.setPozajmica(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(sp));
        assertEquals("GRESKA POZAJMICA STAVKE", ex.getMessage());
    }

    @Test
    void kreirajStavkuPredusloviBacaGreskuZaNevalidanRedniBroj() throws Exception {
        KreirajStavkuPozajmiceSO so = new KreirajStavkuPozajmiceSO();
        StavkaPozajmice sp = validStavka();
        sp.setRb(0);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(sp));
        assertEquals("GRESKA REDNI BROJ", ex.getMessage());
    }

    @Test
    void kreirajStavkuPredusloviBacaGreskuZaNevalidnuKaznu() throws Exception {
        KreirajStavkuPozajmiceSO so = new KreirajStavkuPozajmiceSO();
        StavkaPozajmice sp = validStavka();
        sp.setKazna(0);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(sp));
        assertEquals("GRESKA KAZNA", ex.getMessage());
    }

    @Test
    void kreirajStavkuPredusloviBacaGreskuZaNedostajucuKnjigu() throws Exception {
        KreirajStavkuPozajmiceSO so = new KreirajStavkuPozajmiceSO();
        StavkaPozajmice sp = validStavka();
        sp.setKnjiga(null);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(sp));
        assertEquals("GRESKA KNJIGA STAVKE", ex.getMessage());
    }

    // ---------- AzurirajStavkuPozajmiceSO ----------

    @Test
    void azurirajStavkuPredusloviProlaziZaValidnuStavku() throws Exception {
        AzurirajStavkuPozajmiceSO so = new AzurirajStavkuPozajmiceSO();
        assertDoesNotThrow(() -> so.preduslovi(validStavka()));
    }

    @Test
    void azurirajStavkuPredusloviProlaziZaKaznuNula() throws Exception {
        // kod AzurirajStavkuPozajmiceSO kazna == 0 je dozvoljena (kazna < 0 baca gresku)
        AzurirajStavkuPozajmiceSO so = new AzurirajStavkuPozajmiceSO();
        StavkaPozajmice sp = validStavka();
        sp.setKazna(0);
        assertDoesNotThrow(() -> so.preduslovi(sp));
    }

    @Test
    void azurirajStavkuPredusloviBacaGreskuZaNegativnuKaznu() throws Exception {
        AzurirajStavkuPozajmiceSO so = new AzurirajStavkuPozajmiceSO();
        StavkaPozajmice sp = validStavka();
        sp.setKazna(-1);
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(sp));
        assertEquals("GRESKA KAZNA", ex.getMessage());
    }

    @Test
    void azurirajStavkuPredusloviBacaGreskuZaNullObjekat() {
        AzurirajStavkuPozajmiceSO so = new AzurirajStavkuPozajmiceSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    // ---------- ObrisiStavkuPozajmiceSO ----------

    @Test
    void obrisiStavkuPredusloviProlaziZaValidnuStavku() throws Exception {
        ObrisiStavkuPozajmiceSO so = new ObrisiStavkuPozajmiceSO();
        assertDoesNotThrow(() -> so.preduslovi(validStavka()));
    }

    @Test
    void obrisiStavkuPredusloviBacaGreskuZaNullObjekat() {
        ObrisiStavkuPozajmiceSO so = new ObrisiStavkuPozajmiceSO();
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void obrisiStavkuPredusloviBacaGreskuZaPogresanTip() {
        ObrisiStavkuPozajmiceSO so = new ObrisiStavkuPozajmiceSO();
        assertThrows(Exception.class, () -> so.preduslovi("nije stavka"));
    }

    // ---------- UcitajStavkePozajmiceSO ----------

    @Test
    void ucitajStavkePredusloviProlaziUvek() throws Exception {
        UcitajStavkePozajmiceSO so = new UcitajStavkePozajmiceSO();
        assertDoesNotThrow(() -> so.preduslovi(null));
        assertDoesNotThrow(() -> so.preduslovi(validStavka()));
    }
}
