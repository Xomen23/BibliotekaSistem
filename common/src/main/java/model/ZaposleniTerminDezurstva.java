/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Petar
 */
public class ZaposleniTerminDezurstva implements ApstraktniDomenskiObjekat{
    
    private Radnik radnik;
    private TerminDezurstva terminDezurstva;
    private Date datumDezurstva;
    private boolean prisutnost;

    public ZaposleniTerminDezurstva() {
    }

    public ZaposleniTerminDezurstva(Radnik radnik, TerminDezurstva terminDezurstva, Date datumDezurstva, boolean prisutnost) {
        this.radnik = radnik;
        this.terminDezurstva = terminDezurstva;
        this.datumDezurstva = datumDezurstva;
        this.prisutnost = prisutnost;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public TerminDezurstva getTerminDezurstva() {
        return terminDezurstva;
    }

    public void setTerminDezurstva(TerminDezurstva terminDezurstva) {
        this.terminDezurstva = terminDezurstva;
    }

    public Date getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(Date datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
    }

    public boolean isPrisutnost() {
        return prisutnost;
    }

    public void setPrisutnost(boolean prisutnost) {
        this.prisutnost = prisutnost;
    }

    @Override
    public String toString() {
        return "ZaposleniTerminDezurstva{" + "radnik=" + radnik + ", terminDezurstva=" + terminDezurstva + ", datumDezurstva=" + datumDezurstva + ", prisutnost=" + prisutnost + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ZaposleniTerminDezurstva other = (ZaposleniTerminDezurstva) obj;
        if (!Objects.equals(this.radnik, other.radnik)) {
            return false;
        }
        if (!Objects.equals(this.terminDezurstva, other.terminDezurstva)) {
            return false;
        }
        return Objects.equals(this.datumDezurstva, other.datumDezurstva);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleniTerminDezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idRadnik, idTerminDezurstva, datumDezurstva, prisutnost";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return radnik.getIdRadnik() + ", " + terminDezurstva.getIdTerminDezurstva() + ", '" + datumDezurstva + "', " + prisutnost;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleniTerminDezurstva.idRadnik=" + radnik.getIdRadnik() + " AND zaposleniTerminDezurstva.idTerminDezurstva=" + terminDezurstva.getIdTerminDezurstva() 
                + " AND zaposleniTerminDezurstva.datumDezurstva=" + datumDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        //return "idRadnik= " + radnik.getIdRadnik() + ", idTerminDezurstva= " + terminDezurstva.getIdTerminDezurstva() + ", datumDezurstva= '" + datumDezurstva + "', prisutnost= " + prisutnost; 
        return "prisutnost= " + prisutnost;
    }
    
    
    
    
    
    
}
