/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.terminDezurstva;

import java.util.List;
import model.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za ucitavanje liste svih termina dezurstva.
 *
 * @author Petar
 */
public class UcitajTerminDezurstvaSO extends ApstraktnaGenerickaOperacija {

    private List<TerminDezurstva> dezurstva;

    public List<TerminDezurstva> getDezurstva() {
        return dezurstva;
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
     * Ucitava sve termine dezurstva iz baze podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        dezurstva = broker.getAll(new TerminDezurstva(), "");
        
    }
    
}
