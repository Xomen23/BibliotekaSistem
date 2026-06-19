/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.io.Serializable;
import java.util.List;

/**
 * Zajednički interfejs koji implementiraju sve domenske klase sistema.
 * 
 * Definiše skup metoda potrebnih da bi generička infrastruktura na serveru
 * (broker/repozitorijum) mogla da mapira bilo koji domenski objekat na
 * odgovarajuću SQL operaciju (SELECT, INSERT, UPDATE) bez posebnog koda
 * za svaku klasu pojedinačno.
 *
 * @author Petar
 */
public interface ApstraktniDomenskiObjekat extends Serializable{
    
    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovoj domenskoj klasi.
     *
     * @return naziv tabele
     */
    public String vratiNazivTabele();
    
    /**
     * Konvertuje rezultat SQL upita u listu domenskih objekata.
     *
     * @param rs rezultat SQL upita (ResultSet)
     * @return lista domenskih objekata kreiranih na osnovu rezultata upita
     * @throws Exception ukoliko dođe do greške pri čitanju rezultata
     */
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    /**
     * Vraća listu naziva kolona koje se koriste prilikom INSERT operacije.
     *
     * @return zarezom razdvojena lista naziva kolona
     */
    public String vratiKoloneZaUbacivanje();
    
    /**
     * Vraća listu vrednosti koje se koriste prilikom INSERT operacije,
     * formatiranih za direktnu upotrebu u SQL upitu.
     *
     * @return zarezom razdvojena lista vrednosti
     */
    public String vratiVrednostiZaUbacivanje();
    
    /**
     * Vraća uslov (WHERE klauzula) za pronalaženje ovog objekta po
     * njegovom primarnom ključu.
     *
     * @return tekstualna reprezentacija uslova primarnog ključa
     */
    public String vratiPrimarniKljuc();
    
    /**
     * Kreira jedan domenski objekat na osnovu trenutnog reda u rezultatu SQL upita.
     *
     * @param rs rezultat SQL upita (ResultSet) pozicioniran na željeni red
     * @return domenski objekat kreiran na osnovu tog reda
     * @throws Exception ukoliko dođe do greške pri čitanju rezultata
     */
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    /**
     * Vraća listu parova kolona i vrednosti koja se koristi prilikom
     * UPDATE operacije.
     *
     * @return tekstualna reprezentacija izmenjenih vrednosti, spremna za SET klauzulu
     */
    public String vratiVrednostiZaIzmenu();
    
}
