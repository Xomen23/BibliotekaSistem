package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class TipClanstvaTest {

    private TipClanstva tip;

    @BeforeEach
    void setUp() throws Exception {
        tip = new TipClanstva(1, "Standardni", 500.0, 5);
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, tip.getIdTipClanstva());
        assertEquals("Standardni", tip.getTip());
        assertEquals(500.0, tip.getCena());
        assertEquals(5, tip.getMaksimalanBrojStavki());
    }

    @Test
    void setIdTipClanstvaSetujePravilno() {
        tip.setIdTipClanstva(99);
        assertEquals(99, tip.getIdTipClanstva());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setTipBacaGreskuZaNullIPrazno(String naziv) {
        assertThrows(Exception.class, () -> tip.setTip(naziv));
    }

    @Test
    void setTipSetujePravilno() throws Exception {
        tip.setTip("Premium");
        assertEquals("Premium", tip.getTip());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -100.0})
    void setCenaBacaGreskuZaNegativnuIliNultu(double cena) {
        assertThrows(Exception.class, () -> tip.setCena(cena));
    }

    @Test
    void setCenaSetujePravilno() throws Exception {
        tip.setCena(1000.0);
        assertEquals(1000.0, tip.getCena());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10})
    void setMaksimalanBrojStavkiBacaGreskuZaNegativnuIliNultu(int mbs) {
        assertThrows(Exception.class, () -> tip.setMaksimalanBrojStavki(mbs));
    }

    @Test
    void setMaksimalanBrojStavkiSetujePravilno() throws Exception {
        tip.setMaksimalanBrojStavki(10);
        assertEquals(10, tip.getMaksimalanBrojStavki());
    }

    @Test
    void toStringVracaNazivTipa() {
        assertEquals("Standardni", tip.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Premium", "Basic", "Gold"})
    void equalsVracaFalseZaRazlicitNaziv(String naziv) throws Exception {
        TipClanstva drugi = new TipClanstva(1, naziv, 500.0, 5);
        assertNotEquals(tip, drugi);
    }

    @Test
    void equalsVracaTrueZaIstiNaziv() throws Exception {
        TipClanstva drugi = new TipClanstva(99, "Standardni", 999.0, 99);
        assertEquals(tip, drugi);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, tip);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(tip, tip);
    }

    @Test
    void vratiNazivTabeleVracaTipClanstva() {
        assertEquals("tipClanstva", tip.vratiNazivTabele());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("tip, cena, maksimalanBrojStavki", tip.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanje() {
        assertEquals("'Standardni', 500.0, 5", tip.vratiVrednostiZaUbacivanje());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("tipClanstva.idTipClanstva=1", tip.vratiPrimarniKljuc());
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        assertEquals("tip='Standardni', cena=500.0, maksimalanBrojStavki=5", tip.vratiVrednostiZaIzmenu());
    }
}
