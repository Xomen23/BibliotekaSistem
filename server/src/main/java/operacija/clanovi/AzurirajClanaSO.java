/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clanovi;

import model.Clan;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class AzurirajClanaSO extends ApstraktnaGenerickaOperacija {

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

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Clan)param);
    }
    
}
