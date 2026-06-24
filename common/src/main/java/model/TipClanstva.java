package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja tip clanstva u biblioteci.
 * Tip clanstva definise cenu clanarine i maksimalan broj stavki koje
 * clan sme istovremeno da pozajmi.
 *
 * @author Petar
 */
public class TipClanstva implements ApstraktniDomenskiObjekat {

    private int idTipClanstva;
    private String tip;
    private double cena;
    private int maksimalanBrojStavki;

    /**
     * Podrazumevani konstruktor.
     */
    public TipClanstva() {
    }

    /**
     * Kreira novi tip clanstva sa svim potrebnim podacima.
     *
     * @param idTipClanstva jedinstveni identifikator tipa clanstva
     * @param tip naziv tipa clanstva
     * @param cena cena clanarine, mora biti veca od nule
     * @param maksimalanBrojStavki maksimalan broj stavki, mora biti veci od nule
     */
    public TipClanstva(int idTipClanstva, String tip, double cena, int maksimalanBrojStavki) {
    	
    	try {
    		setIdTipClanstva(idTipClanstva);
            setTip(tip);
            setCena(cena);
            setMaksimalanBrojStavki(maksimalanBrojStavki);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji tipa clanstva: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca jedinstveni identifikator tipa clanstva.
     *
     * @return idTipClanstva
     */
    public int getIdTipClanstva() {
        return idTipClanstva;
    }

    /**
     * Postavlja jedinstveni identifikator tipa clanstva.
     *
     * @param idTipClanstva identifikator tipa clanstva
     */
    public void setIdTipClanstva(int idTipClanstva) {
        this.idTipClanstva = idTipClanstva;
    }

    /**
     * Vraca naziv tipa clanstva.
     *
     * @return tip
     */
    public String getTip() {
        return tip;
    }

    /**
     * Postavlja naziv tipa clanstva.
     *
     * @param tip naziv tipa clanstva, ne sme biti null niti prazan
     * @throws Exception ako je tip null ili prazan
     */
    public void setTip(String tip) throws Exception {
        if (tip == null || tip.isEmpty()) {
            throw new Exception("GRESKA TIP");
        }
        this.tip = tip;
    }

    /**
     * Vraca cenu clanarine.
     *
     * @return cena
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu clanarine.
     *
     * @param cena cena clanarine, mora biti strogo veca od nule
     * @throws Exception ako je cena manja ili jednaka nuli
     */
    public void setCena(double cena) throws Exception {
        if (cena <= 0) {
            throw new Exception("GRESKA CENA");
        }
        this.cena = cena;
    }

    /**
     * Vraca maksimalan broj stavki koje clan sme da pozajmi.
     *
     * @return maksimalanBrojStavki
     */
    public int getMaksimalanBrojStavki() {
        return maksimalanBrojStavki;
    }

    /**
     * Postavlja maksimalan broj stavki koje clan sme da pozajmi.
     *
     * @param maksimalanBrojStavki maksimalan broj stavki, mora biti strogo veci od nule
     * @throws Exception ako je maksimalanBrojStavki manji ili jednak nuli
     */
    public void setMaksimalanBrojStavki(int maksimalanBrojStavki) throws Exception {
        if (maksimalanBrojStavki <= 0) {
            throw new Exception("GRESKA MaxBrStavki");
        }
        this.maksimalanBrojStavki = maksimalanBrojStavki;
    }

    /**
     * Vraca naziv tipa clanstva kao string reprezentaciju.
     *
     * @return naziv tipa clanstva
     */
    @Override
    public String toString() {
        return tip;
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
     * Dva tipa clanstva su jednaka ako imaju isti naziv tipa.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su nazivi tipa jednaki, inace false
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
        final TipClanstva other = (TipClanstva) obj;
        return Objects.equals(this.tip, other.tip);
    }

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "tipClanstva"
     */
    @Override
    public String vratiNazivTabele() {
        return "tipClanstva";
    }

    /**
     * Konvertuje ResultSet u listu TipClanstva objekata.
     *
     * @param rs rezultat SQL upita
     * @return lista TipClanstva objekata
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
            lista.add(tc);
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
        return "tip, cena, maksimalanBrojStavki";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + tip + "', " + cena + ", " + maksimalanBrojStavki;
    }

    /**
     * Vraca WHERE uslov za identifikaciju po primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "tipClanstva.idTipClanstva=" + idTipClanstva;
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
        return "tip='" + tip + "', cena=" + cena + ", maksimalanBrojStavki=" + maksimalanBrojStavki;
    }
}
