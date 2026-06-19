/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavke;

import java.util.List;
import model.Clan;
import model.Pozajmica;
import model.StavkaPozajmice;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class UcitajStavkePozajmiceSO extends ApstraktnaGenerickaOperacija {

    private List<StavkaPozajmice> stavke;

    public List<StavkaPozajmice> getStavke() {
        return stavke;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        if(kljuc != null && kljuc.equals("clan")){
        
            Clan c = (Clan)param;
        
            stavke = broker.getAll(new StavkaPozajmice(),   " join pozajmica on pozajmica.idPozajmica = stavkapozajmice.idPozajmica \n" +
                                                            "join radnik ON pozajmica.idRadnik = radnik.idRadnik \n" +
                                                            "join clan on pozajmica.idClan = clan.idClan \n" +
                                                            "join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva \n" +
                                                            "join knjiga on knjiga.idKnjiga = stavkapozajmice.idKnjiga \n" +
                                                            "WHERE pozajmica.idClan = " + c.getIdClan() +"\n" +
                                                            "order by stavkapozajmice.vraceno asc;");
        
            return;
        }
        
        
        Pozajmica p = (Pozajmica)param;
        
        stavke = broker.getAll(new StavkaPozajmice(),   " join pozajmica on pozajmica.idPozajmica = stavkapozajmice.idPozajmica \n" +
                                                        "join radnik ON pozajmica.idRadnik = radnik.idRadnik \n" +
                                                        "join clan on pozajmica.idClan = clan.idClan \n" +
                                                        "join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva \n" +
                                                        "join knjiga on knjiga.idKnjiga = stavkapozajmice.idKnjiga \n" +
                                                        "where pozajmica.idPozajmica = "+ p.getIdPozajmica() +"\n" +
                                                        "order by rb ASC; ");

        
    }
    
}
