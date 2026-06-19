/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Radnik;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Petar
 */
public class Server extends Thread{
    boolean kraj = false;
    ServerSocket serverSocket;
    public static List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private List<Radnik> ulogovaniRadnici = new ArrayList<>();
    private final ServerskaForma sf;

    public Server(ServerskaForma sf) {
        this.sf = sf;
    }

    public List<Radnik> getUlogovaniRadnici() {
        return ulogovaniRadnici;
    }

    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server je pokrenut. cekam klijenta!");
            
            while(!kraj){
                
                Socket s = serverSocket.accept();
                System.out.println("Server: Klijent se povezao sa serverom...");
                
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s, this);
                klijenti.add(okz);
                okz.start();
                 
            
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void dodajRadnikaUListu(Radnik r){
        ulogovaniRadnici.add(r);
        osveziFormu();
        System.out.println("Radnik: "+ r.toString() +" se uspesno ulogovao!\n");
    }
    
    public void izbaciRadnikaIzListe(Radnik r){
        ulogovaniRadnici.remove(r);
        osveziFormu();
        System.out.println("Radnik: "+ r.toString() +" se izlogovao!\n");
    }
    
    private void osveziFormu(){
        
        String stirngKlijenti = "";
        
        for (Radnik r : ulogovaniRadnici) {
            stirngKlijenti += "Klijent " + r.toString() + " se povezao.\n";
        }
        
        if(sf != null && sf.getjLabelKlijenti() != null){
                sf.getjLabelKlijenti().setText("<html>" + stirngKlijenti.replace("\n", "<br>") + "</html>");
        }
        
    }
    
    
    public void zaustaviServer(){
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.prekini();
            }
            
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
