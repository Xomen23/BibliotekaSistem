/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajPozajmicuForma;
import forme.FormaMod;
import forme.model.ModelTabeleStavke;
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
import model.Clan;
import model.Pozajmica;
import model.Radnik;
import model.StavkaPozajmice;

/**
 *
 * @author Petar
 */
public class DodajPozajmicuController {
 
    private final DodajPozajmicuForma dpf;
    //private boolean kreirajnaPozajmica = false;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");

    
    public DodajPozajmicuController(DodajPozajmicuForma dpf) {
        this.dpf = dpf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod formaMod) {
        
        List<StavkaPozajmice> lista = new ArrayList<>();
        ModelTabeleStavke mts = new ModelTabeleStavke(lista);
        dpf.getjTableStavke().setModel(mts);
        
        pripremiFormu(formaMod);
        dpf.setVisible(true);
        dpf.setLocationRelativeTo(null);
        
        
    }
    
    
    private void pripremiFormu(FormaMod formaMod) {
        
        switch (formaMod) {
            case DODAJ:
                //dpf.getjButtonAzuriraj().setVisible(false);
                //dpf.getjButtonDodaj().setVisible(true);
                //dpf.getjButtonDodaj().setEnabled(true);
                
                dpf.getjTextFieldRadnik().setText(Cordinator.getInstance().getUlogovani().toString());
                dpf.getjTextFieldRadnik().setEnabled(false);
                
                Clan c = (Clan) Cordinator.getInstance().vratiParam("clan");
                dpf.getjTextFieldClan().setText(c.toString());
                dpf.getjTextFieldClan().setEnabled(false);
                
                dpf.getjLabelBrojStavki().setText(String.valueOf(dpf.getjTableStavke().getRowCount()));
                
                int ukupnaKazna2 = 0;
                dpf.getjLabelUkupnaKazna().setText(String.valueOf(ukupnaKazna2));
                
                dpf.getjComboBoxStatus().setSelectedItem("Pozajmljena");
                dpf.getjComboBoxStatus().setEnabled(false);
                
                dpf.getjTextFieldDatumPozajmice().setText(dateFormat.format(new Date()));
                dpf.getjTextFieldDatumPozajmice().setEnabled(false);
                
                // NOVO
                Pozajmica p2 = new Pozajmica();
                p2.setIdPozajmica(-1);
                Cordinator.getInstance().dodajParam("pozajmica", p2);
                
                
                break;
            case IZMENI:
                //dpf.getjButtonDodaj().setVisible(false);
                //dpf.getjButtonAzuriraj().setVisible(true);
                //dpf.getjButtonAzuriraj().setEnabled(true);
                
                Pozajmica p = (Pozajmica) Cordinator.getInstance().vratiParam("pozajmica");
                
                List<StavkaPozajmice> stavke = Komunikacija.getInstance().ucitajStavke(p);
                p.setStavke(stavke);
                ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
                dpf.getjTableStavke().setModel(mts);
                
                
                dpf.getjTextFieldClan().setText(p.getClan().toString());
                dpf.getjTextFieldRadnik().setText(p.getRadnik().toString());
                dpf.getjTextFieldClan().setEnabled(false);
                dpf.getjTextFieldRadnik().setEnabled(false);
                dpf.getjTextFieldDatumPozajmice().setText(dateFormat.format(p.getDatumPozajmice()));
                dpf.getjTextFieldRokVracanja().setText(dateFormat.format(p.getRokVracanja()));
                dpf.getjComboBoxStatus().setSelectedItem(p.getStatus());
                dpf.getjComboBoxNacinPreuzimanja().setSelectedItem(p.getNacinPreuzimanja());
                dpf.getjLabelBrojStavki().setText(String.valueOf(dpf.getjTableStavke().getRowCount()));
                
                dpf.setId(p.getIdPozajmica());
                dpf.setRadnik(p.getRadnik());
                dpf.setClan(p.getClan());
                int ukupnaKazna = 0;
        
                

                List<StavkaPozajmice> lista = mts.getLista();
                for (StavkaPozajmice sp : lista) {
                    ukupnaKazna = ukupnaKazna + sp.getKazna();
                }
                 
                 
                dpf.getjLabelUkupnaKazna().setText(String.valueOf(ukupnaKazna));
                
                break;

            default:
                System.out.println("GRESKA");
        }
        
        
    }

    public void osveziFormu() {
        
        Pozajmica p = (Pozajmica) Cordinator.getInstance().vratiParam("pozajmica");
        List<StavkaPozajmice> stavke = p.getStavke();
        
        
        
        ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
        dpf.getjTableStavke().setModel(mts);
        
        dpf.getjLabelBrojStavki().setText(String.valueOf(dpf.getjTableStavke().getRowCount()));
        int ukupnaKazna = 0;

        List<StavkaPozajmice> lista = mts.getLista();
        for (StavkaPozajmice sp : lista) {
            ukupnaKazna = ukupnaKazna + sp.getKazna();
        }
     
        dpf.getjLabelUkupnaKazna().setText(String.valueOf(ukupnaKazna));
        
        
        
    }

