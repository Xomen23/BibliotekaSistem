/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.terminDezurstva;

import java.util.List;
import model.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class UcitajTerminDezurstvaSO extends ApstraktnaGenerickaOperacija {

    private List<TerminDezurstva> dezurstva;

    public List<TerminDezurstva> getDezurstva() {
        return dezurstva;
    }

    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        dezurstva = broker.getAll(new TerminDezurstva(), "");
        
    }
    
}
