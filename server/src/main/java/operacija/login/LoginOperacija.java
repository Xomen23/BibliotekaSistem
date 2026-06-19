/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import java.util.List;
import model.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Radnik radnik;

    public Radnik getRadnik() {
        return radnik;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Radnik)){
            throw new Exception("Ne moze da se uloguje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Radnik> sviRadnici = broker.getAll((Radnik) param,null);
        System.out.println("KLASA LoginOperacija SO " + sviRadnici);
        
        if(sviRadnici.contains((Radnik)param)){
            for (Radnik r : sviRadnici) {
                if(r.equals((Radnik) param)){
                    radnik = r;
                    return;
                }
            }
        }else{
            radnik = null;
        }
        
        
    }
    
}
