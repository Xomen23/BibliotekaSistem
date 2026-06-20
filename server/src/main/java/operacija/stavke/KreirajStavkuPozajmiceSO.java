/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavke;

import model.Knjiga;
import model.Pozajmica;
import model.StavkaPozajmice;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje nove stavke pozajmice.
 *
 * @author Petar
 */
public class KreirajStavkuPozajmiceSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjen objekat tipa StavkaPozajmice i da li su pozajmica i knjiga postavljene, kao i da su redni broj i kazna validni.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof StavkaPozajmice)){
            throw new Exception("Sistem nije mogao da izmeni stavku");
        }
        
        StavkaPozajmice sp = (StavkaPozajmice) param;
        if(sp.getPozajmica() == null || !(sp.getPozajmica() instanceof Pozajmica)){
            throw new Exception("GRESKA POZAJMICA STAVKE");
        }
        if(sp.getRb() <= 0){
            throw new Exception("GRESKA REDNI BROJ");
        }
        if(sp.getKazna() <= 0){
            throw new Exception("GRESKA KAZNA");
        }
        if(sp.getKnjiga() == null || !(sp.getKnjiga() instanceof Knjiga)){
            throw new Exception("GRESKA KNJIGA STAVKE");
        }
    }

    /**
     * Dodaje novu stavku pozajmice u bazu podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum koriscen za odredjivanje nacina izvrsavanja
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((StavkaPozajmice)param);
    }
    
}
