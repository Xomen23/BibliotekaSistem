package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja jednu stavku u okviru pozajmice.
 * Stavka pozajmice prati konkretnu knjigu, da li je vracena i eventualnu kaznu.
 *
 * @author Petar
 */
public class StavkaPozajmice implements ApstraktniDomenskiObjekat {

    private int rb;
    private Pozajmica pozajmica;
    private Date datumVracanja;
    private boolean vraceno;
    private int kazna;
    private Knjiga knjiga;

    /**
     * Podrazumevani konstruktor.
     */
    public StavkaPozajmice() {
    }

    /**
     * Kreira novu stavku pozajmice sa svim potrebnim podacima.
     *
     * @param rb redni broj stavke unutar pozajmice, mora biti strogo veci od nule
     * @param pozajmica pozajmica kojoj stavka pripada, ne sme biti null
     * @param datumVracanja datum vracanja knjige, moze biti null ako knjiga nije vracena
     * @param vraceno true ako je knjiga vracena
     * @param kazna iznos kazne, ne sme biti negativan
     * @param knjiga pozajmljena knjiga, ne sme biti null
     */
    public StavkaPozajmice(int rb, Pozajmica pozajmica, Date datumVracanja, boolean vraceno, int kazna, Knjiga knjiga) {
    	
    	try {
    		setRb(rb);
            setPozajmica(pozajmica);
            setDatumVracanja(datumVracanja);
            setVraceno(vraceno);
            setKazna(kazna);
            setKnjiga(knjiga);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji pozajmice: " + e.getMessage());
    	}
        
        
    }

    /**
     * Vraca redni broj stavke.
     *
     * @return rb
     */
    public int getRb() {
        return rb;
    }

    /**
     * Postavlja redni broj stavke.
     *
     * @param rb redni broj, mora biti strogo veci od nule
     * @throws Exception ako je rb manji ili jednak nuli
     */
    public void setRb(int rb) throws Exception {
        if (rb <= 0) {
            throw new Exception("GRESKA REDNI BROJ");
        }
        this.rb = rb;
    }

    /**
     * Vraca pozajmicu kojoj stavka pripada.
     *
     * @return pozajmica
     */
    public Pozajmica getPozajmica() {
        return pozajmica;
    }

    /**
     * Postavlja pozajmicu kojoj stavka pripada.
     *
     * @param pozajmica pozajmica, ne sme biti null
     * @throws Exception ako je pozajmica null
     */
    public void setPozajmica(Pozajmica pozajmica) throws Exception {
        if (pozajmica == null) {
            throw new Exception("GRESKA POZAJMICA STAVKE");
        }
        this.pozajmica = pozajmica;
    }

    /**
     * Vraca datum vracanja knjige.
     *
     * @return datumVracanja, moze biti null ako knjiga nije vracena
     */
    public Date getDatumVracanja() {
        return datumVracanja;
    }

    /**
     * Postavlja datum vracanja knjige.
     *
     * @param datumVracanja datum vracanja, moze biti null ako knjiga jos nije vracena
     */
    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    /**
     * Vraca da li je knjiga vracena.
     *
     * @return true ako je knjiga vracena, inace false
     */
    public boolean isVraceno() {
        return vraceno;
    }

    /**
     * Postavlja status vracanja knjige.
     *
     * @param vraceno true ako je knjiga vracena
     */
    public void setVraceno(boolean vraceno) {
        this.vraceno = vraceno;
    }

    /**
     * Vraca iznos kazne za ovu stavku.
     *
     * @return kazna
     */
    public int getKazna() {
        return kazna;
    }

    /**
     * Postavlja iznos kazne za ovu stavku.
     *
     * @param kazna iznos kazne, ne sme biti negativan
     * @throws Exception ako je kazna manja od nule
     */
    public void setKazna(int kazna) throws Exception {
        if (kazna < 0) {
            throw new Exception("GRESKA KAZNA");
        }
        this.kazna = kazna;
    }

    /**
     * Vraca pozajmljenu knjigu.
     *
     * @return knjiga
     */
    public Knjiga getKnjiga() {
        return knjiga;
    }

    /**
     * Postavlja pozajmljenu knjigu.
     *
     * @param knjiga knjiga, ne sme biti null
     * @throws Exception ako je knjiga null
     */
    public void setKnjiga(Knjiga knjiga) throws Exception {
        if (knjiga == null) {
            throw new Exception("GRESKA KNJIGA STAVKE");
        }
        this.knjiga = knjiga;
    }

    /**
     * Vraca string reprezentaciju stavke pozajmice.
     *
     * @return detalji stavke pozajmice
     */
    @Override
    public String toString() {
        return "StavkaPozajmice{rb=" + rb + ", pozajmica=" + pozajmica + ", datumVracanja="
                + datumVracanja + ", vraceno=" + vraceno + ", kazna=" + kazna + ", knjiga=" + knjiga + '}';
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
     * Dve stavke su jednake ako imaju isti redni broj i pripadaju istoj pozajmici.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su redni broj i pozajmica jednaki, inace false
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
        final StavkaPozajmice other = (StavkaPozajmice) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.pozajmica, other.pozajmica);
    }

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "stavkaPozajmice"
     */
    @Override
    public String vratiNazivTabele() {
        return "stavkaPozajmice";
    }

    /**
     * Konvertuje ResultSet u listu StavkaPozajmice objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista StavkaPozajmice objekata
     * @throws Exception ako dodje do greske pri citanju
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idRadnik = rs.getInt("radnik.idRadnik");
            String imeR = rs.getString("radnik.ime");
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
            if (datumVracanjaSQL != null) {
                datumVracanja = new java.util.Date(datumVracanjaSQL.getTime());
            }
            if (vracenoINT == 1) {
                vraceno = true;
            }
            StavkaPozajmice sp = new StavkaPozajmice(rb, p, datumVracanja, vraceno, kazna, k);
            lista.add(sp);
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
        return "idPozajmica, rb, datumVracanja, vraceno, kazna, idKnjiga";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        int vracenoINT = vraceno ? 1 : 0;
        String datumVracanjaSQL;
        if (datumVracanja != null) {
            datumVracanjaSQL = "'" + new java.sql.Date(datumVracanja.getTime()) + "'";
            return pozajmica.getIdPozajmica() + ", " + rb + ", " + datumVracanjaSQL + ", " + vracenoINT + ", " + kazna + ", " + knjiga.getIdKnjiga();
        } else {
            datumVracanjaSQL = "NULL";
            return pozajmica.getIdPozajmica() + ", " + rb + ", " + datumVracanjaSQL + ", " + vracenoINT + ", " + kazna + ", " + knjiga.getIdKnjiga();
        }
    }

    /**
     * Vraca WHERE uslov za identifikaciju po kompozitnom primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaPozajmice.idPozajmica=" + pozajmica.getIdPozajmica() + " AND stavkaPozajmice.rb=" + rb;
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
        int vracenoINT = vraceno ? 1 : 0;
        String datumVracanjaSQL;
        if (datumVracanja != null) {
            datumVracanjaSQL = "'" + new java.sql.Date(datumVracanja.getTime()) + "'";
            return "datumVracanja= " + datumVracanjaSQL + ", vraceno= " + vracenoINT + ", kazna=" + kazna + ", idknjiga=" + knjiga.getIdKnjiga();
        } else {
            datumVracanjaSQL = "NULL";
            return "datumVracanja= " + datumVracanjaSQL + ", vraceno= " + vracenoINT + ", kazna=" + kazna + ", idknjiga=" + knjiga.getIdKnjiga();
        }
    }
}
