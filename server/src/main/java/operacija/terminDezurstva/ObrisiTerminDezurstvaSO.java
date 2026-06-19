/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.terminDezurstva;

import model.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class ObrisiTerminDezurstvaSO extends ApstraktnaGenerickaOperacija {

    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof TerminDezurstva)){
            throw new Exception("Sistem nije mogao da obrise dezurstvo");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        broker.delete((TerminDezurstva) param);
        
    }
    
}
