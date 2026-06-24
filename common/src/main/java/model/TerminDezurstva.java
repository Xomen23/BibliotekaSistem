package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja termin dezurstva u biblioteci.
 * Termin dezurstva definise smenu, opis i lokaciju dezurstva i
 * moze biti dodeljen jednom ili vise radnika.
 *
 * @author Petar
 */
public class TerminDezurstva implements ApstraktniDomenskiObjekat {

    private int idTerminDezurstva;
    private String smena;
    private String opis;
    private String lokacija;

    /**
     * Podrazumevani konstruktor.
     */
    public TerminDezurstva() {
    }

    /**
     * Kreira novi termin dezurstva sa svim potrebnim podacima.
     *
     * @param idTerminDezurstva jedinstveni identifikator termina dezurstva
     * @param smena oznaka smene
     * @param opis opis termina dezurstva
     * @param lokacija lokacija na kojoj se dezurstvo obavlja
     * @throws Exception ako je neka od vrednosti nevalidna
     */
    public TerminDezurstva(int idTerminDezurstva, String smena, String opis, String lokacija) {
    	
    	try {
    		setIdTerminDezurstva(idTerminDezurstva);
            setSmena(smena);
            setOpis(opis);
            setLokacija(lokacija);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji termina dezurstva: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca jedinstveni identifikator termina dezurstva.
     *
     * @return idTerminDezurstva
     */
    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    /**
     * Postavlja jedinstveni identifikator termina dezurstva.
     *
     * @param idTerminDezurstva identifikator termina dezurstva
     */
    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    /**
     * Vraca oznaku smene.
     *
     * @return smena
     */
    public String getSmena() {
        return smena;
    }

    /**
     * Postavlja oznaku smene.
     *
     * @param smena oznaka smene, ne sme biti null niti prazna
     * @throws Exception ako je smena null ili prazna
     */
    public void setSmena(String smena) throws Exception {
        if (smena == null || smena.isEmpty()) {
            throw new Exception("GRESKA SMENA");
        }
        this.smena = smena;
    }

    /**
     * Vraca opis termina dezurstva.
     *
     * @return opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis termina dezurstva.
     *
     * @param opis opis termina dezurstva, ne sme biti null niti prazan
     * @throws Exception ako je opis null ili prazan
     */
    public void setOpis(String opis) throws Exception {
        if (opis == null || opis.isEmpty()) {
            throw new Exception("GRESKA OPIS");
        }
        this.opis = opis;
    }

    /**
     * Vraca lokaciju dezurstva.
     *
     * @return lokacija
     */
    public String getLokacija() {
        return lokacija;
    }

    /**
     * Postavlja lokaciju dezurstva.
     *
     * @param lokacija lokacija dezurstva, ne sme biti null niti prazna
     * @throws Exception ako je lokacija null ili prazna
     */
    public void setLokacija(String lokacija) throws Exception {
        if (lokacija == null || lokacija.isEmpty()) {
            throw new Exception("GRESKA LOKACIJA");
        }
        this.lokacija = lokacija;
    }

    /**
     * Vraca string reprezentaciju termina dezurstva.
     *
     * @return smena i lokacija termina dezurstva
     */
    @Override
    public String toString() {
        return "TerminDezurstva{smena=" + smena + ", lokacija=" + lokacija + '}';
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
     * Dva termina dezurstva su jednaka ako imaju istu smenu i lokaciju.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su smena i lokacija jednaki, inace false
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
        final TerminDezurstva other = (TerminDezurstva) obj;
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.lokacija, other.lokacija);
    }

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "terminDezurstva"
     */
    @Override
    public String vratiNazivTabele() {
        return "terminDezurstva";
    }

    /**
     * Konvertuje ResultSet u listu TerminDezurstva objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista TerminDezurstva objekata
     * @throws Exception ako dodje do greske pri citanju
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idTerminDezurstva = rs.getInt("terminDezurstva.idTerminDezurstva");
            String smena = rs.getString("terminDezurstva.smena");
            String opis = rs.getString("terminDezurstva.opis");
            String lokacija = rs.getString("terminDezurstva.lokacija");
            TerminDezurstva td = new TerminDezurstva(idTerminDezurstva, smena, opis, lokacija);
            lista.add(td);
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
        return "smena, opis, lokacija";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + smena + "', '" + opis + "', '" + lokacija + "'";
    }

    /**
     * Vraca WHERE uslov za identifikaciju po primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "terminDezurstva.idTerminDezurstva=" + idTerminDezurstva;
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
        return "smena='" + smena + "', opis='" + opis + "', lokacija='" + lokacija + "'";
    }
}
