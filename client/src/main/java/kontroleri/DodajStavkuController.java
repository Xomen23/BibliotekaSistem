/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajStavkuForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Knjiga;
import model.Pozajmica;
import model.StavkaPozajmice;

/**
 *
 * @author Petar
 */
public class DodajStavkuController {
    
    private final DodajStavkuForma dsf;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");

    public DodajStavkuController(DodajStavkuForma dsf) {
        this.dsf = dsf;
        addActionListeners();
        popuniComboBox();
    }

    public void otvoriFormu(FormaMod formaMod) {
        pripremiFormu(formaMod);
        dsf.setVisible(true);
        dsf.setLocationRelativeTo(null);
    }

    private void addActionListeners() {
        
        dsf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                
                Pozajmica p = (Pozajmica) Cordinator.getInstance().vratiParam("pozajmica");
                int rb = (int) Cordinator.getInstance().vratiParam("rb");
                
                try {
                    Date datumVracanja = null;
                    if(!dsf.getjTextFieldDatumVracanja().getText().equals("/")){
                        datumVracanja = dateFormat.parse(dsf.getjTextFieldDatumVracanja().getText().trim());

                    }       
                    int kazna = Integer.parseInt(dsf.getjTextFieldKazna().getText());
                    Knjiga k = (Knjiga) dsf.getjComboBoxKnjige().getSelectedItem();
                    boolean vraceno;
                    if(dsf.getjComboBoxVraceno().getSelectedItem().equals("DA")){
                        vraceno = true;
                    }else{
                        vraceno = false;
                    }
                    
                    StavkaPozajmice sp = new StavkaPozajmice(rb,p,datumVracanja,vraceno,kazna,k);
                    
                    // NOVO
                    
                    p.getStavke().add(sp);
                    Cordinator.getInstance().osveziDodajPozajmicuFormu();
                        
                    
                    
                    
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio stavku", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dsf.dispose();
                    
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dsf, "Datum mora biti u formatu dd.MM.yyyy. (22.12.2003.)", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dsf, "Kazna mora biti broj", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
                
            }
        });
        
        
        dsf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {
                
                Pozajmica pozajmica = dsf.getPozajmica();
                int rb = dsf.getRb();
                try {
                    Date datumVracanja = null;
                    if(!dsf.getjTextFieldDatumVracanja().getText().equals("/")){
                        datumVracanja = dateFormat.parse(dsf.getjTextFieldDatumVracanja().getText().trim());

                    }
                    boolean vraceno;
                    if(dsf.getjComboBoxVraceno().getSelectedItem().equals("DA")){
                        vraceno = true;
                    }else{
                        vraceno = false;
                    }
                    int kazna = Integer.parseInt(dsf.getjTextFieldKazna().getText());
                    Knjiga k = (Knjiga) dsf.getjComboBoxKnjige().getSelectedItem();
                    
                    
                    StavkaPozajmice sp = new StavkaPozajmice(rb, pozajmica, datumVracanja, vraceno, kazna, k);
                    
                    // NOVO
                    Pozajmica p = (Pozajmica) Cordinator.getInstance().vratiParam("pozajmica");
                    
                    
                        
                        List<StavkaPozajmice> lista = p.getStavke();

                        for (int i = 0; i < lista.size(); i++) {
                            if (lista.get(i) != null && lista.get(i).equals(sp)) {
                                lista.set(i, sp);
                                Cordinator.getInstance().osveziDodajPozajmicuFormu();
                                break;
                            }
                        }
                        
                    
                    
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio stavku", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dsf.dispose();
                    
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dsf, "Datum mora biti u formatu dd.MM.yyyy. (22.12.2003.)", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dsf, "Kazna mora biti broj", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti pozajmicu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    exc.printStackTrace();
                }
                
            }
        });
        
    }

    private void pripremiFormu(FormaMod formaMod) {
        
        switch (formaMod) {
            case DODAJ:
                dsf.getjButtonAzuriraj().setVisible(false);
                dsf.getjButtonDodaj().setVisible(true);
                dsf.getjButtonDodaj().setEnabled(true);
                
                dsf.getjComboBoxVraceno().setSelectedItem("NE");
                dsf.getjComboBoxVraceno().setEnabled(false);
                
                dsf.getjTextFieldDatumVracanja().setText("/");
                dsf.getjTextFieldDatumVracanja().setEnabled(false);
                
                
                break;
            case IZMENI:
                dsf.getjButtonDodaj().setVisible(false);
                dsf.getjButtonAzuriraj().setVisible(true);
                dsf.getjButtonAzuriraj().setEnabled(true);
                
                StavkaPozajmice sp = (StavkaPozajmice) Cordinator.getInstance().vratiParam("stavka");
                
                if(sp.getDatumVracanja() != null){
                    dsf.getjTextFieldDatumVracanja().setText(dateFormat.format(sp.getDatumVracanja()));

                }else{
                    dsf.getjTextFieldDatumVracanja().setText("/");

                }
                
                dsf.getjTextFieldKazna().setText(String.valueOf(sp.getKazna()));
                dsf.getjComboBoxKnjige().setSelectedItem(sp.getKnjiga());
                if(sp.isVraceno()){
                    dsf.getjComboBoxVraceno().setSelectedItem("DA");
                }else{
                    dsf.getjComboBoxVraceno().setSelectedItem("NE");
                }
                dsf.setPozajmica(sp.getPozajmica());
                dsf.setRb(sp.getRb());
                
                
                break;
            default:
                System.out.println("GRESKA");;
        }
        
    }

    private void popuniComboBox() {
        
        List<Knjiga> knjige = new ArrayList<>();
        knjige = Komunikacija.getInstance().ucitajKnjige();
        
        for (Knjiga k : knjige) {
            dsf.getjComboBoxKnjige().addItem(k);
        }
        
        
    }
    
    
    
}
