/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajClanaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Clan;
import model.TipClanstva;

/**
 *
 * @author Petar
 */
public class DodajClanaController {
    
    private final DodajClanaForma dcf;

    public DodajClanaController(DodajClanaForma dcf) {
        this.dcf = dcf;
        addActionListeners();
        popuniComboBox();
    }

    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        dcf.setVisible(true);
        dcf.setLocationRelativeTo(null);
    }

    private void addActionListeners() {
        
        dcf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                
                String ime = dcf.getjTextFieldIme().getText().trim();
                String prezime = dcf.getjTextFieldPrezime().getText().trim();
                String email = dcf.getjTextFieldEmail().getText().trim();
                String brojTelefona = dcf.getjTextFieldBrojTelefona().getText().trim();
                TipClanstva tc = (TipClanstva) dcf.getjComboBoxTipClanstva().getSelectedItem();
                
                StringBuilder greske = new StringBuilder();
                
                if (ime == null || ime.isEmpty()) {
                    greske.append("- Ime ne sme biti prazno.\n");
                }
                if (prezime == null || prezime.isEmpty()) {
                    greske.append("- Prezime ne sme biti prazno.\n");
                }

                if (email == null || email.isEmpty()) {
                    greske.append("- Email ne sme biti prazan.\n");
                } else if (!email.contains("@")) {
                    greske.append("- Email mora da sadrzi znak @.\n");
                }

                if (brojTelefona == null || brojTelefona.isEmpty()) {
                    greske.append("- Broj telefona ne sme biti prazan.\n");
                }
                
                if (tc == null) {
                    greske.append("- Tip clanstva ne sme biti prazan.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da kreira clana:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                

                // Dalje kod za kreiranje člana, npr. unos u bazu ili nešto drugo

                
                
                /*
                if(ime == null || ime.isEmpty() || prezime == null || prezime.isEmpty() || email == null || email.isEmpty() || !email.contains("@") ||  brojTelefona == null || brojTelefona.isEmpty() || tc == null){
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da kreira clana", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    JOptionPane.showMessageDialog(dcf, "Sistem je kreirao clana", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                
                Clan c = new Clan(-1,ime,prezime,email,brojTelefona, tc);
                JOptionPane.showMessageDialog(dcf, "Sistem je kreirao clana", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                
                
                try{
                
                    Komunikacija.getInstance().dodajClana(c);
                    JOptionPane.showMessageDialog(dcf, "Sistem je zapamtio clana", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dcf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da zapamti clana", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
        dcf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {

                int id = dcf.getId();
                String ime = dcf.getjTextFieldIme().getText().trim();
                String prezime = dcf.getjTextFieldPrezime().getText().trim();
                String email = dcf.getjTextFieldEmail().getText().trim();
                String brojTelefona = dcf.getjTextFieldBrojTelefona().getText().trim();
                TipClanstva tc = (TipClanstva) dcf.getjComboBoxTipClanstva().getSelectedItem();
                
                StringBuilder greske = new StringBuilder();
                
                if (ime == null || ime.isEmpty()) {
                    greske.append("- Ime ne sme biti prazno.\n");
                }
                if (prezime == null || prezime.isEmpty()) {
                    greske.append("- Prezime ne sme biti prazno.\n");
                }

                if (email == null || email.isEmpty()) {
                    greske.append("- Email ne sme biti prazan.\n");
                } else if (!email.contains("@")) {
                    greske.append("- Email mora da sadrzi znak @.\n");
                }

                if (brojTelefona == null || brojTelefona.isEmpty()) {
                    greske.append("- Broj telefona ne sme biti prazan.\n");
                }
                
                if (tc == null) {
                    greske.append("- Tip clanstva ne sme biti prazan.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da zapamti clana:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                Clan c = new Clan(id,ime,prezime,email,brojTelefona,tc);

                try{
                    Komunikacija.getInstance().azurirajClana(c);
                    JOptionPane.showMessageDialog(dcf, "Sistem je zapamtio clana", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dcf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dcf, "Sistem ne moze da zapamti clana", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
        
    }

    private void pripremiFormu(FormaMod formaMod) {
        
        switch (formaMod) {
            case DODAJ:
                dcf.getjButtonAzuriraj().setVisible(false);
                dcf.getjButtonDodaj().setVisible(true);
                dcf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dcf.getjButtonDodaj().setVisible(false);
                dcf.getjButtonAzuriraj().setVisible(true);
                dcf.getjButtonAzuriraj().setEnabled(true);
                
                Clan c = (Clan) Cordinator.getInstance().vratiParam("clan");
                dcf.getjTextFieldIme().setText(c.getIme());
                dcf.getjTextFieldPrezime().setText(c.getPrezime());
                dcf.getjTextFieldEmail().setText(c.getEmail());
                dcf.getjTextFieldBrojTelefona().setText(c.getBrojTelefona());
                dcf.setId(c.getIdClan());
                dcf.getjComboBoxTipClanstva().setSelectedItem(c.getTipClanstva()); // proveri 
                
                break;

            default:
                System.out.println("GRESKA");;
        }
        
    }

    private void popuniComboBox() {
        
        List<TipClanstva> clanstva = new ArrayList<>();
        clanstva = Komunikacija.getInstance().ucitajTipClanstva();
        
        for (TipClanstva tc : clanstva) {
            
            dcf.getjComboBoxTipClanstva().addItem(tc);
            
        }
        
        
    }
    
    
    
    
}
