package operacija.stavke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajStavkePozajmiceSOTest {

    private UcitajStavkePozajmiceSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajStavkePozajmiceSO();
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
