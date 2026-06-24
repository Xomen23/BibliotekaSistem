package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class KnjigaTest {

    private Knjiga knjiga;

    @BeforeEach
    void setUp() throws Exception {
        knjiga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
    }

    @Test
    void konstruktorSetujeSvaPolja() {
        assertEquals(1, knjiga.getIdKnjiga());
        assertEquals("Na Drini cuprija", knjiga.getNaziv());
        assertEquals("Ivo Andric", knjiga.getAutor());
        assertEquals("9788671023033", knjiga.getIsbn());
        assertEquals("Roman", knjiga.getZanr());
    }

    @Test
    void setIdKnjigaSetujePravilno() {
        knjiga.setIdKnjiga(99);
        assertEquals(99, knjiga.getIdKnjiga());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setNazivBacaGreskuZaNullIPrazno(String naziv) {
        assertThrows(Exception.class, () -> knjiga.setNaziv(naziv));
    }

    @Test
    void setNazivSetujePravilno() throws Exception {
        knjiga.setNaziv("Novi naziv");
        assertEquals("Novi naziv", knjiga.getNaziv());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setAutorBacaGreskuZaNullIPrazno(String autor) {
        assertThrows(Exception.class, () -> knjiga.setAutor(autor));
    }

    @Test
    void setAutorSetujePravilno() throws Exception {
        knjiga.setAutor("Novi autor");
        assertEquals("Novi autor", knjiga.getAutor());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setIsbnBacaGreskuZaNullIPrazno(String isbn) {
        assertThrows(Exception.class, () -> knjiga.setIsbn(isbn));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12345678901234", "isbn"})
    void setIsbnBacaGreskuZaPogresnuDuzinu(String isbn) {
        assertThrows(Exception.class, () -> knjiga.setIsbn(isbn));
    }

    @Test
    void setIsbnSetujePravilno() throws Exception {
        knjiga.setIsbn("9780000000002");
        assertEquals("9780000000002", knjiga.getIsbn());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setZanrBacaGreskuZaNullIPrazno(String zanr) {
        assertThrows(Exception.class, () -> knjiga.setZanr(zanr));
    }

    @Test
    void setZanrSetujePravilno() throws Exception {
        knjiga.setZanr("Poezija");
        assertEquals("Poezija", knjiga.getZanr());
    }

    @Test
    void toStringVracaNaziv() {
        assertEquals("Na Drini cuprija", knjiga.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"9780000000002", "9780000000019", "9780000000026"})
    void equalsVracaFalseZaRazlicitIsbn(String isbn) throws Exception {
        Knjiga druga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", isbn, "Roman");
        assertNotEquals(knjiga, druga);
    }

    @Test
    void equalsVracaTrueZaIstiIsbn() throws Exception {
        Knjiga druga = new Knjiga(99, "Drugi naziv", "Drugi autor", "9788671023033", "Drugi zanr");
        assertEquals(knjiga, druga);
    }

    @Test
    void equalsVracaFalseZaNull() {
        assertNotEquals(null, knjiga);
    }

    @Test
    void equalsVracaTrueZaIsteReference() {
        assertEquals(knjiga, knjiga);
    }

    @Test
    void vratiNazivTabeleVracaKnjiga() {
        assertEquals("knjiga", knjiga.vratiNazivTabele());
    }

    @Test
    void vratiKoloneZaUbacivanje() {
        assertEquals("naziv, autor, isbn, zanr", knjiga.vratiKoloneZaUbacivanje());
    }

    @Test
    void vratiVrednostiZaUbacivanje() {
        assertEquals("'Na Drini cuprija', 'Ivo Andric', '9788671023033', 'Roman'", knjiga.vratiVrednostiZaUbacivanje());
    }

    @Test
    void vratiPrimarniKljuc() {
        assertEquals("knjiga.idKnjiga=1", knjiga.vratiPrimarniKljuc());
    }

    @Test
    void vratiVrednostiZaIzmenu() {
        assertEquals("naziv='Na Drini cuprija', autor='Ivo Andric', isbn='9788671023033', zanr='Roman'", knjiga.vratiVrednostiZaIzmenu());
    }
}
