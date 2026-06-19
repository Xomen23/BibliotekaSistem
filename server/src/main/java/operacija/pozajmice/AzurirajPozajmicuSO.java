/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.pozajmice;

import model.Pozajmica;
import model.StavkaPozajmice;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class AzurirajPozajmicuSO extends ApstraktnaGenerickaOperacija {

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

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        Pozajmica p = (Pozajmica) param;

        broker.edit(p);
        
        broker.deleteAllStavke(p);
        
        for (StavkaPozajmice sp : p.getStavke()) {
            broker.add(sp);
        }
        
    }
    
}
