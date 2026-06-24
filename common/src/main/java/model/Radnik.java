package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja zaposlenog radnika biblioteke.
 * Radnik se prijavljuje u sistem korisnickim imenom i sifrom.
 *
 * @author Petar
 */
public class Radnik implements ApstraktniDomenskiObjekat {

    private int idRadnik;
    private String ime;
    private String prezime;
    private String jmbg;
    private String korisnickoIme;
    private String brojTelefona;
    private String sifra;

    /**
     * Podrazumevani konstruktor.
     */
    public Radnik() {
    }

    /**
     * Kreira novog radnika sa svim potrebnim podacima.
     *
     * @param idRadnik jedinstveni identifikator radnika
     * @param ime ime radnika
     * @param prezime prezime radnika
     * @param jmbg jedinstveni maticni broj, mora imati tacno 13 karaktera
     * @param korisnickoIme korisnicko ime za prijavu u sistem
     * @param brojTelefona kontakt telefon radnika
     * @param sifra sifra za prijavu u sistem
     */
    public Radnik(int idRadnik, String ime, String prezime, String jmbg, String korisnickoIme, String brojTelefona, String sifra) {
    	try {
    		setIdRadnik(idRadnik);
            setIme(ime);
            setPrezime(prezime);
            setJmbg(jmbg);
            setKorisnickoIme(korisnickoIme);
            setBrojTelefona(brojTelefona);
            setSifra(sifra);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji radnika: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca jedinstveni identifikator radnika.
     *
     * @return idRadnik
     */
    public int getIdRadnik() {
        return idRadnik;
    }

    /**
     * Postavlja jedinstveni identifikator radnika.
     *
     * @param idRadnik identifikator radnika
     */
    public void setIdRadnik(int idRadnik) {
        this.idRadnik = idRadnik;
    }

    /**
     * Vraca ime radnika.
     *
     * @return ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime radnika.
     *
     * @param ime ime radnika, ne sme biti null niti prazno
     * @throws Exception ako je ime null ili prazno
     */
    public void setIme(String ime) throws Exception {
        if (ime == null || ime.isEmpty()) {
            throw new Exception("GRESKA IME");
        }
        this.ime = ime;
    }

    /**
     * Vraca prezime radnika.
     *
     * @return prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime radnika.
     *
     * @param prezime prezime radnika, ne sme biti null niti prazno
     * @throws Exception ako je prezime null ili prazno
     */
    public void setPrezime(String prezime) throws Exception {
        if (prezime == null || prezime.isEmpty()) {
            throw new Exception("GRESKA PREZIME");
        }
        this.prezime = prezime;
    }

    /**
     * Vraca JMBG radnika.
     *
     * @return jmbg
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Postavlja JMBG radnika.
     *
     * @param jmbg JMBG radnika, mora imati tacno 13 karaktera i ne sme biti null
     * @throws Exception ako je jmbg null, prazan ili nema tacno 13 karaktera
     */
    public void setJmbg(String jmbg) throws Exception {
        if (jmbg == null || jmbg.isEmpty() || jmbg.length() != 13) {
            throw new Exception("GRESKA JMBG");
        }
        this.jmbg = jmbg;
    }

    /**
     * Vraca korisnicko ime radnika.
     *
     * @return korisnickoIme
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime radnika.
     *
     * @param korisnickoIme korisnicko ime, ne sme biti null niti prazno
     * @throws Exception ako je korisnickoIme null ili prazno
     */
    public void setKorisnickoIme(String korisnickoIme) throws Exception {
        if (korisnickoIme == null || korisnickoIme.isEmpty()) {
            throw new Exception("GRESKA KORISNICKO IME");
        }
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraca broj telefona radnika.
     *
     * @return brojTelefona
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona radnika.
     *
     * @param brojTelefona broj telefona, ne sme biti null niti prazan
     * @throws Exception ako je brojTelefona null ili prazan
     */
    public void setBrojTelefona(String brojTelefona) throws Exception {
        if (brojTelefona == null || brojTelefona.isEmpty()) {
            throw new Exception("GRESKA BROJ TELEFONA");
        }
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraca sifru radnika.
     *
     * @return sifra
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja sifru radnika.
     *
     * @param sifra sifra za prijavu, ne sme biti null niti prazna
     * @throws Exception ako je sifra null ili prazna
     */
    public void setSifra(String sifra) throws Exception {
        if (sifra == null || sifra.isEmpty()) {
            throw new Exception("GRESKA SIFRA");
        }
        this.sifra = sifra;
    }

    /**
     * Vraca string reprezentaciju radnika u formatu "ime prezime".
     *
     * @return ime i prezime radnika
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * @return hash kod objekta
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Dva radnika su jednaka ako imaju isto korisnicko ime i sifru.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su korisnicko ime i sifra jednaki, inace false
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
        final Radnik other = (Radnik) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "radnik"
     */
    @Override
    public String vratiNazivTabele() {
        return "radnik";
    }

    /**
     * Konvertuje ResultSet u listu Radnik objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista Radnik objekata
     * @throws Exception ako dodje do greske pri citanju
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idRadnik = rs.getInt("radnik.idRadnik");
            String ime = rs.getString("radnik.ime");
            String prezime = rs.getString("radnik.prezime");
            String jmbg = rs.getString("radnik.jmbg");
            String korisnickoIme = rs.getString("radnik.korisnickoIme");
            String brojTelefona = rs.getString("radnik.brojTelefona");
            String sifra = rs.getString("radnik.sifra");
            Radnik r = new Radnik(idRadnik, ime, prezime, jmbg, korisnickoIme, brojTelefona, sifra);
            lista.add(r);
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
        return "ime, prezime, jmbg, korisnickoIme, brojTelefona, sifra";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + jmbg + "', '" + korisnickoIme + "', '" + brojTelefona + "', '" + sifra + "'";
    }

    /**
     * Vraca WHERE uslov za identifikaciju po primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "radnik.idRadnik=" + idRadnik;
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
        return "ime='" + ime + "', prezime='" + prezime + "', jmbg='" + jmbg + "', korisnickoIme='" + korisnickoIme + "', brojTelefona='" + brojTelefona + "', sifra='" + sifra + "'";
    }
}
