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
 * Domenska klasa koja predstavlja člana biblioteke.
 * 
 * Član biblioteke poseduje osnovne lične podatke (ime, prezime, email, broj telefona)
 * i ima dodeljen TipClanstva koji definiše uslove i ograničenja njegovog
 * članstva (cena, maksimalan broj stavki koje može pozajmiti).
 *
 * @author Petar
 */
public class Clan implements ApstraktniDomenskiObjekat{
    
    private int idClan;
    private String ime;
    private String prezime;
    private String email;
    private String brojTelefona;
    private TipClanstva tipClanstva;

    /**
     * Podrazumevani konstruktor, potreban za kreiranje praznog objekta
     * (npr. pri popunjavanju iz formi pre slanja na server).
     */
    public Clan() {
    }

    /**
     * Kreira novog člana biblioteke sa svim potrebnim podacima.
     *
     * @param idClan jedinstveni identifikator člana
     * @param ime ime člana
     * @param prezime prezime člana
     * @param email email adresa člana
     * @param brojTelefona kontakt telefon člana
     * @param tipClanstva tip članstva dodeljen ovom članu
     */
    public Clan(int idClan, String ime, String prezime, String email, String brojTelefona, TipClanstva tipClanstva) {
        this.idClan = idClan;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.tipClanstva = tipClanstva;
    }

    public int getIdClan() {
        return idClan;
    }

    public void setIdClan(int idClan) {
        this.idClan = idClan;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public TipClanstva getTipClanstva() {
        return tipClanstva;
    }

    public void setTipClanstva(TipClanstva tipClanstva) {
        this.tipClanstva = tipClanstva;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    // MOZDA moze dodas i email da mora bude isti za equals
    
    /**
     * Dva člana se smatraju jednakim ukoliko imaju isto ime i prezime.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su ime i prezime jednaki, inače {@code false}
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
        final Clan other = (Clan) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    @Override
    public String vratiNazivTabele() {
        return "clan";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
        
            int idTipClanstva = rs.getInt("tipClanstva.idTipClanstva");
            String tip = rs.getString("tipClanstva.tip");
            double cena = rs.getDouble("tipClanstva.cena");
            int maksimalanBrojStavki = rs.getInt("tipClanstva.maksimalanBrojStavki");
        
            TipClanstva tc = new TipClanstva(idTipClanstva, tip, cena, maksimalanBrojStavki);
            
            int idClan = rs.getInt("clan.idClan");
            String ime = rs.getString("clan.ime");
            String prezime = rs.getString("clan.prezime");
            String email = rs.getString("clan.email");
            String brojTelefona = rs.getString("clan.brojTelefona");
            
            Clan c = new Clan(idClan, ime, prezime, email, brojTelefona, tc);
            lista.add(c);

        
        }

        return lista;
        
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, brojTelefona, idTipClanstva";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime  + "', '" + email + "', '" + brojTelefona + "', " + tipClanstva.getIdTipClanstva();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "clan.idClan=" + idClan;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', brojTelefona='" + brojTelefona + "', idTipClanstva=" + tipClanstva.getIdTipClanstva();
    }
    
    
    
    
    
}
