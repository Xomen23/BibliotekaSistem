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
 *
 * @author Petar
 */
public class TipClanstva implements ApstraktniDomenskiObjekat{
    
    private int idTipClanstva;
    private String tip;
    private double cena;
    private int maksimalanBrojStavki;

    public TipClanstva() {
    }

    public TipClanstva(int idTipClanstva, String tip, double cena, int maksimalanBrojStavki) {
        this.idTipClanstva = idTipClanstva;
        this.tip = tip;
        this.cena = cena;
        this.maksimalanBrojStavki = maksimalanBrojStavki;
    }

    public int getIdTipClanstva() {
        return idTipClanstva;
    }

    public void setIdTipClanstva(int idTipClanstva) {
        this.idTipClanstva = idTipClanstva;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getMaksimalanBrojStavki() {
        return maksimalanBrojStavki;
    }

    public void setMaksimalanBrojStavki(int maksimalanBrojStavki) {
        this.maksimalanBrojStavki = maksimalanBrojStavki;
    }

    @Override
    public String toString() {
        return tip;
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
        final TipClanstva other = (TipClanstva) obj;
        return Objects.equals(this.tip, other.tip);
    }

    @Override
    public String vratiNazivTabele() {
        return "tipClanstva";
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
            lista.add(tc);
        
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "tip, cena, maksimalanBrojStavki";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + tip + "', " + cena + ", " + maksimalanBrojStavki;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipClanstva.idTipClanstva=" + idTipClanstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "tip='" + tip + "', cena=" + cena + ", maksimalanBrojStavki=" + maksimalanBrojStavki;
    }
    
    
    
    
    
}
