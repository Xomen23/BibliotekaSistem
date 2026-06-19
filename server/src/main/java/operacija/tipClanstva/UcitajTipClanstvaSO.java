/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.tipClanstva;

import java.util.List;
import model.TipClanstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class UcitajTipClanstvaSO extends ApstraktnaGenerickaOperacija {

    private List<TipClanstva> clanstva;

    public List<TipClanstva> getClanstva() {
        return clanstva;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        clanstva = broker.getAll(new TipClanstva(), "");
    }
    
}
