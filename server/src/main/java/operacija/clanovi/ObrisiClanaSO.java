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
public class ObrisiClanaSO extends ApstraktnaGenerickaOperacija {

    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Clan)){
            throw new Exception("Sistem nije mogao da obrise clana");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Clan) param);
    }
    
}
