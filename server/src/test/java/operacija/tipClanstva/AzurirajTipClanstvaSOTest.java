package operacija.tipClanstva;

import model.TipClanstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AzurirajTipClanstvaSOTest {

    private AzurirajTipClanstvaSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new AzurirajTipClanstvaSO();
    }

    private TipClanstva validTipClanstva() throws Exception {
        return new TipClanstva(1, "Standardni", 500.0, 5);
    }

    @Test
    void predusloviProlaziZaValidanTipClanstva() {
        assertDoesNotThrow(() -> so.preduslovi(validTipClanstva()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije tip"));
    }
}
