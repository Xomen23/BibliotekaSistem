package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja knjigu u fondu biblioteke.
 * Svaka knjiga je jedinstveno odredjena ISBN brojem.
 *
 * @author Petar
 */
public class Knjiga implements ApstraktniDomenskiObjekat {

    private int idKnjiga;
    private String naziv;
    private String autor;
    private String isbn;
    private String zanr;

    /**
     * Podrazumevani konstruktor.
     */
    public Knjiga() {
    }

    /**
     * Kreira novu knjigu sa svim potrebnim podacima.
     *
     * @param idKnjiga jedinstveni identifikator knjige
     * @param naziv naziv knjige
     * @param autor autor knjige
     * @param isbn ISBN broj knjige, mora imati tacno 13 karaktera
     * @param zanr zanr knjige
     * @throws Exception ako je neka od vrednosti nevalidna
     */
    public Knjiga(int idKnjiga, String naziv, String autor, String isbn, String zanr) {
    	
    	try {
    		setIdKnjiga(idKnjiga);
            setNaziv(naziv);
            setAutor(autor);
            setIsbn(isbn);
            setZanr(zanr);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji knjige: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca jedinstveni identifikator knjige.
     *
     * @return idKnjiga
     */
    public int getIdKnjiga() {
        return idKnjiga;
    }

    /**
     * Postavlja jedinstveni identifikator knjige.
     *
     * @param idKnjiga identifikator knjige
     */
    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    /**
     * Vraca naziv knjige.
     *
     * @return naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv knjige.
     *
     * @param naziv naziv knjige, ne sme biti null niti prazan
     * @throws Exception ako je naziv null ili prazan
     */
    public void setNaziv(String naziv) throws Exception {
        if (naziv == null || naziv.isEmpty()) {
            throw new Exception("GRESKA NAZIV");
        }
        this.naziv = naziv;
    }

    /**
     * Vraca autora knjige.
     *
     * @return autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Postavlja autora knjige.
     *
     * @param autor autor knjige, ne sme biti null niti prazan
     * @throws Exception ako je autor null ili prazan
     */
    public void setAutor(String autor) throws Exception {
        if (autor == null || autor.isEmpty()) {
            throw new Exception("GRESKA AUTOR");
        }
        this.autor = autor;
    }

    /**
     * Vraca ISBN broj knjige.
     *
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Postavlja ISBN broj knjige.
     *
     * @param isbn ISBN broj knjige, mora imati tacno 13 karaktera i ne sme biti null
     * @throws Exception ako je isbn null, prazan ili nema tacno 13 karaktera
     */
    public void setIsbn(String isbn) throws Exception {
        if (isbn == null || isbn.isEmpty() || isbn.length() != 13) {
            throw new Exception("GRESKA ISBN");
        }
        this.isbn = isbn;
    }

    /**
     * Vraca zanr knjige.
     *
     * @return zanr
     */
    public String getZanr() {
        return zanr;
    }

    /**
     * Postavlja zanr knjige.
     *
     * @param zanr zanr knjige, ne sme biti null niti prazan
     * @throws Exception ako je zanr null ili prazan
     */
    public void setZanr(String zanr) throws Exception {
        if (zanr == null || zanr.isEmpty()) {
            throw new Exception("GRESKA ZANR");
        }
        this.zanr = zanr;
    }

    /**
     * Vraca naziv knjige kao string reprezentaciju.
     *
     * @return naziv knjige
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * @return hash kod objekta
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Dve knjige su jednake ako imaju isti ISBN broj.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su ISBN brojevi jednaki, inace false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Knjiga other = (Knjiga) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "knjiga"
     */
    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    /**
     * Konvertuje ResultSet u listu Knjiga objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista Knjiga objekata
     * @throws Exception ako dodje do greske pri citanju
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idKnjiga = rs.getInt("knjiga.idKnjiga");
            String naziv = rs.getString("knjiga.naziv");
            String autor = rs.getString("knjiga.autor");
            String isbn = rs.getString("knjiga.isbn");
            String zanr = rs.getString("knjiga.zanr");
            Knjiga k = new Knjiga(idKnjiga, naziv, autor, isbn, zanr);
            lista.add(k);
        }
        return lista;
    }

    /**
     * Vraca nazive kolona za INSERT upit.
     *
     * @return nazivi kolona odvojeni zarezom
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, autor, isbn, zanr";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + autor + "', '" + isbn + "', '" + zanr + "'";
    }

    /**
     * Vraca WHERE uslov za identifikaciju po primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "knjiga.idKnjiga=" + idKnjiga;
    }

    /**
     * Nije implementirano.
     *
     * @param rs ResultSet
     * @return nista
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Vraca vrednosti za UPDATE upit.
     *
     * @return SET klauzula za UPDATE upit
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', autor='" + autor + "', isbn='" + isbn + "', zanr='" + zanr + "'";
    }
}
