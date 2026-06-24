package operacija.terminDezurstva;

import model.TerminDezurstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObrisiTerminDezurstvaSOTest {

    private ObrisiTerminDezurstvaSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new ObrisiTerminDezurstvaSO();
    }

    private TerminDezurstva validTerminDezurstva() throws Exception {
        return new TerminDezurstva(1, "Prepodne", "Dezurstvo", "Glavna sala");
    }

    @Test
    void predusloviProlaziZaValidanTerminDezurstva() {
        assertDoesNotThrow(() -> so.preduslovi(validTerminDezurstva()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije termin"));
    }
}
