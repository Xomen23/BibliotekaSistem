/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnici;

import java.util.List;
import model.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za ucitavanje liste svih radnika biblioteke.
 *
 * @author Petar
 */
public class UcitajRadnikeSO extends ApstraktnaGenerickaOperacija {

    private List<Radnik> radnici;
    
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
     * Ucitava sve radnike iz baze podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        radnici = broker.getAll(new Radnik(), "");
    }

    public List<Radnik> getRadnici() {
        return radnici;
    }
    
    
}
