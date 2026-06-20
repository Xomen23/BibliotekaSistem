/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.knjige;

import model.Knjiga;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * Sistemska operacija za izmenu postojece knjige u fondu biblioteke.
 *
 * @author Petar
 */
public class AzurirajKnjiguSO extends ApstraktnaGenerickaOperacija {

    /**
     * Proverava da li je prosledjen objekat tipa Knjiga i da li su naziv, autor i zanr popunjeni, kao i da ISBN ima tacno 13 karaktera.
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof Knjiga)){
            throw new Exception("Sistem nije mogao da izmeni knjigu");
        }
        
        Knjiga k = (Knjiga) param;
        
        if(k.getNaziv() == null || k.getNaziv().isEmpty()){
            throw new Exception("GRESKA NAZIV");
        }
        
        if(k.getAutor() == null || k.getAutor().isEmpty()){
            throw new Exception("GRESKA AUTOR");
        }
        
        if(k.getIsbn() == null || k.getIsbn().isEmpty() || k.getIsbn().length() != 13){
            throw new Exception("GRESKA ISBN");
        }
        
        if(k.getZanr()== null || k.getZanr().isEmpty()){
            throw new Exception("GRESKA ZANR");
        }
                    
        
    }

    /**
     * Azurira podatke postojece knjige u bazi podataka.
     *
     * @param param objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni kljuc/kriterijum (nije obavezno koriscen u ovoj operaciji)
     * @throws Exception ukoliko dodje do greske pri izvrsavanju operacije
     */
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Knjiga)param);
    }
    
}

 
