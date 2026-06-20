/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pozajmice;

import java.util.List;
import model.Pozajmica;
import model.StavkaPozajmice;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za kreiranje nove pozajmice.
 *
 * @author Petar
 */
public class KreirajPozajmicuSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjen objekat tipa Pozajmica i da li su datum pozajmice, rok vracanja, status, nacin preuzimanja, clan i radnik popunjeni, kao i da broj stavki i ukupna kazna nisu negativni.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Pozajmica)){
            throw new Exception("Sistem nije mogao da izmeni pozajmicu");
        }
        
        Pozajmica p = (Pozajmica) param;
        if(p.getDatumPozajmice() == null){
            throw new Exception("GRESKA DATUM POZAJMICE");
        }
        if(p.getRokVracanja()== null){
            throw new Exception("GRESKA ROK VRACANJA");
        }
        if(p.getStatus() == null || p.getStatus().isEmpty()){
            throw new Exception("GRESKA STATUS");
        }
        if(p.getNacinPreuzimanja() == null || p.getNacinPreuzimanja().isEmpty()){
            throw new Exception("GRESKA NACIN PREUZIMANJA");
        }
        if(p.getBrojStavki() < 0){
            throw new Exception("GRESKA BROJ STAVKI");
        }
        if(p.getUkupnaKazna() < 0){
            throw new Exception("GRESKA UKUPNA KAZNA");
        }
        if(p.getClan() == null){
            throw new Exception("GRESKA CLAN");
        }
        if(p.getRadnik() == null){
            throw new Exception("GRESKA RADNIK");
        }
    }

    /**
     * Dodaje novu pozajmicu u bazu podataka, a zatim dodaje i sve njene stavke, povezujuci ih sa novokreiranom pozajmicom.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum koriscen za odredjivanje nacina izvrsavanja
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        int idPozajmica = broker.add((Pozajmica)param);
        
        Pozajmica p = (Pozajmica) param;
        List<StavkaPozajmice> lista = p.getStavke();
        
        
        for (StavkaPozajmice sp : lista) {
            sp.getPozajmica().setIdPozajmica(idPozajmica);
            broker.add(sp);
        }
        
    }
    
}
