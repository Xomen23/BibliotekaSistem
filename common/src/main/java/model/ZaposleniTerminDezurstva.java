package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja vezu izmedju radnika i termina dezurstva.
 * Cuva podatke o tome ko je rasporedjen na koje dezurstvo i da li je bio prisutan.
 *
 * @author Petar
 */
public class ZaposleniTerminDezurstva implements ApstraktniDomenskiObjekat {

    private Radnik radnik;
    private TerminDezurstva terminDezurstva;
    private Date datumDezurstva;
    private boolean prisutnost;

    /**
     * Podrazumevani konstruktor.
     */
    public ZaposleniTerminDezurstva() {
    }

    /**
     * Kreira novi zapis o dezurstvu radnika.
     *
     * @param radnik radnik rasporedjen na dezurstvo, ne sme biti null
     * @param terminDezurstva termin dezurstva, ne sme biti null
     * @param datumDezurstva datum dezurstva, ne sme biti null
     * @param prisutnost true ako je radnik bio prisutan
     * @throws Exception ako je neka od vrednosti nevalidna
     */
    public ZaposleniTerminDezurstva(Radnik radnik, TerminDezurstva terminDezurstva, Date datumDezurstva, boolean prisutnost) {
    	try {
    		setRadnik(radnik);
            setTerminDezurstva(terminDezurstva);
            setDatumDezurstva(datumDezurstva);
            setPrisutnost(prisutnost);
    	}catch(Exception e) {
    		System.err.println("Greska pri inicijalizaciji zaposleni-termin dezurstva: " + e.getMessage());
    	}
        
    }

    /**
     * Vraca radnika rasporedjen na dezurstvo.
     *
     * @return radnik
     */
    public Radnik getRadnik() {
        return radnik;
    }

    /**
     * Postavlja radnika rasporedjen na dezurstvo.
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
     * Vraca termin dezurstva.
     *
     * @return terminDezurstva
     */
    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    /**
     * Postavlja termin dezurstva.
     *
     * @param terminDezurstva termin dezurstva, ne sme biti null
     * @throws Exception ako je terminDezurstva null
     */
    public void setTerminDezurstva(TerminDezurstva terminDezurstva) throws Exception {
        if (terminDezurstva == null) {
            throw new Exception("GRESKA TERMIN DEZURSTVA");
        }
        this.terminDezurstva = terminDezurstva;
    }

    /**
     * Vraca datum dezurstva.
     *
     * @return datumDezurstva
     */
    public Date getDatumDezurstva() {
        return datumDezurstva;
    }

    /**
     * Postavlja datum dezurstva.
     *
     * @param datumDezurstva datum dezurstva, ne sme biti null
     * @throws Exception ako je datumDezurstva null
     */
    public void setDatumDezurstva(Date datumDezurstva) throws Exception {
        if (datumDezurstva == null) {
            throw new Exception("GRESKA DATUM DEZURSTVA");
        }
        this.datumDezurstva = datumDezurstva;
    }

    /**
     * Vraca da li je radnik bio prisutan na dezurstvu.
     *
     * @return true ako je radnik bio prisutan
     */
    public boolean isPrisutnost() {
        return prisutnost;
    }

    /**
     * Postavlja prisutnost radnika na dezurstvu.
     *
     * @param prisutnost true ako je radnik bio prisutan
     */
    public void setPrisutnost(boolean prisutnost) {
        this.prisutnost = prisutnost;
    }

    /**
     * Vraca string reprezentaciju zapisa o dezurstvu.
     *
     * @return detalji zapisa o dezurstvu
     */
    @Override
    public String toString() {
        return "ZaposleniTerminDezurstva{radnik=" + radnik + ", terminDezurstva=" + terminDezurstva
                + ", datumDezurstva=" + datumDezurstva + ", prisutnost=" + prisutnost + '}';
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
     * Dva zapisa su jednaka ako se odnose na istog radnika, isti termin i isti datum.
     *
     * @param obj objekat sa kojim se poredi
     * @return true ako su radnik, termin dezurstva i datum jednaki, inace false
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
        final ZaposleniTerminDezurstva other = (ZaposleniTerminDezurstva) obj;
        if (!Objects.equals(this.radnik, other.radnik)) {
            return false;
        }
        if (!Objects.equals(this.terminDezurstva, other.terminDezurstva)) {
            return false;
        }
        return Objects.equals(this.datumDezurstva, other.datumDezurstva);
    }

    /**
     * Vraca naziv tabele u bazi podataka.
     *
     * @return naziv tabele "zaposleniTerminDezurstva"
     */
    @Override
    public String vratiNazivTabele() {
        return "zaposleniTerminDezurstva";
    }

    /**
     * Nije implementirano.
     *
     * @param rs ResultSet
     * @return nista
     * @throws UnsupportedOperationException uvek
     */
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Vraca nazive kolona za INSERT upit.
     *
     * @return nazivi kolona odvojeni zarezom
     */
    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idRadnik, idTerminDezurstva, datumDezurstva, prisutnost";
    }

    /**
     * Vraca vrednosti za INSERT upit.
     *
     * @return vrednosti odvojene zarezom
     */
    @Override
    public String vratiVrednostiZaUbacivanje() {
        return radnik.getIdRadnik() + ", " + terminDezurstva.getIdTerminDezurstva() + ", '" + datumDezurstva + "', " + prisutnost;
    }

    /**
     * Vraca WHERE uslov za identifikaciju po kompozitnom primarnom kljucu.
     *
     * @return uslov primarnog kljuca
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleniTerminDezurstva.idRadnik=" + radnik.getIdRadnik()
                + " AND zaposleniTerminDezurstva.idTerminDezurstva=" + terminDezurstva.getIdTerminDezurstva()
                + " AND zaposleniTerminDezurstva.datumDezurstva=" + datumDezurstva;
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
        return "prisutnost= " + prisutnost;
    }
}
