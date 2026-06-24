package operacija.radnici;

import model.Radnik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObrisiRadnikaSOTest {

    private ObrisiRadnikaSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new ObrisiRadnikaSO();
    }

    private Radnik validRadnik() throws Exception {
        return new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
    }

    @Test
    void predusloviProlaziZaValidanRadnik() {
        assertDoesNotThrow(() -> so.preduslovi(validRadnik()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije radnik"));
    }
}
