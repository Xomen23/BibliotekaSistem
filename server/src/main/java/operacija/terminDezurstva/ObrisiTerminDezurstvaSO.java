/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.terminDezurstva;

import model.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za brisanje termina dezurstva.
 *
 * @author Petar
 */
public class ObrisiTerminDezurstvaSO extends ApstraktnaGenerickaOperacija {

    
    
    /**
     * Proverava da li je prosledjen objekat tipa TerminDezurstva.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof TerminDezurstva)){
            throw new Exception("Sistem nije mogao da obrise dezurstvo");
        }
        
    }

    /**
     * Brise termin dezurstva iz baze podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        broker.delete((TerminDezurstva) param);
        
    }
    
}
