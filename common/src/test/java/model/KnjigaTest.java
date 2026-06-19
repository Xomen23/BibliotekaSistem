package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za domensku klasu {@link Knjiga}.
 *
 * @author Petar
 */
class KnjigaTest {

    private Knjiga knjiga;

    @BeforeEach
    void setUp() {
        knjiga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "978-86-7102-303-3", "Roman");
    }

    @Test
    void testKonstruktorIGeteri() {
        assertEquals(1, knjiga.getIdKnjiga());
        assertEquals("Na Drini cuprija", knjiga.getNaziv());
        assertEquals("Ivo Andric", knjiga.getAutor());
        assertEquals("978-86-7102-303-3", knjiga.getIsbn());
        assertEquals("Roman", knjiga.getZanr());
    }

    @Test
    void testSeteri() {
        Knjiga k = new Knjiga();
        k.setIdKnjiga(2);
        k.setNaziv("Prokleta avlija");
        k.setAutor("Ivo Andric");
        k.setIsbn("978-86-7102-304-0");
        k.setZanr("Novela");

        assertEquals(2, k.getIdKnjiga());
        assertEquals("Prokleta avlija", k.getNaziv());
        assertEquals("Ivo Andric", k.getAutor());
        assertEquals("978-86-7102-304-0", k.getIsbn());
        assertEquals("Novela", k.getZanr());
    }

    @Test
    void testToString() {
        assertEquals("Na Drini cuprija", knjiga.toString());
    }

    @Test
    void testEqualsIstiIsbn() {
        Knjiga druga = new Knjiga(99, "Drugi naziv", "Drugi autor", "978-86-7102-303-3", "Drugi zanr");
        assertEquals(knjiga, druga);
    }

    @Test
    void testEqualsRazlicitIsbn() {
        Knjiga druga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "000-00-0000-000-0", "Roman");
        assertNotEquals(knjiga, druga);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, knjiga);
    }

    @Test
    void testEqualsRazlicitaKlasa() {
        assertNotEquals("Na Drini cuprija", knjiga);
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("knjiga", knjiga.vratiNazivTabele());
    }

    @Test
    void testVratiKoloneZaUbacivanje() {
        assertEquals("naziv, autor, isbn, zanr", knjiga.vratiKoloneZaUbacivanje());
    }

    @Test
    void testVratiVrednostiZaUbacivanje() {
        String ocekivano = "'Na Drini cuprija', 'Ivo Andric', '978-86-7102-303-3', 'Roman'";
        assertEquals(ocekivano, knjiga.vratiVrednostiZaUbacivanje());
    }

    @Test
    void testVratiPrimarniKljuc() {
        assertEquals("knjiga.idKnjiga=1", knjiga.vratiPrimarniKljuc());
    }

    @Test
    void testVratiVrednostiZaIzmenu() {
        String ocekivano = "naziv='Na Drini cuprija', autor='Ivo Andric', isbn='978-86-7102-303-3', zanr='Roman'";
        assertEquals(ocekivano, knjiga.vratiVrednostiZaIzmenu());
    }
}
