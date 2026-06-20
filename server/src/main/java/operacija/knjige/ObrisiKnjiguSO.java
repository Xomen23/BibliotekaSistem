/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjige;

import model.Knjiga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za brisanje knjige iz fonda biblioteke.
 *
 * @author Petar
 */
public class ObrisiKnjiguSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjen objekat tipa Knjiga.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof Knjiga)){
            throw new Exception("Sistem nije mogao da obrise knjigu");
        }

    }

    /**
     * Brise knjigu iz baze podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Knjiga) param);
    }
    
}
