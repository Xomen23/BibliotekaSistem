/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazRadnikaForma;
import forme.model.ModelTabeleRadnik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Radnik;

/**
 *
 * @author Petar
 */
public class PrikazRadnikaController {
    
    private final PrikazRadnikaForma prf;

    public PrikazRadnikaController(PrikazRadnikaForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
        prf.setLocationRelativeTo(null);
        
    }

    public void pripremiFormu() {
        
        List<Radnik> radnici = Komunikacija.getInstance().ucitajRadnike();
        ModelTabeleRadnik mtr = new ModelTabeleRadnik(radnici);
        prf.getjTableRadnici().setModel(mtr);
        
    }

    private void addActionListeners() {
        
        prf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRadnici().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prf, "Oznacite radnika kog zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleRadnik mtr = (ModelTabeleRadnik) prf.getjTableRadnici().getModel();
                    Radnik r = mtr.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obisiRadnika(r);
                        JOptionPane.showMessageDialog(prf, "Sistem je obrisao radnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da obrise radnika", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                
                }
                
                
            }
        });
        
        prf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRadnici().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(prf, "Oznacite radnika kog zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleRadnik mtr = (ModelTabeleRadnik) prf.getjTableRadnici().getModel();
                    Radnik r = mtr.getLista().get(red);
                    
                    Cordinator.getInstance().dodajParam("radnik", r);
                    Cordinator.getInstance().otvoriIzmeniRadnikaFormu();
                    
                }
                
                
            }
        });
        
        prf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = prf.getjTextFieldIme().getText().trim();
                String prezime = prf.getjTextFieldPrezime().getText().trim();
                String korisnickoIme = prf.getjTextFieldKorisnickoIme().getText().trim();

                ModelTabeleRadnik mtr = (ModelTabeleRadnik) prf.getjTableRadnici().getModel();
                mtr.pretrazi(ime, prezime,korisnickoIme);
                
            }
        });
        
        prf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremiFormu();
                prf.getjTextFieldIme().setText("");
                prf.getjTextFieldPrezime().setText("");
                prf.getjTextFieldKorisnickoIme().setText("");
            }
        });
        
        
    }
    
    
    
    
}
