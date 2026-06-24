package operacija.terminDezurstva;

import model.TerminDezurstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajTerminDezurstvaSOTest {

    private UcitajTerminDezurstvaSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajTerminDezurstvaSO();
    }

    @Test
    void predusloviProlaziZaNull() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    @Test
    void predusloviProlaziZaBiloKojiObjekat() throws Exception {
        TerminDezurstva obj = new TerminDezurstva(1, "Prepodne", "Dezurstvo", "Glavna sala");
        assertDoesNotThrow(() -> so.preduslovi(obj));
    }
}
