/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazPozajmicaForma;
import forme.model.ModelTabelePozajmica;
import forme.model.ModelTabeleStavke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Pozajmica;
import model.StavkaPozajmice;

/**
 *
 * @author Petar
 */
public class PrikazPozajmicaController {
    
    private final PrikazPozajmicaForma ppf;
    //private int selektovanaPozajmica = -1;

    public PrikazPozajmicaController(PrikazPozajmicaForma ppf) {
        this.ppf = ppf;
        addActionListeners();
    }

    
    
    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
        ppf.setLocationRelativeTo(null);   
    }
    
    private void pripremiFormu() {
        
        
        List<Pozajmica> pozajmice = Komunikacija.getInstance().ucitajPozajmice();
        ModelTabelePozajmica mtp = new ModelTabelePozajmica(pozajmice);
        ppf.getjTablePozajmice().setModel(mtp);
        
        ModelTabeleStavke mts = new ModelTabeleStavke(new ArrayList<>());
        ppf.getjTableStavke().setModel(mts);

        
        
    }
    
    public void osveziFormu(){
        
        int red = ppf.getjTablePozajmice().getSelectedRow();
        ModelTabelePozajmica mtp = (ModelTabelePozajmica) ppf.getjTablePozajmice().getModel();
        Pozajmica p = mtp.getLista().get(red);
        
        List<StavkaPozajmice> stavke = Komunikacija.getInstance().ucitajStavke(p);
        ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
        ppf.getjTableStavke().setModel(mts);
        
        
    }
    

    private void addActionListeners() {
        
        ppf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ppf.getjTableStavke().getSelectedRow();
                //int redPozajmice = ppf.getjTablePozajmice().getSelectedRow();
                
                if(red == -1){
                    JOptionPane.showMessageDialog(ppf, "Oznacite stavku koju zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleStavke mts = (ModelTabeleStavke) ppf.getjTableStavke().getModel();
                    StavkaPozajmice sp = mts.getLista().get(red);
                    
                    // NOVO
                    //ModelTabelePozajmica mtp = (ModelTabelePozajmica) ppf.getjTablePozajmice().getModel();
                    //Pozajmica p = mtp.getLista().get(redPozajmice);
                    
                    try{
                        
                        ModelTabeleStavke mtss = (ModelTabeleStavke) ppf.getjTableStavke().getModel();
                        int size = mtss.getLista().size();
                        
                        
                        if(size <= 1){
                            JOptionPane.showMessageDialog(ppf, "Pozajmica mora da ima makar jednu stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        
                        Komunikacija.getInstance().obisiStavku(sp);
                        JOptionPane.showMessageDialog(ppf, "Sistem je obrisao stavku", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                        
                        
                        //NOVO
                        /*
                        List<StavkaPozajmice> stavke = Komunikacija.getInstance().ucitajStavke(p);
                        ModelTabeleStavke mtss = new ModelTabeleStavke(stavke);
                        ppf.getjTableStavke().setModel(mtss);
                        */
                        
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(ppf, "Sistem ne moze da obrise stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                
                }
                
            }
        });
        
        ppf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ppf.getjTablePozajmice().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ppf, "Oznacite pozajmicu koju zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabelePozajmica mtp = (ModelTabelePozajmica) ppf.getjTablePozajmice().getModel();
                    Pozajmica p = mtp.getLista().get(red);
                    
                    Cordinator.getInstance().dodajParam("pozajmica", p);
                    Cordinator.getInstance().otvoriIzmeniPozajmicuFormu();
                    
                }
                
            }
        });
        
        
        ppf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String clan = ppf.getjTextFieldClan().getText().trim();
                String radnik = ppf.getjTextFieldRadnik().getText().trim();
                ModelTabelePozajmica mtp = (ModelTabelePozajmica) ppf.getjTablePozajmice().getModel();
                mtp.pretrazi(clan, radnik);

                
            }
        });
        
        ppf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremiFormu();
                ppf.getjTextFieldClan().setText("");
                ppf.getjTextFieldRadnik().setText("");
                
                List<StavkaPozajmice> stavke = new ArrayList<>();
                ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
                ppf.getjTableStavke().setModel(mts);
                
            }
        });
        
    }

    
    
    
    
}
