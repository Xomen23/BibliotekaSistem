/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tipClanstva;

import model.TipClanstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje novog tipa clanstva.
 *
 * @author Petar
 */
public class KreirajTipClanstvaSO extends ApstraktnaGenerickaOperacija {

    
    
    /**
     * Proverava da li je prosledjen objekat tipa TipClanstva, da li je naziv tipa popunjen, i da su cena i maksimalan broj stavki veci od nule.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof TipClanstva)){
            throw new Exception("Sistem nije mogao da doda tip clanstva");
        }
        
        TipClanstva tc = (TipClanstva) param;
        if(tc.getTip() == null || tc.getTip().isEmpty()){
            throw new Exception("GRESKA TIP");
        }
        if(tc.getCena() <= 0){
            throw new Exception("GRESKA CENA");
        }
        if(tc.getMaksimalanBrojStavki()<= 0){
            throw new Exception("GRESKA MaxBrStavki");
        }
    }

    /**
     * Dodaje novi tip clanstva u bazu podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((TipClanstva)param);
    }
    
}
