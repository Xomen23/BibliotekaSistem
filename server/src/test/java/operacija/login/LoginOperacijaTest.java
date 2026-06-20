package operacija.login;

import model.Radnik;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za sistemsku operaciju prijave radnika u sistem.
 * Testira se metoda preduslovi, koja ne zahteva konekciju na bazu podataka.
 *
 * @author Petar
 */
class LoginOperacijaTest {

    @Test
    void predusloviProlaziZaValidnogRadnika() {
        LoginOperacija so = new LoginOperacija();
        Radnik r = new Radnik(0, null, null, null, "sjovanovic", null, "tajna123");
        assertDoesNotThrow(() -> so.preduslovi(r));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        LoginOperacija so = new LoginOperacija();
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(null));
        assertEquals("Ne moze da se uloguje", ex.getMessage());
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        LoginOperacija so = new LoginOperacija();
        assertThrows(Exception.class, () -> so.preduslovi("nije radnik"));
    }

    @Test
    void getRadnikJeNullPreIzvrsavanjaOperacije() {
        LoginOperacija so = new LoginOperacija();
        assertNull(so.getRadnik());
    }
}
