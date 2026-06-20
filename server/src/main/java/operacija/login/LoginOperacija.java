/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import java.util.List;
import model.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za prijavu radnika u sistem.
 * Proverava da li u bazi postoji radnik cije se korisnicko ime i sifra
 * poklapaju sa prosledjenim podacima, i ako postoji, postavlja ga kao
 * rezultat prijave.
 *
 * @author Petar
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Radnik radnik;

    public Radnik getRadnik() {
        return radnik;
    }
    
    
    /**
     * Proverava da li je prosledjen objekat tipa Radnik sa korisnickim
     * imenom i sifrom koji se koriste za prijavu.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko prosledjeni objekat nije tipa Radnik
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Radnik)){
            throw new Exception("Ne moze da se uloguje");
        }
    }

    /**
     * Pronalazi radnika u bazi cije korisnicko ime i sifra odgovaraju
     * prosledjenim podacima. Ukoliko radnik nije pronadjen, atribut
     * radnik ostaje null.
     *
     * @param param objekat tipa Radnik sa unetim korisnickim imenom i sifrom
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ukoliko dodje do greske pri citanju iz baze
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Radnik> sviRadnici = broker.getAll((Radnik) param,null);
        System.out.println("KLASA LoginOperacija SO " + sviRadnici);
        
        if(sviRadnici.contains((Radnik)param)){
            for (Radnik r : sviRadnici) {
                if(r.equals((Radnik) param)){
                    radnik = r;
                    return;
                }
            }
        }else{
            radnik = null;
        }
        
        
    }
    
}
