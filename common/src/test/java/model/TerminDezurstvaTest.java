package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class TerminDezurstvaTest {

    private TerminDezurstva termin;

    @BeforeEach
    void setUp() throws Exception {
        termin = new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", "Glavna sala");
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, termin.getIdTerminDezurstva());
        assertEquals("Prepodne", termin.getSmena());
        assertEquals("Dezurstvo na pultu", termin.getOpis());
        assertEquals("Glavna sala", termin.getLokacija());
    }

    @Test
    void setIdTerminDezurstvaSetujePravilno() {
        termin.setIdTerminDezurstva(99);
        assertEquals(99, termin.getIdTerminDezurstva());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setSmenaGacaGreskuZaNullIPrazno(String smena) {
        assertThrows(Exception.class, () -> termin.setSmena(smena));
    }

    @Test
    void setSmenaSetujePravilno() throws Exception {
        termin.setSmena("Popodne");
        assertEquals("Popodne", termin.getSmena());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setOpisBacaGreskuZaNullIPrazno(String opis) {
        assertThrows(Exception.class, () -> termin.setOpis(opis));
    }

    @Test
    void setOpisSetujePravilno() throws Exception {
        termin.setOpis("Novi opis");
        assertEquals("Novi opis", termin.getOpis());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setLokacijaBacaGreskuZaNullIPrazno(String lokacija) {
        assertThrows(Exception.class, () -> termin.setLokacija(lokacija));
    }

    @Test
    void setLokacijaSetujePravilno() throws Exception {
        termin.setLokacija("Magacin");
        assertEquals("Magacin", termin.getLokacija());
    }

    @Test
    void toStringVracaSmenuILokaciju() {
        assertEquals("TerminDezurstva{smena=Prepodne, lokacija=Glavna sala}", termin.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Popodne", "Noc", "Vikend"})
    void equalsVracaFalseZaRazlicituSmenu(String smena) throws Exception {
        TerminDezurstva drugi = new TerminDezurstva(1, smena, "Dezurstvo na pultu", "Glavna sala");
        assertNotEquals(termin, drugi);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Magacin", "Ulaz", "Citaonica"})
    void equalsVracaFalseZaRazlicituLokaciju(String lokacija) throws Exception {
        TerminDezurstva drugi = new TerminDezurstva(1, "Prepodne", "Dezurstvo na pultu", lokacija);
        assertNotEquals(termin, drugi);
    }

    @Test
    void equalsVracaTrueZaIstuSmenuILokaciju() throws Exception {
        TerminDezurstva drugi = new TerminDezurstva(99, "Prepodne", "Drugi opis", "Glavna sala");
        assertEquals(termin, drugi);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, termin);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(termin, termin);
    }

    @Test
    void vratiNazivTabeleVracaTerminDezurstva() {
        assertEquals("terminDezurstva", termin.vratiNazivTabele());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("smena, opis, lokacija", termin.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanje() {
        assertEquals("'Prepodne', 'Dezurstvo na pultu', 'Glavna sala'", termin.vratiVrednostiZaUbacivanje());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("terminDezurstva.idTerminDezurstva=1", termin.vratiPrimarniKljuc());
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        assertEquals("smena='Prepodne', opis='Dezurstvo na pultu', lokacija='Glavna sala'", termin.vratiVrednostiZaIzmenu());
    }
}
