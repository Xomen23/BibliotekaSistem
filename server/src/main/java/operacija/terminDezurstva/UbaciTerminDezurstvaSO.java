/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.terminDezurstva;

import model.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje novog termina dezurstva.
 *
 * @author Petar
 */
public class UbaciTerminDezurstvaSO extends ApstraktnaGenerickaOperacija {

    
    
    /**
     * Proverava da li je prosledjen objekat tipa TerminDezurstva i da li su smena, opis i lokacija popunjeni.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof TerminDezurstva)){
            throw new Exception("Sistem nije mogao da doda dezurstvo");
        }

        TerminDezurstva td = (TerminDezurstva) param;
        if(td.getSmena() == null || td.getSmena().isEmpty()){
            throw new Exception("GRESKA SMENA");
        }
        if(td.getOpis() == null || td.getOpis().isEmpty()){
            throw new Exception("GRESKA OPIS");
        }
        if(td.getLokacija() == null || td.getLokacija().isEmpty()){
            throw new Exception("GRESKA LOKACIJA");
        }
        
    }

    /**
     * Dodaje novi termin dezurstva u bazu podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((TerminDezurstva)param);
    }
    
}
