package operacija.knjige;

import model.Knjiga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KreirajKnjiguSOTest {

    private KreirajKnjiguSO so;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajKnjiguSO();
    }

    private Knjiga validKnjiga() throws Exception {
        return new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
    }

    @Test
    void predusloviProlaziZaValidanKnjiga() {
        assertDoesNotThrow(() -> so.preduslovi(validKnjiga()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije knjiga"));
    }
}
