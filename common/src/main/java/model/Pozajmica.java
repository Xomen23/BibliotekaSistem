/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja pozajmicu knjiga u biblioteci.
 * 
 * Pozajmica povezuje Radnika koji je obradio pozajmicu, Clan}a
 * kojem su knjige pozajmljene, i listu pojedinačnih stavki
 * (StavkaPozajmice) koje predstavljaju konkretne pozajmljene knjige.
 * Pozajmice se ne brišu iz sistema, već se čuvaju kao istorijski podaci.
 *
 * @author Petar
 */
public class Pozajmica implements ApstraktniDomenskiObjekat{
    
    private int idPozajmica;
    private Date datumPozajmice;
    private Date rokVracanja;
    private String status;
    private String nacinPreuzimanja;
    private int brojStavki;
    private int ukupnaKazna;
    private Radnik radnik;
    private Clan clan;
    private List<StavkaPozajmice> stavke = new ArrayList<>();
    //private SimpleDateFormat dateFormat = new SimpleDate(Format("yyyy-MM-dd");

    /**
     * Podrazumevani konstruktor.
     */
    public Pozajmica() {
    }

    /**
     * Kreira novu pozajmicu sa svim potrebnim podacima.
     *
     * @param idPozajmica jedinstveni identifikator pozajmice
     * @param datumPozajmice datum kada je pozajmica napravljena
     * @param rokVracanja krajnji rok za vraćanje pozajmljenih knjiga
     * @param status status pozajmice (npr. "Aktivna", "Zatvorena")
     * @param nacinPreuzimanja način preuzimanja knjiga
     * @param brojStavki broj pozajmljenih stavki (knjiga) u ovoj pozajmici
     * @param ukupnaKazna ukupan iznos kazne za ovu pozajmicu
     * @param radnik radnik koji je obradio pozajmicu
     * @param clan član kojem su knjige pozajmljene
     * @param stavke lista pojedinačnih stavki pozajmice
     */
    public Pozajmica(int idPozajmica, Date datumPozajmice, Date rokVracanja, String status, String nacinPreuzimanja, int brojStavki, int ukupnaKazna, Radnik radnik, Clan clan, List<StavkaPozajmice> stavke) {
        this.idPozajmica = idPozajmica;
        this.datumPozajmice = datumPozajmice;
        this.rokVracanja = rokVracanja;
        this.status = status;
        this.nacinPreuzimanja = nacinPreuzimanja;
        this.brojStavki = brojStavki;
        this.ukupnaKazna = ukupnaKazna;
        this.radnik = radnik;
        this.clan = clan;
        this.stavke = stavke;
    }

    public int getIdPozajmica() {
        return idPozajmica;
    }

    public void setIdPozajmica(int idPozajmica) {
        this.idPozajmica = idPozajmica;
    }

    public Date getDatumPozajmice() {
        return datumPozajmice;
    }

    public void setDatumPozajmice(Date datumPozajmice) {
        this.datumPozajmice = datumPozajmice;
    }

    public Date getRokVracanja() {
        return rokVracanja;
    }

