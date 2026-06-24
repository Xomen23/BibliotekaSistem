package operacija.clanovi;

import model.Clan;
import model.TipClanstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajClanaSOTest {

    private UcitajClanaSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajClanaSO();
    }

    @Test
    void predusloviProlaziZaNull() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    @Test
    void predusloviProlaziZaBiloKojiObjekat() throws Exception {
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        Clan clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        assertDoesNotThrow(() -> so.preduslovi(clan));
    }
}
