/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajKnjiguForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Knjiga;

/**
 *
 * @author Petar
 */
public class DodajKnjiguController {
    
    private final DodajKnjiguForma dkf;

    public DodajKnjiguController(DodajKnjiguForma dkf) {
        this.dkf = dkf;
        addActionListeners();
        
    }
    

    private void addActionListeners() {
        
        dkf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                
                String naziv = dkf.getjTextFieldNaziv().getText().trim();
                String autor = dkf.getjTextFieldAutor().getText().trim();
                String isbn = dkf.getjTextFieldIsbn().getText().trim();
                String zanr = dkf.getjTextFieldZanr().getText().trim();
                
                
                StringBuilder greske = new StringBuilder();

                if (naziv == null || naziv.isEmpty()) {
                    greske.append("- Naziv knjige ne sme biti prazan.\n");
                }
                if (autor == null || autor.isEmpty()) {
                    greske.append("- Autor knjige ne sme biti prazan.\n");
                }
                if (isbn == null || isbn.isEmpty()) {
                    greske.append("- ISBN ne sme biti prazan.\n");
                } else if (isbn.length() != 13) {
                    greske.append("- ISBN mora imati tacno 13 karaktera.\n");
                }
                if (zanr == null || zanr.isEmpty()) {
                    greske.append("- Zanr knjige ne sme biti prazan.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da kreira knjigu:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                

                
                
                
                /*
                if(naziv == null || naziv.isEmpty() || autor == null || autor.isEmpty() || isbn == null || isbn.isEmpty() || isbn.length() != 13 ||  zanr == null || zanr.isEmpty()){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da kreira knjigu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    JOptionPane.showMessageDialog(dkf, "Sistem je kreirao knjigu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                
                Knjiga k = new Knjiga(-1,naziv,autor,isbn,zanr);
                JOptionPane.showMessageDialog(dkf, "Sistem je kreirao knjigu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                
                
                
                try{
                
                    Komunikacija.getInstance().dodajKnjigu(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio knjigu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti knjigu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
        dkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {
                
                int id = dkf.getId();
                String naziv = dkf.getjTextFieldNaziv().getText().trim();
                String autor = dkf.getjTextFieldAutor().getText().trim();
                String isbn = dkf.getjTextFieldIsbn().getText().trim();
                String zanr = dkf.getjTextFieldZanr().getText().trim();
                
                StringBuilder greske = new StringBuilder();

                if (naziv == null || naziv.isEmpty()) {
                    greske.append("- Naziv knjige ne sme biti prazan.\n");
                }
                if (autor == null || autor.isEmpty()) {
                    greske.append("- Autor knjige ne sme biti prazan.\n");
                }
                if (isbn == null || isbn.isEmpty()) {
                    greske.append("- ISBN ne sme biti prazan.\n");
                } else if (isbn.length() != 13) {
                    greske.append("- ISBN mora imati tacno 13 karaktera.\n");
                }
                if (zanr == null || zanr.isEmpty()) {
                    greske.append("- Zanr knjige ne sme biti prazan.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti knjigu:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                Knjiga k = new Knjiga(id,naziv,autor,isbn,zanr);

                try{
                    Komunikacija.getInstance().azurirajKnjigu(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio knjigu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti knjigu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        

    }

    
    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        dkf.setVisible(true);
        dkf.setLocationRelativeTo(null);
    }

    private void pripremiFormu(FormaMod formaMod) {
        switch (formaMod) {
            case DODAJ:
                dkf.getjButtonAzuriraj().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonAzuriraj().setVisible(true);
                dkf.getjButtonAzuriraj().setEnabled(true);
                
                Knjiga k = (Knjiga) Cordinator.getInstance().vratiParam("knjiga");
                dkf.getjTextFieldNaziv().setText(k.getNaziv());
                dkf.getjTextFieldAutor().setText(k.getAutor());
                dkf.getjTextFieldIsbn().setText(k.getIsbn());
                dkf.getjTextFieldZanr().setText(k.getZanr());
                dkf.setId(k.getIdKnjiga());
                
                break;
            default:
                System.out.println("GRESKA");;
        }
        
    }

   
    
}