    public void setRokVracanja(Date rokVracanja) {
        this.rokVracanja = rokVracanja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNacinPreuzimanja() {
        return nacinPreuzimanja;
    }

    public void setNacinPreuzimanja(String nacinPreuzimanja) {
        this.nacinPreuzimanja = nacinPreuzimanja;
    }

    public int getBrojStavki() {
        return brojStavki;
    }

    public void setBrojStavki(int brojStavki) {
        this.brojStavki = brojStavki;
    }

    public int getUkupnaKazna() {
        return ukupnaKazna;
    }

    public void setUkupnaKazna(int ukupnaKazna) {
        this.ukupnaKazna = ukupnaKazna;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public List<StavkaPozajmice> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPozajmice> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Pozajmica{" + "idPozajmica=" + idPozajmica + ", datumPozajmice=" + datumPozajmice + ", rokVracanja=" + rokVracanja + ", status=" + status + ", nacinPreuzimanja=" + nacinPreuzimanja + ", brojStavki=" + brojStavki + ", ukupnaKazna=" + ukupnaKazna + ", radnik=" + radnik + ", clan=" + clan + ", stavke=" + stavke + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Dve pozajmice se smatraju jednakim ukoliko imaju isti datum pozajmice i istog člana.
     *
     * @param obj objekat sa kojim se poredi
     * @return {@code true} ako su datum pozajmice i član jednaki, inače {@code false}
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
        final Pozajmica other = (Pozajmica) obj;
        if (!Objects.equals(this.datumPozajmice, other.datumPozajmice)) {
            return false;
        }
        return Objects.equals(this.clan, other.clan);
    }

    @Override
    public String vratiNazivTabele() {
        return "pozajmica";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
        
            int idRadnik = rs.getInt("radnik.idRadnik");
            String imeR =  rs.getString("radnik.ime");
            String prezimeR = rs.getString("radnik.prezime");
            String jmbgR = rs.getString("radnik.jmbg");
            String korisnickoImeR = rs.getString("radnik.korisnickoIme");
            String brojTelefonaR = rs.getString("radnik.brojTelefona");
            String sifraR = rs.getString("radnik.sifra");
            
            Radnik r = new Radnik(idRadnik, imeR, prezimeR, jmbgR, korisnickoImeR, brojTelefonaR, sifraR);
        
            
            
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
            
            
            
            int idPozajmica = rs.getInt("pozajmica.idPozajmica");
            java.sql.Date datumPozajmiceSQL = rs.getDate("pozajmica.datumPozajmice");
            java.sql.Date rokVracanjaSQL = rs.getDate("pozajmica.rokVracanja");
            String status = rs.getString("pozajmica.status");
            String nacinPreuzimanja = rs.getString("pozajmica.nacinPreuzimanja");
            int brojStavki = rs.getInt("pozajmica.brojStavki");
            int ukupnaKazna = rs.getInt("pozajmica.ukupnaKazna");
            
            java.util.Date datumPozajmice = new java.util.Date(datumPozajmiceSQL.getTime());
            java.util.Date rokVracanja = new java.util.Date(rokVracanjaSQL.getTime());
            
            Pozajmica p = new Pozajmica(idPozajmica, datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, r, c, stavke);
            lista.add(p);
        
        }

        return lista;
        
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, idRadnik, idClan";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        
        if(datumPozajmice != null && rokVracanja != null){
            java.sql.Date datumPozajmiceSQL = new java.sql.Date(datumPozajmice.getTime());
            java.sql.Date rokVracanjaSQL = new java.sql.Date(rokVracanja.getTime());
            
            
            return "'" + datumPozajmiceSQL + "', '" + rokVracanjaSQL + "', '" + status + "', '" + nacinPreuzimanja + "', " + brojStavki + ", " + ukupnaKazna + ", " + radnik.getIdRadnik() + ", " + clan.getIdClan();
            
        }
        
        return "";
        
        
        
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "pozajmica.idPozajmica=" + idPozajmica;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        
        if(datumPozajmice != null && rokVracanja != null){
            java.sql.Date datumPozajmiceSQL = new java.sql.Date(datumPozajmice.getTime());
            java.sql.Date rokVracanjaSQL = new java.sql.Date(rokVracanja.getTime());
            
            
            return "datumPozajmice='" +  datumPozajmiceSQL + "', rokVracanja='" + rokVracanjaSQL + "', status='" + status + "', nacinPreuzimanja='" + nacinPreuzimanja +"', brojStavki=" +  brojStavki 
                + ", ukupnaKazna=" + ukupnaKazna + ", idRadnik=" + radnik.getIdRadnik() + ", idClan=" + clan.getIdClan();
            
        }
        
        return "";
        
    }
    

    
    
    
    
    
}
