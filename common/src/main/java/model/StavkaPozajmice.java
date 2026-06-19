/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Petar
 */
public class StavkaPozajmice implements ApstraktniDomenskiObjekat{
    
    private int rb;
    private Pozajmica pozajmica;
    private Date datumVracanja;
    private boolean vraceno;
    private int kazna;
    private Knjiga knjiga;

    public StavkaPozajmice() {
    }

    public StavkaPozajmice(int rb, Pozajmica pozajmica, Date datumVracanja, boolean vraceno, int kazna, Knjiga knjiga) {
        this.rb = rb;
        this.pozajmica = pozajmica;
        this.datumVracanja = datumVracanja;
        this.vraceno = vraceno;
        this.kazna = kazna;
        this.knjiga = knjiga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Pozajmica getPozajmica() {
        return pozajmica;
    }

    public void setPozajmica(Pozajmica pozajmica) {
        this.pozajmica = pozajmica;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public boolean isVraceno() {
        return vraceno;
    }

    public void setVraceno(boolean vraceno) {
        this.vraceno = vraceno;
    }

    public int getKazna() {
        return kazna;
    }

    public void setKazna(int kazna) {
        this.kazna = kazna;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    @Override
    public String toString() {
        return "StavkaPozajmice{" + "rb=" + rb + ", pozajmica=" + pozajmica + ", datumVracanja=" + datumVracanja + ", vraceno=" + vraceno + ", kazna=" + kazna + ", knjiga=" + knjiga + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

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
        final StavkaPozajmice other = (StavkaPozajmice) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.pozajmica, other.pozajmica);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaPozajmice";
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
            
            List<StavkaPozajmice> stavke = new ArrayList<>();
            
            Pozajmica p = new Pozajmica(idPozajmica, datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, r, c, stavke);
            
            int idKnjiga = rs.getInt("knjiga.idKnjiga");
            String naziv = rs.getString("knjiga.naziv");
            String autor = rs.getString("knjiga.autor");
            String isbn = rs.getString("knjiga.isbn");
            String zanr = rs.getString("knjiga.zanr");
            
            Knjiga k = new Knjiga(idKnjiga, naziv, autor, isbn, zanr);
            
            int rb = rs.getInt("stavkaPozajmice.rb");
            java.sql.Date datumVracanjaSQL = rs.getDate("stavkaPozajmice.datumVracanja");
            int vracenoINT = rs.getInt("stavkaPozajmice.vraceno");
            int kazna = rs.getInt("stavkaPozajmice.kazna");
            boolean vraceno = false;
            java.util.Date datumVracanja = null;
            if(datumVracanjaSQL != null){
                datumVracanja = new java.util.Date(datumVracanjaSQL.getTime());
            }
            
            if(vracenoINT == 1){
                vraceno = true;
            }else{
                vraceno = false;
            }
            
            StavkaPozajmice sp = new StavkaPozajmice(rb, p, datumVracanja, vraceno, kazna, k);
            lista.add(sp);
        
        
        }

        return lista;
        
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idPozajmica, rb, datumVracanja, vraceno, kazna, idKnjiga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        
        int vracenoINT;
            if(vraceno){
                vracenoINT = 1;
            }else{
                vracenoINT = 0;
            }
        String datumVracanjaSQL;
        if(datumVracanja != null){
            datumVracanjaSQL = "'" + new java.sql.Date(datumVracanja.getTime()) + "'";
            
            
            return pozajmica.getIdPozajmica() + ", " + rb + ", " + datumVracanjaSQL + ", " + vracenoINT + ", " + kazna + ", " + knjiga.getIdKnjiga();
            
        }else{
            
            datumVracanjaSQL = "NULL";
            return pozajmica.getIdPozajmica() + ", " + rb + ", " + datumVracanjaSQL + ", " + vracenoINT + ", " + kazna + ", " + knjiga.getIdKnjiga();
        
        }
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaPozajmice.idPozajmica=" + pozajmica.getIdPozajmica() + " AND stavkaPozajmice.rb=" + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        //return "idPozajmica= " + pozajmica.getIdPozajmica() + ", rb= " + rb + ", datumVracanja= '" + datumVracanja + "', vraceno= " + vraceno + ", kazna=" + kazna + ", idknjiga=" + knjiga.getIdKnjiga();
        int vracenoINT;
            if(vraceno){
                vracenoINT = 1;
            }else{
                vracenoINT = 0;
            }
        String datumVracanjaSQL;
        if(datumVracanja != null){
            
            datumVracanjaSQL = "'" + new java.sql.Date(datumVracanja.getTime()) + "'";
            return "datumVracanja= " + datumVracanjaSQL + ", vraceno= " + vracenoINT + ", kazna=" + kazna + ", idknjiga=" + knjiga.getIdKnjiga();
            
        }else{
            
            datumVracanjaSQL = "NULL";
            return "datumVracanja= " + datumVracanjaSQL + ", vraceno= " + vracenoINT + ", kazna=" + kazna + ", idknjiga=" + knjiga.getIdKnjiga();
        
        }
        
        
    }
    
    
    
    
    
    
}
