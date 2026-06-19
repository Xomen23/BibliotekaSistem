/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavke;

import java.util.List;
import model.Pozajmica;
import model.StavkaPozajmice;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Petar
 */
public class ObrisiStavkuPozajmiceSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof StavkaPozajmice)){
            throw new Exception("Sistem nije mogao da obrise stavku");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {

        StavkaPozajmice stavka = (StavkaPozajmice) param;
        broker.delete(stavka);

        
        List<Pozajmica> listaPozajmica = broker.getAll(new Pozajmica(), " join radnik ON pozajmica.idRadnik = radnik.idRadnik join clan on pozajmica.idClan = clan.idClan join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva WHERE idPozajmica = " + stavka.getPozajmica().getIdPozajmica());

        if (!listaPozajmica.isEmpty()) {
            Pozajmica pozajmica = (Pozajmica) listaPozajmica.get(0);
            List<StavkaPozajmice> preostaleStavke = broker.getAll(new StavkaPozajmice(), " join pozajmica on pozajmica.idPozajmica = stavkapozajmice.idPozajmica \n" +
                                                                                        "join radnik ON pozajmica.idRadnik = radnik.idRadnik \n" +
                                                                                        "join clan on pozajmica.idClan = clan.idClan \n" +
                                                                                        "join tipclanstva on clan.idTipClanstva = tipclanstva.idTipClanstva \n" +
                                                                                        "join knjiga on knjiga.idKnjiga = stavkapozajmice.idKnjiga \n" +
                                                                                        "where pozajmica.idPozajmica = " + pozajmica.getIdPozajmica());

            int noviBrojStavki = preostaleStavke.size();

            int novaUkupnaKazna = 0;
            for (StavkaPozajmice s : preostaleStavke) {
                novaUkupnaKazna = novaUkupnaKazna + s.getKazna();
            }

            pozajmica.setBrojStavki(noviBrojStavki);
            pozajmica.setUkupnaKazna(novaUkupnaKazna);

            broker.edit(pozajmica);
        
        }
    }
    
}
