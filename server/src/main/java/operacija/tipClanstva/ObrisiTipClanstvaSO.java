/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tipClanstva;

import model.TipClanstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class ObrisiTipClanstvaSO extends ApstraktnaGenerickaOperacija {

    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof TipClanstva)){
            throw new Exception("Sistem nije mogao da obrise clanstvo");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((TipClanstva) param);
    }
    
}
