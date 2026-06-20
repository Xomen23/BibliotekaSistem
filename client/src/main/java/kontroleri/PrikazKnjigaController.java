/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cordinator.Cordinator;
import forme.PrikazKnjigaForma;
import forme.model.ModelTabeleKnjiga;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Knjiga;

/**
 *
 * @author Petar
 */
public class PrikazKnjigaController {
    
    private final PrikazKnjigaForma pkf;

    public PrikazKnjigaController(PrikazKnjigaForma pkf) {
        this.pkf = pkf;
        addActionListeners();
    }
    
    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
        pkf.setLocationRelativeTo(null);
        
    }
    
    private void pripremiFormu() {
        List<Knjiga> knjige = Komunikacija.getInstance().ucitajKnjige();
        ModelTabeleKnjiga mtk = new ModelTabeleKnjiga(knjige);
        pkf.getjTableKnjige().setModel(mtk);
    }
    
    private void addActionListeners() {
        
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKnjige().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pkf, "Oznacite knjigu koju zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
                    Knjiga k = mtk.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obisiKnjigu(k);
                        JOptionPane.showMessageDialog(pkf, "Sistem je obrisao knjigu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise knjigu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                
                }
                
                
            }
        });
        
        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int red = pkf.getjTableKnjige().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pkf, "Oznacite knjigu koju zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
                    Knjiga k = mtk.getLista().get(red);
                    
                    Cordinator.getInstance().dodajParam("knjiga", k);
                    Cordinator.getInstance().otvoriIzmeniKnjigaFormu();
                    
                }
                
                
            }
        });
        
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String naziv = pkf.getjTextFieldNaziv().getText().trim();
                String autor = pkf.getjTextFieldAutor().getText().trim();
                String zanr = pkf.getjTextFieldZanr().getText().trim();

                ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
                mtk.pretrazi(naziv, autor,zanr);
                
            }
        });
        
        
        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pripremiFormu();
                pkf.getjTextFieldNaziv().setText("");
                pkf.getjTextFieldAutor().setText("");
                pkf.getjTextFieldZanr().setText("");
                
            }
        });

        pkf.addBtnIzvezuJsonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izvezuKnjigeUJson();
            }
        });
        
        
    }


    private void izvezuKnjigeUJson() {

        ModelTabeleKnjiga mtk = (ModelTabeleKnjiga) pkf.getjTableKnjige().getModel();
        List<Knjiga> knjige = mtk.getLista();

        if (knjige == null || knjige.isEmpty()) {
            JOptionPane.showMessageDialog(pkf, "Nema knjiga za izvoz", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File("knjige_export.json"));
        int rezultat = fileChooser.showSaveDialog(pkf);

        if (rezultat != JFileChooser.APPROVE_OPTION) {
            return;
        }

        java.io.File fajl = fileChooser.getSelectedFile();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(fajl)) {
            gson.toJson(knjige, writer);
            JOptionPane.showMessageDialog(pkf, "Knjige su uspesno izvezene u JSON fajl:\n" + fajl.getAbsolutePath(), "USPEH", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(pkf, "Doslo je do greske prilikom izvoza: " + ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
        }

    }
    

    
    
}
