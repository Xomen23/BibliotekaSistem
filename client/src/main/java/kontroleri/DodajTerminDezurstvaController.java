/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajTerminDezurstvaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.TerminDezurstva;

/**
 *
 * @author Petar
 */
public class DodajTerminDezurstvaController {
    
    private final DodajTerminDezurstvaForma dtdf;

    public DodajTerminDezurstvaController(DodajTerminDezurstvaForma dtdf) {
        this.dtdf = dtdf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        dtdf.setVisible(true);
        dtdf.setLocationRelativeTo(null);
    }

    private void pripremiFormu(FormaMod formaMod) {
        
        switch (formaMod) {
            case DODAJ:
                dtdf.getjButtonAzuriraj().setVisible(false);
                dtdf.getjButtonDodaj().setVisible(true);
                dtdf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dtdf.getjButtonDodaj().setVisible(false);
                dtdf.getjButtonAzuriraj().setVisible(true);
                dtdf.getjButtonAzuriraj().setEnabled(true);
                
                TerminDezurstva td = (TerminDezurstva) Cordinator.getInstance().vratiParam("dezurstvo");
                dtdf.getjTextFieldSmena().setText(td.getSmena());
                dtdf.getjTextFieldOpis().setText(td.getOpis());
                dtdf.getjTextFieldLokacija().setText(td.getLokacija());
                dtdf.setId(td.getIdTerminDezurstva());

                break;
            default:
                System.out.println("GRESKA");;
        }
        
    }
    
    private void addActionListeners() {
        
        dtdf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                
                String smena = dtdf.getjTextFieldSmena().getText().trim();
                String opis = dtdf.getjTextFieldOpis().getText().trim();
                String lokacija = dtdf.getjTextFieldLokacija().getText().trim();
                
                StringBuilder greske = new StringBuilder();

                if (smena == null || smena.isEmpty()) {
                    greske.append("- Smena ne sme biti prazna.\n");
                }
                if (opis == null || opis.isEmpty()) {
                    greske.append("- Opis ne sme biti prazan.\n");
                }
                if (lokacija == null || lokacija.isEmpty()) {
                    greske.append("- Lokacija ne sme biti prazna.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dtdf, "Sistem ne moze da kreira termin dezurstva:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                /*
                if(smena == null || smena.isEmpty() || opis == null || opis.isEmpty() || lokacija == null || lokacija.isEmpty()){
                    JOptionPane.showMessageDialog(dtdf, "Sistem ne moze da kreira termin dezurstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    JOptionPane.showMessageDialog(dtdf, "Sistem je kreirao termin dezurstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                
                TerminDezurstva td = new TerminDezurstva(-1,smena,opis,lokacija);
                JOptionPane.showMessageDialog(dtdf, "Sistem je kreirao termin dezurstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                
                
                
                try{
                
                    Komunikacija.getInstance().dodajDezurstvo(td);
                    JOptionPane.showMessageDialog(dtdf, "Sistem je zapamtio termin dezurstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dtdf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dtdf, "Sistem ne moze da zapamti termin dezurstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
        dtdf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {
                
                int id = dtdf.getId();
                String smena = dtdf.getjTextFieldSmena().getText().trim();
                String opis = dtdf.getjTextFieldOpis().getText().trim();
                String lokacija = dtdf.getjTextFieldLokacija().getText().trim();
                
                StringBuilder greske = new StringBuilder();

                if (smena == null || smena.isEmpty()) {
                    greske.append("- Smena ne sme biti prazna.\n");
                }
                if (opis == null || opis.isEmpty()) {
                    greske.append("- Opis ne sme biti prazan.\n");
                }
                if (lokacija == null || lokacija.isEmpty()) {
                    greske.append("- Lokacija ne sme biti prazna.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dtdf, "Sistem ne moze da zapamti termin dezurstva:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                TerminDezurstva td = new TerminDezurstva(id,smena,opis,lokacija);

                try{
                    Komunikacija.getInstance().azurirajDezurstvo(td);
                    JOptionPane.showMessageDialog(dtdf, "Sistem je zapamtio termin dezurstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dtdf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dtdf, "Sistem ne moze da zapamti termin dezurstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
        
    }

    
    
    
    
}
