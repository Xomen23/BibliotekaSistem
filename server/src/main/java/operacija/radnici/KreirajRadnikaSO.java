/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnici;

import model.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje novog radnika biblioteke.
 *
 * @author Petar
 */
public class KreirajRadnikaSO extends ApstraktnaGenerickaOperacija {

    
    
    
    /**
     * Proverava da li je prosledjen objekat tipa Radnik i da li su ime, prezime, korisnicko ime, broj telefona i sifra popunjeni, kao i da JMBG ima tacno 13 karaktera.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Radnik)){
            throw new Exception("Sistem nije mogao da doda radnika");
        }
        
        Radnik r = (Radnik) param;
        if(r.getIme() == null || r.getIme().isEmpty()){
            throw new Exception("GRESKA IME");
        }
        
        if(r.getPrezime()== null || r.getPrezime().isEmpty()){
            throw new Exception("GRESKA PREZIME");
        }
        
        if(r.getJmbg()== null || r.getJmbg().isEmpty() || r.getJmbg().length() != 13){
            throw new Exception("GRESKA JMBG");
        }
        
        if(r.getKorisnickoIme()== null || r.getKorisnickoIme().isEmpty()){
            throw new Exception("GRESKA KORISNICKO IME");
        }
        
        if(r.getBrojTelefona()== null || r.getBrojTelefona().isEmpty()){
            throw new Exception("GRESKA BROJ TELEFONA");
        }
        
        if(r.getSifra()== null || r.getSifra().isEmpty()){
            throw new Exception("GRESKA SIFRA");
        }
        
    }

    /**
     * Dodaje novog radnika u bazu podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Radnik)param);
    }
    
    
    
}
