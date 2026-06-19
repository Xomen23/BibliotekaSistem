/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DetaljiClanaForma;
import forme.FormaMod;
import forme.model.ModelTabeleKnjiga;
import forme.model.ModelTabelePozajmica;
import forme.model.ModelTabeleStavke;
import forme.model.ModelTabeleStavke2;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Komunikacija;
import model.Clan;
import model.Knjiga;
import model.Pozajmica;
import model.StavkaPozajmice;

/**
 *
 * @author Petar
 */
public class DetaljiClanaController {
    
    private final DetaljiClanaForma dcf;

    public DetaljiClanaController(DetaljiClanaForma dcf) {
        this.dcf = dcf;
        
        List<StavkaPozajmice> stavke = new ArrayList<>();
        ModelTabeleStavke2 mts = new ModelTabeleStavke2(stavke);
        dcf.getjTableIstorijaPozajmica().setModel(mts); 
        
        popuniTabeluStavki();
        
        
    }

    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        dcf.setVisible(true);
        dcf.setLocationRelativeTo(null);
    }
    
    
    
    private void pripremiFormu(FormaMod formaMod) {
        
        switch (formaMod) {
            case DODAJ:
                break;
            case IZMENI:
                
                Clan c = (Clan) Cordinator.getInstance().vratiParam("clanDetalji");
                dcf.setId(c.getIdClan());
                dcf.getjLabelIme().setText(c.getIme());
                dcf.getjLabelPrezime().setText(c.getPrezime());
                dcf.getjLabelEmail().setText(c.getEmail());
                dcf.getjLabelBrojTelefona().setText(c.getBrojTelefona());
                dcf.getjLabelTipClanstva().setText(c.getTipClanstva().toString());
                
                try{
                
                    List<StavkaPozajmice> stavke = Komunikacija.getInstance().ucitajStavkeClana(c);
                    ModelTabeleStavke2 mts = new ModelTabeleStavke2(stavke);
                    dcf.getjTableIstorijaPozajmica().setModel(mts); 



                    dcf.getjLabelUkupanBrojPozajmljenihKnjga().setText(String.valueOf(stavke.size()));

                    int broj_vracenih_knjiga = 0;
                    int broj_nevracenih_knjiga = 0;
                    int dugovanja = 0;

                    for (StavkaPozajmice sp : stavke) {

                        if(sp.isVraceno()){
                            broj_vracenih_knjiga++;
                            dugovanja = dugovanja + sp.getKazna();
                        }
                    }

                    broj_nevracenih_knjiga = stavke.size() - broj_vracenih_knjiga;

                    dcf.getjLabelBrojVracenihKnjiga().setText(String.valueOf(broj_vracenih_knjiga));
                    dcf.getjLabelBrojKnjigaKojeDrzi().setText(String.valueOf(broj_nevracenih_knjiga));
                    dcf.getjLabelDugovanja().setText(String.valueOf(dugovanja));
                
                
                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Greska prilikom ucitavanja stavki clana");
                }
                
                
                break;

            default:
                System.out.println("GRESKA");;
        }
        
    }

    private void popuniTabeluStavki() {
        
        
        
    }

 
    
    
}
