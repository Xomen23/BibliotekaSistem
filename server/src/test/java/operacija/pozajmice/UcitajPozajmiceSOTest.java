package operacija.pozajmice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajPozajmiceSOTest {

    private UcitajPozajmiceSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajPozajmiceSO();
    }

    @Test
    void predusloviProlaziZaNull() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    @Test
    void predusloviProlaziZaBiloKojiObjekat() {
        assertDoesNotThrow(() -> so.preduslovi("bilo sta"));
    }
}
