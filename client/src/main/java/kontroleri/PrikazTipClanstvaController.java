/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazTipaClanstvaForma;
import forme.model.ModelTabeleTipClanstva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.TipClanstva;

/**
 *
 * @author Petar
 */
public class PrikazTipClanstvaController {

    private final PrikazTipaClanstvaForma ptcf;

    public PrikazTipClanstvaController(PrikazTipaClanstvaForma ptcf) {
        this.ptcf = ptcf;
        addActionListeners();
    }
    
    
    public void otvoriFormu() {
        pripremiFormu();
        ptcf.setVisible(true);
        ptcf.setLocationRelativeTo(null);
    }
    
    private void pripremiFormu() {
        List<TipClanstva> clanstva = Komunikacija.getInstance().ucitajTipClanstva();
        ModelTabeleTipClanstva mttc = new ModelTabeleTipClanstva(clanstva);
        ptcf.getjTableTipClanstva().setModel(mttc);
    }
    
    

    private void addActionListeners() {
        
        ptcf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ptcf.getjTableTipClanstva().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ptcf, "Oznacite tip clanstva koju zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleTipClanstva mttc = (ModelTabeleTipClanstva) ptcf.getjTableTipClanstva().getModel();
                    TipClanstva tc = mttc.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obisiTipClanstva(tc);
                        JOptionPane.showMessageDialog(ptcf, "Sistem je obrisao tip clanstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(ptcf, "Sistem ne moze da obrise tip clanstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                
                }
                
            }
        });
        
        
        ptcf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ptcf.getjTableTipClanstva().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ptcf, "Oznacite tip clanstva koju zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleTipClanstva mttc = (ModelTabeleTipClanstva) ptcf.getjTableTipClanstva().getModel();
                    TipClanstva tc = mttc.getLista().get(red);
                    
                    Cordinator.getInstance().dodajParam("clanstvo", tc);
                    Cordinator.getInstance().otvoriIzmeniTipClanstvaFormu();
                    
                }
                
            }
        });
        
        
        
        ptcf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String tip = ptcf.getjTextFieldTip().getText().trim();
                String maxBrStavkiSTR = ptcf.getjTextFieldMaxBrojStavki().getText().trim();
                int maxBrStavki;
                
                
                
                try{
                    if(maxBrStavkiSTR.isBlank()){
                        maxBrStavki = -1;
                    }else{
                        maxBrStavki = Integer.parseInt(maxBrStavkiSTR);
                    }
                    ModelTabeleTipClanstva mttc = (ModelTabeleTipClanstva) ptcf.getjTableTipClanstva().getModel();
                    mttc.pretrazi(tip, maxBrStavki);
                }catch(Exception exp){
                    JOptionPane.showMessageDialog(ptcf, "Pogresno uneti podaci", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exp.printStackTrace();  
                }
            }
        });
        
        
        
        
        ptcf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                ptcf.getjTextFieldTip().setText("");
                ptcf.getjTextFieldMaxBrojStavki().setText("");
                
            }
        });
        
        
    }

    
}
