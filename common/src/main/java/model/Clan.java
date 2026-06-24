package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja clana biblioteke.
 * Clan poseduje licne podatke i ima dodeljen tip clanstva koji
 * definise uslove i ogranicenja njegovog clanstva.
 *
 * @author Petar
 */
public class Clan implements ApstraktniDomenskiObjekat {

    private int idClan;
    private String ime;
    private String prezime;
    private String email;
    private String brojTelefona;
    private TipClanstva tipClanstva;

    /**
     * Podrazumevani konstruktor.
     */
    public Clan() {
    }

    /**
     * Kreira novog clana biblioteke sa svim potrebnim podacima.
     *
     * @param idClan jedinstveni identifikator clana
     * @param ime ime clana
     * @param prezime prezime clana
     * @param email email adresa clana
     * @param brojTelefona kontakt telefon clana
     * @param tipClanstva tip clanstva dodeljen ovom clanu
     */
    public Clan(int idClan, String ime, String prezime, String email, String brojTelefona, TipClanstva tipClanstva) {
    	try {
    		setIdClan(idClan);
            setIme(ime);
            setPrezime(prezime);
            setEmail(email);
            setBrojTelefona(brojTelefona);
            setTipClanstva(tipClanstva);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji clana: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca jedinstveni identifikator clana.
     *
     * @return idClan
     */
    public int getIdClan() {
        return idClan;
    }

    /**
     * Postavlja jedinstveni identifikator clana.
     *
     * @param idClan identifikator clana
     */
    public void setIdClan(int idClan) {
        this.idClan = idClan;
    }

    /**
     * Vraca ime clana.
     *
     * @return ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime clana.
     *
     * @param ime ime clana, ne sme biti null niti prazno
     * @throws Exception ako je ime null ili prazno
     */
    public void setIme(String ime) throws Exception {
        if (ime == null || ime.isEmpty()) {
            throw new Exception("GRESKA IME");
        }
        this.ime = ime;
    }

    /**
     * Vraca prezime clana.
     *
     * @return prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime clana.
     *
     * @param prezime prezime clana, ne sme biti null niti prazno
     * @throws Exception ako je prezime null ili prazno
     */
    public void setPrezime(String prezime) throws Exception {
        if (prezime == null || prezime.isEmpty()) {
            throw new Exception("GRESKA PREZIME");
        }
        this.prezime = prezime;
    }

    /**
     * Vraca email adresu clana.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email adresu clana.
     *
     * @param email email adresa clana, mora sadrzati karakter '@' i ne sme biti null niti prazna
     * @throws Exception ako je email null, prazan ili ne sadrzi '@'
     */
    public void setEmail(String email) throws Exception {
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new Exception("GRESKA EMAIL");
        }
        this.email = email;
    }

    /**
     * Vraca broj telefona clana.
     *
     * @return brojTelefona
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }

    /**
     * Postavlja broj telefona clana.
     *
     * @param brojTelefona broj telefona clana, ne sme biti null niti prazan
     * @throws Exception ako je brojTelefona null ili prazan
     */
    public void setBrojTelefona(String brojTelefona) throws Exception {
        if (brojTelefona == null || brojTelefona.isEmpty()) {
            throw new Exception("GRESKA BROJ TELEFONA");
        }
        this.brojTelefona = brojTelefona;
    }

    /**
     * Vraca tip clanstva clana.
     *
     * @return tipClanstva
     */
    public TipClanstva getTipClanstva() {
        return tipClanstva;
    }

    /**
     * Postavlja tip clanstva clana.
     *
     * @param tipClanstva tip clanstva, ne sme biti null
     * @throws Exception ako je tipClanstva null
     */
    public void setTipClanstva(TipClanstva tipClanstva) throws Exception {
        if (tipClanstva == null) {
            throw new Exception("GRESKA TIP CLANSTVA");
        }
        this.tipClanstva = tipClanstva;
    }

    /**
     * Vraca string reprezentaciju clana u formatu "ime prezime".
     *
     * @return ime i prezime clana
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
        int hash = 3;
        return hash;
    }

    /**
     * Dva clana su jednaka ako imaju isto ime i prezime.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su ime i prezime jednaki, inace false
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

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "clan"
     */
    @Override
    public String vratiNazivTabele() {
        return "clan";
    }

    /**
     * Konvertuje ResultSet u listu Clan objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista Clan objekata
     * @throws Exception ako dodje do greske pri citanju
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
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

    /**
     * Vraca nazive kolona za INSERT upit.
     *
     * @return nazivi kolona odvojeni zarezom
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, brojTelefona, idTipClanstva";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + brojTelefona + "', " + tipClanstva.getIdTipClanstva();
    }

    /**
     * Vraca WHERE uslov za identifikaciju po primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "clan.idClan=" + idClan;
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
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', brojTelefona='" + brojTelefona + "', idTipClanstva=" + tipClanstva.getIdTipClanstva();
    }
}
