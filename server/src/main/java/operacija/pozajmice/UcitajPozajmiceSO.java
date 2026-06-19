/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pozajmice;

import java.util.List;
import model.Pozajmica;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class UcitajPozajmiceSO extends ApstraktnaGenerickaOperacija {

    private List<Pozajmica> pozajmice;

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        pozajmice = broker.getAll(new Pozajmica(), " join radnik ON pozajmica.idRadnik = radnik.idRadnik join clan on pozajmica.idClan = clan.idClan join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva;");
        
    }
    
}
