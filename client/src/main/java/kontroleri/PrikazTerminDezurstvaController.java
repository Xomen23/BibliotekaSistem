/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazTerminaDezurstvaForma;
import forme.model.ModelTabeleTerminDezurstva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.TerminDezurstva;

/**
 *
 * @author Petar
 */
public class PrikazTerminDezurstvaController {
    
    private final PrikazTerminaDezurstvaForma ptdf;

    public PrikazTerminDezurstvaController(PrikazTerminaDezurstvaForma ptdf) {
        this.ptdf = ptdf;
        addActionListeners();
    }

    public void ootvoriFormu() {
        pripremiFormu();
        ptdf.setVisible(true);
        ptdf.setLocationRelativeTo(null);
    }

    private void pripremiFormu() {
        
        List<TerminDezurstva> dezurstva = Komunikacija.getInstance().ucitajDezurstva();
        ModelTabeleTerminDezurstva mttd = new ModelTabeleTerminDezurstva(dezurstva);
        ptdf.getjTableDezurstva().setModel(mttd);

        
    }

    private void addActionListeners() {
        
        
        
        ptdf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ptdf.getjTableDezurstva().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ptdf, "Oznacite termin dezurstva koji zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleTerminDezurstva mttd = (ModelTabeleTerminDezurstva) ptdf.getjTableDezurstva().getModel();
                    TerminDezurstva td = mttd.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obisiDezurstvo(td);
                        JOptionPane.showMessageDialog(ptdf, "Sistem je obrisao termin dezurstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(ptdf, "Sistem ne moze da obrise termin dezurstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                
                }
                
            }
        });
        
        ptdf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = ptdf.getjTableDezurstva().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(ptdf, "Oznacite termin dezurstva koju zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleTerminDezurstva mttd = (ModelTabeleTerminDezurstva) ptdf.getjTableDezurstva().getModel();
                    TerminDezurstva td = mttd.getLista().get(red);
                    
                    Cordinator.getInstance().dodajParam("dezurstvo", td);
                    Cordinator.getInstance().otvoriIzmeniTerminDezurstvaFormu();
                    
                }
                
            }
        });
        
        
        
        ptdf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String smena = ptdf.getjTextFieldSmena().getText().trim();
                String lokacija = ptdf.getjTextFieldLokacija().getText().trim();
                
                ModelTabeleTerminDezurstva mttd = (ModelTabeleTerminDezurstva) ptdf.getjTableDezurstva().getModel();
                mttd.pretrazi(smena, lokacija);
                
                
            }
        });
        
        
        ptdf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                ptdf.getjTextFieldLokacija().setText("");
                ptdf.getjTextFieldSmena().setText("");
            
            }
        });
        
    }
    
    
    
    
}
