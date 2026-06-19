/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajRadnikaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Radnik;

/**
 *
 * @author Petar
 */
public class DodajRadnikaController {
    
    private final DodajRadnikaForma drf;

    public DodajRadnikaController(DodajRadnikaForma drf) {
        this.drf = drf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        drf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                
                String ime = drf.getjTextFieldIme().getText().trim();
                String prezime = drf.getjTextFieldPrezime().getText().trim();
                String jmbg = drf.getjTextFieldJmbg().getText().trim();
                String brojTelefona = drf.getjTextFieldBrojTelefona().getText().trim();
                String korisnickoIme = drf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = drf.getjTextFieldSifra().getText().trim();
                
                StringBuilder greske = new StringBuilder();

                if (ime == null || ime.isEmpty()) {
                    greske.append("- Ime ne sme biti prazno.\n");
                }

                if (prezime == null || prezime.isEmpty()) {
                    greske.append("- Prezime ne sme biti prazno.\n");
                }

                if (jmbg == null || jmbg.isEmpty()) {
                    greske.append("- JMBG ne sme biti prazan.\n");
                } else if (jmbg.length() != 13) {
                    greske.append("- JMBG mora imati tacno 13 karaktera.\n");
                }

                if (brojTelefona == null || brojTelefona.isEmpty()) {
                    greske.append("- Broj telefona ne sme biti prazan.\n");
                }

                if (korisnickoIme == null || korisnickoIme.isEmpty()) {
                    greske.append("- Korisnicko ime ne sme biti prazno.\n");
                }

                if (sifra == null || sifra.isEmpty()) {
                    greske.append("- Sifra ne sme biti prazna.\n");
                }

                if (greske.length() > 0) {
                    greske.append("\nSistem ne moze da kreira radnika.");
                    JOptionPane.showMessageDialog(drf, greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                

                
                
                /*
                if(ime == null || ime.isEmpty() || prezime == null || prezime.isEmpty() || jmbg == null || jmbg.isEmpty() || jmbg.length() != 13 ||  brojTelefona == null || brojTelefona.isEmpty()  
                        ||  korisnickoIme == null || korisnickoIme.isEmpty() ||  sifra == null || sifra.isEmpty()){
                    JOptionPane.showMessageDialog(drf, "Sistem ne moze da kreira radnika", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    JOptionPane.showMessageDialog(drf, "Sistem je kreirao radnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                
                Radnik r = new Radnik(-1,ime,prezime,jmbg,korisnickoIme,brojTelefona,sifra);
                JOptionPane.showMessageDialog(drf, "Sistem je kreirao radnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                
                //Radnik r = (Radnik) Cordinator.getInstance().vratiParam("radnik");
                
                try{
                    Komunikacija.getInstance().dodajRadnika(r);
                    JOptionPane.showMessageDialog(drf, "Sistem je zapamtio radnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    drf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(drf, "Sistem ne moze da zapamti radnika", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
                
            }
        });
        
        drf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {       
                
                int id = drf.getId();
                String ime = drf.getjTextFieldIme().getText().trim();
                String prezime = drf.getjTextFieldPrezime().getText().trim();
                String jmbg = drf.getjTextFieldJmbg().getText().trim();
                String brojTelefona = drf.getjTextFieldBrojTelefona().getText().trim();
                String korisnickoIme = drf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra = drf.getjTextFieldSifra().getText().trim();
                
                StringBuilder greske = new StringBuilder();

                if (ime == null || ime.isEmpty()) {
                    greske.append("- Ime ne sme biti prazno.\n");
                }

                if (prezime == null || prezime.isEmpty()) {
                    greske.append("- Prezime ne sme biti prazno.\n");
                }

                if (jmbg == null || jmbg.isEmpty()) {
                    greske.append("- JMBG ne sme biti prazan.\n");
                } else if (jmbg.length() != 13) {
                    greske.append("- JMBG mora imati tacno 13 karaktera.\n");
                }

                if (brojTelefona == null || brojTelefona.isEmpty()) {
                    greske.append("- Broj telefona ne sme biti prazan.\n");
                }

                if (korisnickoIme == null || korisnickoIme.isEmpty()) {
                    greske.append("- Korisnicko ime ne sme biti prazno.\n");
                }

                if (sifra == null || sifra.isEmpty()) {
                    greske.append("- Sifra ne sme biti prazna.\n");
                }

                if (greske.length() > 0) {
                    greske.append("\nSistem ne moze da zapamti radnika.");
                    JOptionPane.showMessageDialog(drf, greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                Radnik r = new Radnik(id,ime,prezime,jmbg,korisnickoIme,brojTelefona,sifra);
                
                
                //Radnik r = (Radnik) Cordinator.getInstance().vratiParam("radnik");
                

                try{
                    Komunikacija.getInstance().azurirajRadnika(r);
                    JOptionPane.showMessageDialog(drf, "Sistem je zapamtio radnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    drf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(drf, "Sistem ne moze da zapamti radnika", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
                
            }
        });
        
    }
    
    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        drf.setVisible(true);
        drf.setLocationRelativeTo(null);
    }

    private void pripremiFormu(FormaMod mod) {
        
        switch (mod) {
            case DODAJ:
                drf.getjButtonAzuriraj().setVisible(false);
                drf.getjButtonDodaj().setVisible(true);
                drf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                drf.getjButtonDodaj().setVisible(false);
                drf.getjButtonAzuriraj().setVisible(true);
                drf.getjButtonAzuriraj().setEnabled(true);
                
                Radnik r = (Radnik) Cordinator.getInstance().vratiParam("radnik");
                drf.getjTextFieldIme().setText(r.getIme());
                drf.getjTextFieldPrezime().setText(r.getPrezime());
                drf.getjTextFieldJmbg().setText(r.getJmbg());
                drf.getjTextFieldBrojTelefona().setText(r.getBrojTelefona());
                drf.getjTextFieldKorisnickoIme().setText(r.getKorisnickoIme());
                drf.getjTextFieldSifra().setText(r.getSifra());
                drf.setId(r.getIdRadnik());
                
                break;
            default:
                System.out.println("GRESKA");;
        }
        
    }
    
}
