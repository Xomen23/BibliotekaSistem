/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pozajmice;

import java.util.List;
import model.Pozajmica;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za ucitavanje liste svih pozajmica, zajedno sa podacima o radniku, clanu i tipu clanstva.
 *
 * @author Petar
 */
public class UcitajPozajmiceSO extends ApstraktnaGenerickaOperacija {

    private List<Pozajmica> pozajmice;

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
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
     * Ucitava sve pozajmice iz baze podataka, povezane sa radnikom, clanom i tipom clanstva.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum koriscen za odredjivanje nacina izvrsavanja
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        pozajmice = broker.getAll(new Pozajmica(), " join radnik ON pozajmica.idRadnik = radnik.idRadnik join clan on pozajmica.idClan = clan.idClan join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva;");
        
    }
    
}
