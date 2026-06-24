package operacija.login;

import model.Radnik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginOperacijaTest {

    private LoginOperacija so;

    @BeforeEach
    void setUp() {
        so = new LoginOperacija();
    }

    @Test
    void predusloviProlaziZaValidnogRadnika() throws Exception {
        Radnik r = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        assertDoesNotThrow(() -> so.preduslovi(r));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(null));
        assertEquals("Ne moze da se uloguje", ex.getMessage());
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije radnik"));
    }

    @Test
    void getRadnikJeNullPreIzvrsavanja() {
        assertNull(so.getRadnik());
    }
}
