package operacija.knjige;

import model.Knjiga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UcitajKnjigeSOTest {

    private UcitajKnjigeSO so;

    @BeforeEach
    void setUp() {
        so = new UcitajKnjigeSO();
    }

    @Test
    void predusloviProlaziZaNull() {
        assertDoesNotThrow(() -> so.preduslovi(null));
    }

    @Test
    void predusloviProlaziZaBiloKojiObjekat() throws Exception {
        Knjiga obj = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
        assertDoesNotThrow(() -> so.preduslovi(obj));
    }
}
