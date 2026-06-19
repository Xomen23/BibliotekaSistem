/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajTipClanstvaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.TipClanstva;

/**
 *
 * @author Petar
 */
public class DodajTipClanstvaController {
    
    private final DodajTipClanstvaForma dtcf;

    public DodajTipClanstvaController(DodajTipClanstvaForma dtcf) {
        this.dtcf = dtcf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        dtcf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                
                StringBuilder greske = new StringBuilder();
                String tip = dtcf.getjTextFieldTip().getText().trim();
                double cena = 0;
                int maxbrStavki = 0;

                try {
                    cena = Double.parseDouble(dtcf.getjTextFieldCena().getText().trim());
                } catch (NumberFormatException e) {
                    greske.append("- Cena mora biti broj.\n");
                }

                try {
                    maxbrStavki = Integer.parseInt(dtcf.getjTextFieldMaxBrStavki().getText().trim());
                } catch (NumberFormatException e) {
                    greske.append("- Maksimalan broj stavki mora biti broj.\n");
                }

                if (maxbrStavki <= 0) {
                    greske.append("- Maksimalan broj stavki mora biti veci od 0.\n");
                }
                if (tip == null || tip.isEmpty()) {
                    greske.append("- Tip clanstva ne sme biti prazan.\n");
                }
                if (cena <= 0) {
                    greske.append("- Cena mora biti veca od 0.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da kreira tip clanstva:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TipClanstva tc = new TipClanstva(-1, tip, cena, maxbrStavki);
                JOptionPane.showMessageDialog(dtcf, "Sistem je kreirao tip clanstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                

                try {
                    Komunikacija.getInstance().dodajTipClanstva(tc);
                    JOptionPane.showMessageDialog(dtcf, "Sistem je zapamtio tip clanstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dtcf.dispose();
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da zapamti tip clanstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }


                
                /*
                String tip = dtcf.getjTextFieldTip().getText().trim();
                double cena;
                int maxbrStavki;
                try{
                    cena = Double.parseDouble(dtcf.getjTextFieldCena().getText().trim());
                    maxbrStavki = Integer.parseInt(dtcf.getjTextFieldMaxBrStavki().getText().trim());
                    
                    if(maxbrStavki <= 0 || tip == null || tip.isEmpty() || cena <= 0){
                        JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da kreira tip clanstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else{
                        JOptionPane.showMessageDialog(dtcf, "Sistem je kreirao tip clanstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                    TipClanstva tc = new TipClanstva(-1,tip,cena,maxbrStavki);
                    
                    try{

                        Komunikacija.getInstance().dodajTipClanstva(tc);
                        JOptionPane.showMessageDialog(dtcf, "Sistem je zapamtio tip clanstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        dtcf.dispose();
                    
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da zapamti tip clanstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }
                    
                }catch(Exception exc){
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da kreira tip clanstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                
                */
                
                
                
            }
        });
        
        dtcf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {
                
                int id = dtcf.getId();
                
                StringBuilder greske = new StringBuilder();
                String tip = dtcf.getjTextFieldTip().getText().trim();
                double cena = 0;
                int maxbrStavki = 0;

                try {
                    cena = Double.parseDouble(dtcf.getjTextFieldCena().getText().trim());
                } catch (NumberFormatException e) {
                    greske.append("- Cena mora biti broj.\n");
                }

                try {
                    maxbrStavki = Integer.parseInt(dtcf.getjTextFieldMaxBrStavki().getText().trim());
                } catch (NumberFormatException e) {
                    greske.append("- Maksimalan broj stavki mora biti broj.\n");
                }

                if (maxbrStavki <= 0) {
                    greske.append("- Maksimalan broj stavki mora biti veci od 0.\n");
                }
                if (tip == null || tip.isEmpty()) {
                    greske.append("- Tip clanstva ne sme biti prazan.\n");
                }
                if (cena <= 0) {
                    greske.append("- Cena mora biti veca od 0.\n");
                }

                if (greske.length() > 0) {
                    JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da zapamti tip clanstva:\n" + greske.toString(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                
                TipClanstva tc = new TipClanstva(id,tip,cena, maxbrStavki);
                

                try{
                    Komunikacija.getInstance().azurirajClanstvo(tc);
                    JOptionPane.showMessageDialog(dtcf, "Sistem je zapamtio tip clanstva", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dtcf.dispose();
                    
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dtcf, "Sistem ne moze da zapamti tip clanstva", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
        
    }
    
    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        dtcf.setVisible(true);
        dtcf.setLocationRelativeTo(null);
    }

    private void pripremiFormu(FormaMod formaMod) {
        switch (formaMod) {
            case DODAJ:
                dtcf.getjButtonAzuriraj().setVisible(false);
                dtcf.getjButtonDodaj().setVisible(true);
                dtcf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dtcf.getjButtonDodaj().setVisible(false);
                dtcf.getjButtonAzuriraj().setVisible(true);
                dtcf.getjButtonAzuriraj().setEnabled(true);
                
                TipClanstva tc = (TipClanstva) Cordinator.getInstance().vratiParam("clanstvo");
                dtcf.getjTextFieldTip().setText(tc.getTip());
                dtcf.getjTextFieldMaxBrStavki().setText(String.valueOf(tc.getMaksimalanBrojStavki()));
                dtcf.getjTextFieldCena().setText(String.valueOf(tc.getCena()));
                dtcf.setId(tc.getIdTipClanstva());
                
                
                break;
            default:
                System.out.println("GRESKA");;
        }
        
    }
    
}
