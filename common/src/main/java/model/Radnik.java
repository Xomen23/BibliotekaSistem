/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja zaposlenog radnika biblioteke.
 * 
 * Radnik se prijavljuje u sistem korisničkim imenom i šifrom, i zadužen
 * je za kreiranje i obradu pozajmica, kao i za rad tokom dodeljenih
 * termina dežurstva.
 *
 * @author Petar
 */
public class Radnik implements ApstraktniDomenskiObjekat{
    
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
     * @param jmbg jedinstveni matični broj građana
     * @param korisnickoIme korisničko ime za prijavu u sistem
     * @param brojTelefona kontakt telefon radnika
     * @param sifra šifra (lozinka) za prijavu u sistem
     */
    public Radnik(int idRadnik, String ime, String prezime, String jmbg, String korisnickoIme, String brojTelefona, String sifra) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.korisnickoIme = korisnickoIme;
        this.brojTelefona = brojTelefona;
        this.sifra = sifra;
    }

    public int getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(int idRadnik) {
        this.idRadnik = idRadnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Dva radnika se smatraju jednakim ukoliko imaju isto korisničko ime i šifru.
     * Ova metoda se koristi i pri prijavi radnika u sistem (login).
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su korisničko ime i šifra jednaki, inače {@code false}
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

    @Override
    public String vratiNazivTabele() {
        return "radnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
        
            int idRadnik = rs.getInt("radnik.idRadnik");
            String ime =  rs.getString("radnik.ime");
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

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, jmbg, korisnickoIme, brojTelefona, sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + jmbg + "', '" + korisnickoIme + "', '" + brojTelefona + "', '" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "radnik.idRadnik=" + idRadnik;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', jmbg='" + jmbg + "', korisnickoIme='" + korisnickoIme + "', brojTelefona='" + brojTelefona + "', sifra='" + sifra + "'"; 
    }
    
    
    
    
    
}
