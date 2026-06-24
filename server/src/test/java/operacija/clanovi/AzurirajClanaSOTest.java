package operacija.clanovi;

import model.Clan;
import model.TipClanstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AzurirajClanaSOTest {

    private AzurirajClanaSO so;
    private TipClanstva tip;

    @BeforeEach
    void setUp() throws Exception {
        so = new AzurirajClanaSO();
        tip = new TipClanstva(1, "Standardni", 500.0, 5);
    }

    private Clan validClan() throws Exception {
        return new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
    }

    @Test
    void predusloviProlaziZaValidnogClana() {
        assertDoesNotThrow(() -> so.preduslovi(validClan()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije clan"));
    }
}
