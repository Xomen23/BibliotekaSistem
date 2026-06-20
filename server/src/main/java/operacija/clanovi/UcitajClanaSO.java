/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clanovi;

import java.util.List;
import model.Clan;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za ucitavanje liste svih clanova biblioteke, zajedno sa njihovim tipom clanstva.
 *
 * @author Petar
 */
public class UcitajClanaSO extends ApstraktnaGenerickaOperacija {

    private List<Clan> clanovi;

    public List<Clan> getClanovi() {
        return clanovi;
    }
    
    
    /**
     * Ova operacija nema dodatnih preduslova.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    /**
     * Ucitava sve clanove iz baze podataka, povezane sa odgovarajucim tipom clanstva.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        clanovi = broker.getAll(new Clan(), " join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva");
        
    }
    
}
