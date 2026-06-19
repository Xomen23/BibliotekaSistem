package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link Clan}.
 *
 * @author Petar
 */
class ClanTest {

    private TipClanstva tipClanstva;
    private Clan clan;

    @BeforeEach
    void setUp() {
        tipClanstva = new TipClanstva(1, "Standardni", 500.0, 5);
        clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tipClanstva);
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, clan.getIdClan());
        assertEquals("Petar", clan.getIme());
        assertEquals("Mitrovic", clan.getPrezime());
        assertEquals("petar@gmail.com", clan.getEmail());
        assertEquals("0641234567", clan.getBrojTelefona());
        assertEquals(tipClanstva, clan.getTipClanstva());
    }

    @Test
    void testSeteri() {
        Clan c = new Clan();
        c.setIdClan(2);
        c.setIme("Ana");
        c.setPrezime("Anic");
        c.setEmail("ana@gmail.com");
        c.setBrojTelefona("0651112233");
        c.setTipClanstva(tipClanstva);

        assertEquals(2, c.getIdClan());
        assertEquals("Ana", c.getIme());
        assertEquals("Anic", c.getPrezime());
        assertEquals("ana@gmail.com", c.getEmail());
        assertEquals("0651112233", c.getBrojTelefona());
        assertEquals(tipClanstva, c.getTipClanstva());
    }

    @Test
    void testToString() {
        assertEquals("Petar Mitrovic", clan.toString());
    }

    @Test
    void testEqualsIstoImeIPrezime() {
        Clan drugi = new Clan(2, "Petar", "Mitrovic", "drugi@mail.com", "0699999999", tipClanstva);
        assertEquals(clan, drugi);
    }

    @Test
    void testEqualsRazlicitoIme() {
        Clan drugi = new Clan(1, "Marko", "Mitrovic", "petar@gmail.com", "0641234567", tipClanstva);
        assertNotEquals(clan, drugi);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, clan);
    }

    @Test
    void testEqualsRazlicitaKlasa() {
        assertNotEquals("Petar Mitrovic", clan);
    }

    @Test
    void testEqualsIsteReference() {
        assertEquals(clan, clan);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("clan", clan.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("ime, prezime, email, brojTelefona, idTipClanstva", clan.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        String ocekivano = "'Petar', 'Mitrovic', 'petar@gmail.com', '0641234567', 1";
        assertEquals(ocekivano, clan.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("clan.idClan=1", clan.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        String ocekivano = "ime='Petar', prezime='Mitrovic', email='petar@gmail.com', brojTelefona='0641234567', idTipClanstva=1";
        assertEquals(ocekivano, clan.vratiVrednostiZaIzmenu());
    }
}
