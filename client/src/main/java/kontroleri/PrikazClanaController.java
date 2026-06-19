/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazClanaForma;
import forme.model.ModelTabeleClan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Clan;

/**
 *
 * @author Petar
 */
public class PrikazClanaController {
    
    private final PrikazClanaForma pcf;
    private DodajPozajmicuController dpc;

    public PrikazClanaController(PrikazClanaForma pcf) {
        this.pcf = pcf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pcf.setVisible(true);
        pcf.setLocationRelativeTo(null);
        
    }
    
    private void pripremiFormu() {
        
        List<Clan> clanovi = Komunikacija.getInstance().ucitajClanove();
        ModelTabeleClan mtc = new ModelTabeleClan(clanovi);
        pcf.getjTableClanovi().setModel(mtc);
        
        
    }

    private void addActionListeners() {
        
        pcf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = pcf.getjTableClanovi().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pcf, "Oznacite clana koga zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleClan mtc = (ModelTabeleClan) pcf.getjTableClanovi().getModel();
                    Clan c = mtc.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obisiClana(c);
                        JOptionPane.showMessageDialog(pcf, "Sistem je obrisao clana", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(pcf, "Sistem ne moze da obrise clana", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                
                }
                
            }
        });
        
        pcf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = pcf.getjTableClanovi().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pcf, "Oznacite clana koga zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleClan mtc = (ModelTabeleClan) pcf.getjTableClanovi().getModel();
                    Clan c = mtc.getLista().get(red);
                    
                    
                    Cordinator.getInstance().dodajParam("clan", c);
                    Cordinator.getInstance().otvoriIzmeniClanaFormu();
                    
                }
                
            }
        });
        
        pcf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = pcf.getjTableClanovi().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pcf, "Oznacite clana za kojeg cete prikazati detalje", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleClan mtc = (ModelTabeleClan) pcf.getjTableClanovi().getModel();
                    Clan c = mtc.getLista().get(red);
                    
                    
                    Cordinator.getInstance().dodajParam("clanDetalji", c);
                    Cordinator.getInstance().otvoriDetaljiClanaFormu();
                }
                
            }
        });
        
        pcf.addBtnKreirajPozajmicuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = pcf.getjTableClanovi().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pcf, "Oznacite clana za kojeg cete kreirati pozajmicu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleClan mtc = (ModelTabeleClan) pcf.getjTableClanovi().getModel();
                    Clan c = mtc.getLista().get(red);
                    
                    
                    Cordinator.getInstance().dodajParam("clan", c);
                    Cordinator.getInstance().otvoriDodajPozajmicuFormu();
                    //Cordinator.getInstance().setKreirajnaPozajmica(true);
                }
                
            }
        });
        
        
        pcf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = pcf.getjTextFieldIme().getText().trim();
                String prezime = pcf.getjTextFieldPrezime().getText().trim();
                String email = pcf.getjTextFieldEmail().getText().trim();
                
                ModelTabeleClan mtc = (ModelTabeleClan) pcf.getjTableClanovi().getModel();
                mtc.pretrazi(ime, prezime, email);
                
                
            }
        });
        
        
        pcf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremiFormu();
                pcf.getjTextFieldIme().setText("");
                pcf.getjTextFieldPrezime().setText("");
                pcf.getjTextFieldEmail().setText("");
                
            }
        });
        
    }

    
    
    
    
    
}
