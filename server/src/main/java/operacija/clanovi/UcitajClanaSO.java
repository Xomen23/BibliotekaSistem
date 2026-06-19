/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clanovi;

import java.util.List;
import model.Clan;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class UcitajClanaSO extends ApstraktnaGenerickaOperacija {

    private List<Clan> clanovi;

    public List<Clan> getClanovi() {
        return clanovi;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        clanovi = broker.getAll(new Clan(), " join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva");
        
    }
    
}
