/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.GlavnaForma;
import model.Radnik;

/**
 *
 * @author Petar
 */
public class GlavnaFormaController {
    
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
        
        gf.getjLabelOpis().setText("<html>Dobrodosli u sistem za pracenje pozajmica biblioteke!<br><br>"
                                + "Ovaj softver omogucava efikasno upravljanje pozajmicama.<br>"
                                + "Kroz jednostavan meni mozete brzo dodavati i pregledati clanove, pozajmice, radnike, knjige i termine dezurstva.<br>"
                                + "Svaka pozajmica je povezana sa clanom biblioteke i odgovornim radnikom.<br><br>"
                                + "Koristite gornji meni za navigaciju kroz aplikaciju.<br>"
                                + "<b>Zelimo vam uspesan rad!</b></html>");
        
        
        /*
        gf.getjLabelOpis().setText(
            "<html>"
                + "<div style='text-align: left; font-size: 12px;'>"
                + "<b>Dobrodošli u sistem za praćenje pozajmica biblioteke!</b><br><br>"
                + "Ovaj softver omogućava efikasno upravljanje pozajmicama.<br>"
                + "Kroz jednostavan meni možete brzo dodavati i pregledati:<br>"
                + "<b>Članove, pozajmice, radnike, knjige i termine dežurstva.</b><br><br>"
                + "Svaka pozajmica je povezana sa članom biblioteke i odgovornim radnikom.<br><br>"
                + "Koristite gornji meni za navigaciju kroz aplikaciju.<br><br>"
                + "<b style='font-size: 14px;'>Želimo vam uspešan rad!</b>"
                + "</div>"
                + "</html>"
        );
        */
    }

    private void addActionListeners() {

        
    }
    
    
    public void otvoriFormu() {
        
        Radnik ulogovani = Cordinator.getInstance().getUlogovani();
        gf.setVisible(true);
        gf.getjLabelImeKorisnika().setText(ulogovani.toString());
        gf.setLocationRelativeTo(null);
        
    }
    
}
