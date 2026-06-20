/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clanovi;

import model.Clan;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za izmenu postojeceg clana biblioteke.
 *
 * @author Petar
 */
public class AzurirajClanaSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjen objekat tipa Clan i da li su ime, prezime, email i broj telefona popunjeni, kao i da je dodeljen tip clanstva.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof Clan)){
            throw new Exception("Sistem nije mogao da izmeni clana");
        }
        
        Clan c = (Clan) param;
        if(c.getIme()== null || c.getIme().isEmpty()){
            throw new Exception("GRESKA IME");
        }
        if(c.getPrezime()== null || c.getPrezime().isEmpty()){
            throw new Exception("GRESKA PREZIME");
        }
        if(c.getEmail()== null || c.getEmail().isEmpty() || !c.getEmail().contains("@")){
            throw new Exception("GRESKA EMAIL");
        }
        if(c.getBrojTelefona()== null || c.getBrojTelefona().isEmpty()){
            throw new Exception("GRESKA BROJ TELEFONA");
        }
        if(c.getTipClanstva() == null){
            throw new Exception("GRESKA TIP CLANSTVA");
        }
        
    }

    /**
     * Azurira podatke postojeceg clana u bazi podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Clan)param);
    }
    
}
