package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class RadnikTest {

    private Radnik radnik;

    @BeforeEach
    void setUp() throws Exception {
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, radnik.getIdRadnik());
        assertEquals("Stefan", radnik.getIme());
        assertEquals("Jovanovic", radnik.getPrezime());
        assertEquals("0101990123456", radnik.getJmbg());
        assertEquals("sjovanovic", radnik.getKorisnickoIme());
        assertEquals("0641111111", radnik.getBrojTelefona());
        assertEquals("tajna123", radnik.getSifra());
    }

    @Test
    void setIdRadnikSetujePravilno() {
        radnik.setIdRadnik(99);
        assertEquals(99, radnik.getIdRadnik());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setImeBacaGreskuZaNullIPrazno(String ime) {
        assertThrows(Exception.class, () -> radnik.setIme(ime));
    }

    @Test
    void setImeSetujePravilno() throws Exception {
        radnik.setIme("Ana");
        assertEquals("Ana", radnik.getIme());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setPrezimeBacaGreskuZaNullIPrazno(String prezime) {
        assertThrows(Exception.class, () -> radnik.setPrezime(prezime));
    }

    @Test
    void setPrezimeSetujePravilno() throws Exception {
        radnik.setPrezime("Anic");
        assertEquals("Anic", radnik.getPrezime());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setJmbgBacaGreskuZaNullIPrazno(String jmbg) {
        assertThrows(Exception.class, () -> radnik.setJmbg(jmbg));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "01019901234567", "jmbg"})
    void setJmbgBacaGreskuZaPogresnuDuzinu(String jmbg) {
        assertThrows(Exception.class, () -> radnik.setJmbg(jmbg));
    }

    @Test
    void setJmbgSetujePravilno() throws Exception {
        radnik.setJmbg("0202995654321");
        assertEquals("0202995654321", radnik.getJmbg());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setKorisnickoImeBacaGreskuZaNullIPrazno(String korisnickoIme) {
        assertThrows(Exception.class, () -> radnik.setKorisnickoIme(korisnickoIme));
    }

    @Test
    void setKorisnickoImeSetujePravilno() throws Exception {
        radnik.setKorisnickoIme("noviuser");
        assertEquals("noviuser", radnik.getKorisnickoIme());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setBrojTelefonaBacaGreskuZaNullIPrazno(String broj) {
        assertThrows(Exception.class, () -> radnik.setBrojTelefona(broj));
    }

    @Test
    void setBrojTelefonaSetujePravilno() throws Exception {
        radnik.setBrojTelefona("0652222222");
        assertEquals("0652222222", radnik.getBrojTelefona());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setSifraBacaGreskuZaNullIPrazno(String sifra) {
        assertThrows(Exception.class, () -> radnik.setSifra(sifra));
    }

    @Test
    void setSifraSetujePravilno() throws Exception {
        radnik.setSifra("novasifra");
        assertEquals("novasifra", radnik.getSifra());
    }

    @Test
    void toStringVracaImeIPrezime() {
        assertEquals("Stefan Jovanovic", radnik.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"drugiuser", "treci", "cetvrti"})
    void equalsVracaFalseZaRazlicitoKorisnickoIme(String korisnickoIme) throws Exception {
        Radnik drugi = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", korisnickoIme, "0641111111", "tajna123");
        assertNotEquals(radnik, drugi);
    }

    @Test
    void equalsVracaTrueZaIstoKorisnickoImeISifru() throws Exception {
        Radnik drugi = new Radnik(99, "Neko", "Drugi", "9999999999999", "sjovanovic", "0600000000", "tajna123");
        assertEquals(radnik, drugi);
    }

    @Test
    void equalsVracaFalseZaRazlicitaSifru() throws Exception {
        Radnik drugi = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "pogresna");
        assertNotEquals(radnik, drugi);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, radnik);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(radnik, radnik);
    }

    @Test
    void vratiNazivTabeleVracaRadnik() {
        assertEquals("radnik", radnik.vratiNazivTabele());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("ime, prezime, jmbg, korisnickoIme, brojTelefona, sifra", radnik.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanje() {
        assertEquals("'Stefan', 'Jovanovic', '0101990123456', 'sjovanovic', '0641111111', 'tajna123'", radnik.vratiVrednostiZaUbacivanje());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("radnik.idRadnik=1", radnik.vratiPrimarniKljuc());
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        assertEquals("ime='Stefan', prezime='Jovanovic', jmbg='0101990123456', korisnickoIme='sjovanovic', brojTelefona='0641111111', sifra='tajna123'", radnik.vratiVrednostiZaIzmenu());
    }
}
