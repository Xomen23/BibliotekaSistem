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
public class AzurirajTipClanstvaSO extends ApstraktnaGenerickaOperacija {

    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof TipClanstva)){
            throw new Exception("Sistem nije mogao da izmeni tip clanstva");
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

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((TipClanstva)param);
    }
    
}
