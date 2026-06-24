package operacija.tipClanstva;

import model.TipClanstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajTipClanstvaSOTest {

    private UcitajTipClanstvaSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajTipClanstvaSO();
    }

    @Test
    void predusloviProlaziZaNull() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    @Test
    void predusloviProlaziZaBiloKojiObjekat() throws Exception {
        TipClanstva obj = new TipClanstva(1, "Standardni", 500.0, 5);
        assertDoesNotThrow(() -> so.preduslovi(obj));
    }
}
