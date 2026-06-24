package operacija.radnici;

import model.Radnik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajRadnikeSOTest {

    private UcitajRadnikeSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajRadnikeSO();
    }

    @Test
    void predusloviProlaziZaNull() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    @Test
    void predusloviProlaziZaBiloKojiObjekat() throws Exception {
        Radnik obj = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        assertDoesNotThrow(() -> so.preduslovi(obj));
    }
}
