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
public class TerminDezurstva implements ApstraktniDomenskiObjekat{
    
    private int idTerminDezurstva;
    private String smena;
    private String opis;
    private String lokacija;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, String smena, String opis, String lokacija) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.smena = smena;
        this.opis = opis;
        this.lokacija = lokacija;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getSmena() {
        return smena;
    }

    public void setSmena(String smena) {
        this.smena = smena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public String toString() {
        return "TerminDezurstva{" + "smena=" + smena + ", lokacija=" + lokacija + '}';
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
        final TerminDezurstva other = (TerminDezurstva) obj;
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.lokacija, other.lokacija);
    }

    @Override
    public String vratiNazivTabele() {
        return "terminDezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
        
            int idTerminDezurstva = rs.getInt("terminDezurstva.idTerminDezurstva");
            String smena = rs.getString("terminDezurstva.smena");
            String opis = rs.getString("terminDezurstva.opis");
            String lokacija = rs.getString("terminDezurstva.lokacija");
            
            TerminDezurstva td = new TerminDezurstva(idTerminDezurstva, smena, opis, lokacija);
            lista.add(td);
            
            
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "smena, opis, lokacija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + smena + "', '" + opis + "', '" + lokacija + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
       return "terminDezurstva.idTerminDezurstva=" + idTerminDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "smena='" + smena + "', opis='" + opis + "', lokacija='" + lokacija + "'";
    }
    
    
    
    
    
}
