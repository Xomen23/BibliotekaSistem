package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ClanTest {

    private Clan clan;
    private TipClanstva tip;

    @BeforeEach
    void setUp() throws Exception {
        tip = new TipClanstva(1, "Standardni", 500.0, 5);
        clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, clan.getIdClan());
        assertEquals("Petar", clan.getIme());
        assertEquals("Mitrovic", clan.getPrezime());
        assertEquals("petar@gmail.com", clan.getEmail());
        assertEquals("0641234567", clan.getBrojTelefona());
        assertEquals(tip, clan.getTipClanstva());
    }

    @Test
    void setIdClanSetujePravilno() {
        clan.setIdClan(99);
        assertEquals(99, clan.getIdClan());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setImeBacaGreskuZaNullIPrazno(String ime) {
        assertThrows(Exception.class, () -> clan.setIme(ime));
    }

    @Test
    void setImeSetujePravilno() throws Exception {
        clan.setIme("Ana");
        assertEquals("Ana", clan.getIme());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setPrezimeBacaGreskuZaNullIPrazno(String prezime) {
        assertThrows(Exception.class, () -> clan.setPrezime(prezime));
    }

    @Test
    void setPrezimeSetujePravilno() throws Exception {
        clan.setPrezime("Anic");
        assertEquals("Anic", clan.getPrezime());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setEmailBacaGreskuZaNullIPrazno(String email) {
        assertThrows(Exception.class, () -> clan.setEmail(email));
    }

    @Test
    void setEmailBacaGreskuZaEmailBezAt() {
        assertThrows(Exception.class, () -> clan.setEmail("nevalidanEmail"));
    }

    @Test
    void setEmailSetujePravilno() throws Exception {
        clan.setEmail("novi@mail.com");
        assertEquals("novi@mail.com", clan.getEmail());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setBrojTelefonaBacaGreskuZaNullIPrazno(String broj) {
        assertThrows(Exception.class, () -> clan.setBrojTelefona(broj));
    }

    @Test
    void setBrojTelefonaSetujePravilno() throws Exception {
        clan.setBrojTelefona("065111222");
        assertEquals("065111222", clan.getBrojTelefona());
    }

    @Test
    void setTipClanstvaBacaGreskuZaNull() {
        assertThrows(Exception.class, () -> clan.setTipClanstva(null));
    }

    @Test
    void setTipClanstvaSetujePravilno() throws Exception {
        TipClanstva noviTip = new TipClanstva(2, "Premium", 1000.0, 10);
        clan.setTipClanstva(noviTip);
        assertEquals(noviTip, clan.getTipClanstva());
    }

    @Test
    void toStringVracaImeIPrezime() {
        assertEquals("Petar Mitrovic", clan.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Petar", "Ana", "Marko"})
    void equalsVracaFalseZaRazlicitoIme(String ime) throws Exception {
        Clan drugi = new Clan(1, ime, "DrugoP", "d@d.com", "0600000000", tip);
        if (!ime.equals("Petar") || !drugi.getPrezime().equals(clan.getPrezime())) {
            assertNotEquals(clan, drugi);
        }
    }

    @Test
    void equalsVracaTrueZaIstoImeIPrezime() throws Exception {
        Clan drugi = new Clan(99, "Petar", "Mitrovic", "drugi@mail.com", "0699999999", tip);
        assertEquals(clan, drugi);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, clan);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(clan, clan);
    }

    @Test
    void vratiNazivTabeleVracaClan() {
        assertEquals("clan", clan.vratiNazivTabele());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("ime, prezime, email, brojTelefona, idTipClanstva", clan.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanje() {
        assertEquals("'Petar', 'Mitrovic', 'petar@gmail.com', '0641234567', 1", clan.vratiVrednostiZaUbacivanje());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("clan.idClan=1", clan.vratiPrimarniKljuc());
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        assertEquals("ime='Petar', prezime='Mitrovic', email='petar@gmail.com', brojTelefona='0641234567', idTipClanstva=1", clan.vratiVrednostiZaIzmenu());
    }
}
