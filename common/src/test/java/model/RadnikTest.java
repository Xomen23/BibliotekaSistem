package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link Radnik}.
 *
 * @author Petar
 */
class RadnikTest {

    private Radnik radnik;

    @BeforeEach
    void setUp() {
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, radnik.getIdRadnik());
        assertEquals("Stefan", radnik.getIme());
        assertEquals("Jovanovic", radnik.getPrezime());
        assertEquals("0101990123456", radnik.getJmbg());
        assertEquals("sjovanovic", radnik.getKorisnickoIme());
        assertEquals("0641111111", radnik.getBrojTelefona());
        assertEquals("tajna123", radnik.getSifra());
    }

    @Test
    void testSeteri() {
        Radnik r = new Radnik();
        r.setIdRadnik(2);
        r.setIme("Ana");
        r.setPrezime("Stanojevic");
        r.setJmbg("0202995654321");
        r.setKorisnickoIme("astanojevic");
        r.setBrojTelefona("0652222222");
        r.setSifra("lozinka");

        assertEquals(2, r.getIdRadnik());
        assertEquals("Ana", r.getIme());
        assertEquals("Stanojevic", r.getPrezime());
        assertEquals("0202995654321", r.getJmbg());
        assertEquals("astanojevic", r.getKorisnickoIme());
        assertEquals("0652222222", r.getBrojTelefona());
        assertEquals("lozinka", r.getSifra());
    }

    @Test
    void testToString() {
        assertEquals("Stefan Jovanovic", radnik.toString());
    }

    @Test
    void testEqualsIstoKorisnickoImeISifra() {
        Radnik drugi = new Radnik(99, "Neko Drugi", "Prezime", "9999999999999", "sjovanovic", "0600000000", "tajna123");
        assertEquals(radnik, drugi);
    }

    @Test
    void testEqualsRazlicitaSifra() {
        Radnik drugi = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "pogresnaSifra");
        assertNotEquals(radnik, drugi);
    }

    @Test
    void testEqualsRazlicitoKorisnickoIme() {
        Radnik drugi = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "drugiUser", "0641111111", "tajna123");
        assertNotEquals(radnik, drugi);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, radnik);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("radnik", radnik.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("ime, prezime, jmbg, korisnickoIme, brojTelefona, sifra", radnik.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        String ocekivano = "'Stefan', 'Jovanovic', '0101990123456', 'sjovanovic', '0641111111', 'tajna123'";
        assertEquals(ocekivano, radnik.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("radnik.idRadnik=1", radnik.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        String ocekivano = "ime='Stefan', prezime='Jovanovic', jmbg='0101990123456', korisnickoIme='sjovanovic', brojTelefona='0641111111', sifra='tajna123'";
        assertEquals(ocekivano, radnik.vratiVrednostiZaIzmenu());
    }
}
