package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja pozajmicu knjiga u biblioteci.
 * Pozajmica povezuje radnika, clana i listu stavki pozajmice.
 * Pozajmice se ne brisu iz sistema, vec ostaju kao istorijski podaci.
 *
 * @author Petar
 */
public class Pozajmica implements ApstraktniDomenskiObjekat {

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

    /**
     * Podrazumevani konstruktor.
     */
    public Pozajmica() {
    }

    /**
     * Kreira novu pozajmicu sa svim potrebnim podacima.
     *
     * @param idPozajmica jedinstveni identifikator pozajmice
     * @param datumPozajmice datum kada je pozajmica napravljena, ne sme biti null
     * @param rokVracanja krajnji rok za vracanje knjiga, ne sme biti null
     * @param status status pozajmice, ne sme biti null niti prazan
     * @param nacinPreuzimanja nacin preuzimanja knjiga, ne sme biti null niti prazan
     * @param brojStavki broj pozajmljenih stavki, ne sme biti negativan
     * @param ukupnaKazna ukupan iznos kazne, ne sme biti negativan
     * @param radnik radnik koji je obradio pozajmicu, ne sme biti null
     * @param clan clan kome su knjige pozajmljene, ne sme biti null
     * @param stavke lista stavki pozajmice
     */
    public Pozajmica(int idPozajmica, Date datumPozajmice, Date rokVracanja, String status,
            String nacinPreuzimanja, int brojStavki, int ukupnaKazna,
            Radnik radnik, Clan clan, List<StavkaPozajmice> stavke) {
    	
    	try {
    		setIdPozajmica(idPozajmica);
            setDatumPozajmice(datumPozajmice);
            setRokVracanja(rokVracanja);
            setStatus(status);
            setNacinPreuzimanja(nacinPreuzimanja);
            setBrojStavki(brojStavki);
            setUkupnaKazna(ukupnaKazna);
            setRadnik(radnik);
            setClan(clan);
            setStavke(stavke);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji pozajmice: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca jedinstveni identifikator pozajmice.
     *
     * @return idPozajmica
     */
    public int getIdPozajmica() {
        return idPozajmica;
    }

    /**
     * Postavlja jedinstveni identifikator pozajmice.
     *
     * @param idPozajmica identifikator pozajmice
     */
    public void setIdPozajmica(int idPozajmica) {
        this.idPozajmica = idPozajmica;
    }

    /**
     * Vraca datum pozajmice.
     *
     * @return datumPozajmice
     */
    public Date getDatumPozajmice() {
        return datumPozajmice;
    }

    /**
     * Postavlja datum pozajmice.
     *
     * @param datumPozajmice datum pozajmice, ne sme biti null
     * @throws Exception ako je datumPozajmice null
     */
    public void setDatumPozajmice(Date datumPozajmice) throws Exception {
        if (datumPozajmice == null) {
            throw new Exception("GRESKA DATUM POZAJMICE");
        }
        this.datumPozajmice = datumPozajmice;
    }

    /**
     * Vraca rok vracanja knjiga.
     *
     * @return rokVracanja
     */
    public Date getRokVracanja() {
        return rokVracanja;
    }

    /**
     * Postavlja rok vracanja knjiga.
     *
     * @param rokVracanja rok vracanja, ne sme biti null
     * @throws Exception ako je rokVracanja null
     */
    public void setRokVracanja(Date rokVracanja) throws Exception {
        if (rokVracanja == null) {
            throw new Exception("GRESKA ROK VRACANJA");
        }
        this.rokVracanja = rokVracanja;
    }

    /**
     * Vraca status pozajmice.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Postavlja status pozajmice.
     *
     * @param status status pozajmice, ne sme biti null niti prazan
     * @throws Exception ako je status null ili prazan
     */
    public void setStatus(String status) throws Exception {
        if (status == null || status.isEmpty()) {
            throw new Exception("GRESKA STATUS");
        }
        this.status = status;
    }

    /**
     * Vraca nacin preuzimanja knjiga.
     *
     * @return nacinPreuzimanja
     */
    public String getNacinPreuzimanja() {
        return nacinPreuzimanja;
    }

    /**
     * Postavlja nacin preuzimanja knjiga.
     *
     * @param nacinPreuzimanja nacin preuzimanja, ne sme biti null niti prazan
     * @throws Exception ako je nacinPreuzimanja null ili prazan
     */
    public void setNacinPreuzimanja(String nacinPreuzimanja) throws Exception {
        if (nacinPreuzimanja == null || nacinPreuzimanja.isEmpty()) {
            throw new Exception("GRESKA NACIN PREUZIMANJA");
        }
        this.nacinPreuzimanja = nacinPreuzimanja;
    }

    /**
     * Vraca broj stavki u pozajmici.
     *
     * @return brojStavki
     */
    public int getBrojStavki() {
        return brojStavki;
    }

    /**
     * Postavlja broj stavki u pozajmici.
     *
     * @param brojStavki broj stavki, ne sme biti negativan
     * @throws Exception ako je brojStavki manji od nule
     */
    public void setBrojStavki(int brojStavki) throws Exception {
        if (brojStavki < 0) {
            throw new Exception("GRESKA BROJ STAVKI");
        }
        this.brojStavki = brojStavki;
    }

    /**
     * Vraca ukupan iznos kazne.
     *
     * @return ukupnaKazna
     */
    public int getUkupnaKazna() {
        return ukupnaKazna;
    }

    /**
     * Postavlja ukupan iznos kazne.
     *
     * @param ukupnaKazna ukupna kazna, ne sme biti negativna
     * @throws Exception ako je ukupnaKazna manja od nule
     */
    public void setUkupnaKazna(int ukupnaKazna) throws Exception {
        if (ukupnaKazna < 0) {
            throw new Exception("GRESKA UKUPNA KAZNA");
        }
        this.ukupnaKazna = ukupnaKazna;
    }

    /**
     * Vraca radnika koji je obradio pozajmicu.
     *
     * @return radnik
     */
    public Radnik getRadnik() {
        return radnik;
    }

    /**
     * Postavlja radnika koji je obradio pozajmicu.
     *
     * @param radnik radnik, ne sme biti null
     * @throws Exception ako je radnik null
     */
    public void setRadnik(Radnik radnik) throws Exception {
        if (radnik == null) {
            throw new Exception("GRESKA RADNIK");
        }
        this.radnik = radnik;
    }

    /**
     * Vraca clana kome su knjige pozajmljene.
     *
     * @return clan
     */
    public Clan getClan() {
        return clan;
    }

    /**
     * Postavlja clana kome su knjige pozajmljene.
     *
     * @param clan clan, ne sme biti null
     * @throws Exception ako je clan null
     */
    public void setClan(Clan clan) throws Exception {
        if (clan == null) {
            throw new Exception("GRESKA CLAN");
        }
        this.clan = clan;
    }

    /**
     * Vraca listu stavki pozajmice.
     *
     * @return stavke
     */
    public List<StavkaPozajmice> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki pozajmice.
     *
     * @param stavke lista stavki pozajmice
     */
    public void setStavke(List<StavkaPozajmice> stavke) {
        this.stavke = stavke;
    }

    /**
     * Vraca string reprezentaciju pozajmice.
     *
     * @return detalji pozajmice
     */
    @Override
    public String toString() {
        return "Pozajmica{idPozajmica=" + idPozajmica + ", datumPozajmice=" + datumPozajmice
                + ", rokVracanja=" + rokVracanja + ", status=" + status
                + ", nacinPreuzimanja=" + nacinPreuzimanja + ", brojStavki=" + brojStavki
                + ", ukupnaKazna=" + ukupnaKazna + ", radnik=" + radnik
                + ", clan=" + clan + ", stavke=" + stavke + '}';
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
     * Dve pozajmice su jednake ako imaju isti datum pozajmice i istog clana.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su datum pozajmice i clan jednaki, inace false
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

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "pozajmica"
     */
    @Override
    public String vratiNazivTabele() {
        return "pozajmica";
    }

    /**
     * Konvertuje ResultSet u listu Pozajmica objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista Pozajmica objekata
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

            Pozajmica p = new Pozajmica(idPozajmica, datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, r, c, stavke);
            lista.add(p);
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
        return "datumPozajmice, rokVracanja, status, nacinPreuzimanja, brojStavki, ukupnaKazna, idRadnik, idClan";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom, ili prazan string ako datumi nisu postavljeni
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        if (datumPozajmice != null && rokVracanja != null) {
            java.sql.Date datumPozajmiceSQL = new java.sql.Date(datumPozajmice.getTime());
            java.sql.Date rokVracanjaSQL = new java.sql.Date(rokVracanja.getTime());
            return "'" + datumPozajmiceSQL + "', '" + rokVracanjaSQL + "', '" + status + "', '"
                    + nacinPreuzimanja + "', " + brojStavki + ", " + ukupnaKazna + ", "
                    + radnik.getIdRadnik() + ", " + clan.getIdClan();
        }
        return "";
    }

    /**
     * Vraca WHERE uslov za identifikaciju po primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "pozajmica.idPozajmica=" + idPozajmica;
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
     * @return SET klauzula za UPDATE upit, ili prazan string ako datumi nisu postavljeni
     */
    @Override
    public String vratiVrednostiZaIzmenu() {
        if (datumPozajmice != null && rokVracanja != null) {
            java.sql.Date datumPozajmiceSQL = new java.sql.Date(datumPozajmice.getTime());
            java.sql.Date rokVracanjaSQL = new java.sql.Date(rokVracanja.getTime());
            return "datumPozajmice='" + datumPozajmiceSQL + "', rokVracanja='" + rokVracanjaSQL
                    + "', status='" + status + "', nacinPreuzimanja='" + nacinPreuzimanja
                    + "', brojStavki=" + brojStavki + ", ukupnaKazna=" + ukupnaKazna
                    + ", idRadnik=" + radnik.getIdRadnik() + ", idClan=" + clan.getIdClan();
        }
        return "";
    }
}