    private void addActionListeners() {
        
        dpf.addBtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {    
                
                int rb = 0;
                ModelTabeleStavke mts = (ModelTabeleStavke) dpf.getjTableStavke().getModel();
                if(mts.getRowCount() == 0){
                    rb = 0;
                }else{
                    List<StavkaPozajmice> stavke = mts.getLista();
                    for (StavkaPozajmice sp : stavke) {
                        if(rb < sp.getRb()){
                            rb = sp.getRb();
                        }
                    }
                
                }
                rb++;
                Cordinator.getInstance().dodajParam("rb", rb);
                Cordinator.getInstance().otvoriDodajStavkuFormu();
                
                
                
                
            }
        });
        
        dpf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                int red = dpf.getjTableStavke().getSelectedRow();
                //int redPozajmice = ppf.getjTablePozajmice().getSelectedRow();
                
                
                if(red == -1){
                    JOptionPane.showMessageDialog(dpf, "Oznacite stavku koju zelite da obriste", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleStavke mts = (ModelTabeleStavke) dpf.getjTableStavke().getModel();
                    StavkaPozajmice sp = mts.getLista().get(red);
                    Pozajmica p = (Pozajmica) Cordinator.getInstance().vratiParam("pozajmica");
                    // NOVO
                    //ModelTabelePozajmica mtp = (ModelTabelePozajmica) ppf.getjTablePozajmice().getModel();
                    //Pozajmica p = mtp.getLista().get(redPozajmice);
                    
                    try{
                        
                        ModelTabeleStavke mtss = (ModelTabeleStavke) dpf.getjTableStavke().getModel();
                        int size = mtss.getLista().size();
                        
                        
                        if(size <= 1){
                            JOptionPane.showMessageDialog(dpf, "Pozajmica mora da ima makar jednu stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        
                        p.getStavke().remove(sp);
                        JOptionPane.showMessageDialog(dpf, "Sistem je obrisao stavku", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        //pripremiFormu();
                        osveziFormu();
                        

                        
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(dpf, "Sistem ne moze da obrise stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                
                }
            }
        });
        
        
        dpf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni();
            }

            private void izmeni() {
                
                int red = dpf.getjTableStavke().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(dpf, "Oznacite stavku koju zelite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleStavke mts = (ModelTabeleStavke) dpf.getjTableStavke().getModel();
                    StavkaPozajmice sp = mts.getLista().get(red);
                    
                    Cordinator.getInstance().dodajParam("stavka", sp);
                    Cordinator.getInstance().otvoriIzmeniStavkuFormu();
                    
                }
                
            }
        });
        
        dpf.addBtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj();
            }

            private void sacuvaj() {
                
                // NOVO
                // moras upis za id++ i stavis stavke 
                Pozajmica p2 = (Pozajmica) Cordinator.getInstance().vratiParam("pozajmica");
                List<StavkaPozajmice> stavke;
                if(p2.getIdPozajmica() != -1){
                    
                    int id = dpf.getId();
                    Clan clan = dpf.getClan();
                    Radnik radnik = dpf.getRadnik();
                    try {

                        Date datumPozajmice = dateFormat.parse(dpf.getjTextFieldDatumPozajmice().getText().trim());
                        Date rokVracanja = dateFormat.parse(dpf.getjTextFieldRokVracanja().getText().trim());
                        String status = (String) dpf.getjComboBoxStatus().getSelectedItem();
                        String nacinPreuzimanja = (String) dpf.getjComboBoxNacinPreuzimanja().getSelectedItem();
                        int brojStavki = Integer.parseInt(dpf.getjLabelBrojStavki().getText());
                        int ukupnaKazna = Integer.parseInt(dpf.getjLabelUkupnaKazna().getText());
                        //List<StavkaPozajmice> lista = new ArrayList<>();

                        Pozajmica p = new Pozajmica(id,datumPozajmice,rokVracanja,status,nacinPreuzimanja,brojStavki,ukupnaKazna,radnik,clan,p2.getStavke());
                 
                        Komunikacija.getInstance().azurirajPozajmicu(p);
                        JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio pozajmicu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        dpf.dispose();
              
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(dpf, "Datum mora biti u formatu dd.MM.yyyy. (22.12.2003.)", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(dpf, "Sistem ne moze da zapamti pozajmicu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }

                }else{
                    
                    Clan clan = (Clan) Cordinator.getInstance().vratiParam("clan");
                    Radnik radnik = Cordinator.getInstance().getUlogovani();
                    try {

                        Date datumPozajmice = dateFormat.parse(dpf.getjTextFieldDatumPozajmice().getText().trim());
                        Date rokVracanja = dateFormat.parse(dpf.getjTextFieldRokVracanja().getText().trim());
                        String status = (String) dpf.getjComboBoxStatus().getSelectedItem();
                        String nacinPreuzimanja = (String) dpf.getjComboBoxNacinPreuzimanja().getSelectedItem();
                        int brojStavki = Integer.parseInt(dpf.getjLabelBrojStavki().getText());
                        int ukupnaKazna = Integer.parseInt(dpf.getjLabelUkupnaKazna().getText());

                        p2.setDatumPozajmice(datumPozajmice);
                        p2.setRokVracanja(rokVracanja);
                        p2.setStatus(status);
                        p2.setNacinPreuzimanja(nacinPreuzimanja);
                        p2.setBrojStavki(brojStavki);
                        p2.setUkupnaKazna(ukupnaKazna);
                        p2.setRadnik(radnik);
                        p2.setClan(clan);

                        if(Integer.parseInt(dpf.getjLabelBrojStavki().getText()) == 0){
                            JOptionPane.showMessageDialog(dpf, "Pozajmica mora da ima makar jednu stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        Komunikacija.getInstance().dodajPozajmicu(p2);
                        JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio pozajmicu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        dpf.dispose();

                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(dpf, "Datum mora biti u formatu dd.MM.yyyy. (22.12.2003.)", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(dpf, "Sistem ne moze da zapamti pozajmicu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }
                    
                    
                    
                }
                
                
                
                
                
                
            }
        });
        
        
        
    }

    

  
    
    
    
}
