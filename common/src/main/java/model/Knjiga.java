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
public class Knjiga implements ApstraktniDomenskiObjekat{
    
    private int idKnjiga;
    private String naziv;
    private String autor;
    private String isbn;
    private String zanr;

    public Knjiga() {
    }

    public Knjiga(int idKnjiga, String naziv, String autor, String isbn, String zanr) {
        this.idKnjiga = idKnjiga;
        this.naziv = naziv;
        this.autor = autor;
        this.isbn = isbn;
        this.zanr = zanr;
    }

    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Knjiga other = (Knjiga) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
        
            int idKnjiga = rs.getInt("knjiga.idKnjiga");
            String naziv = rs.getString("knjiga.naziv");
            String autor = rs.getString("knjiga.autor");
            String isbn = rs.getString("knjiga.isbn");
            String zanr = rs.getString("knjiga.zanr");
            
            Knjiga k = new Knjiga(idKnjiga, naziv, autor, isbn, zanr);
            lista.add(k);
        
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, autor, isbn, zanr";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + autor + "', '" + isbn + "', '" + zanr + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "knjiga.idKnjiga=" + idKnjiga;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', autor='" + autor + "', isbn='" + isbn + "', zanr='" + zanr + "'";
    }
    
    
    
    
}
