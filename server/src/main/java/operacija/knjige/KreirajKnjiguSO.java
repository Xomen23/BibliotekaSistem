/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjige;

import model.Knjiga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class KreirajKnjiguSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Knjiga)){
            throw new Exception("Sistem nije mogao da doda knjgu");
        }
        
        Knjiga k = (Knjiga) param;
        if(k.getNaziv() == null || k.getNaziv().isEmpty()){
            throw new Exception("GRESKA NAZIV");
        }
        if(k.getAutor()== null || k.getAutor().isEmpty()){
            throw new Exception("GRESKA AUTOR");
        }
        if(k.getIsbn()== null || k.getIsbn().isEmpty() || k.getIsbn().length() != 13){
            throw new Exception("GRESKA ISBN");
        }
        if(k.getZanr()== null || k.getZanr().isEmpty()){
            throw new Exception("GRESKA ZANR");
        }
        
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Knjiga)param);
    }
    
}
