/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import forme.ServerskaForma;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primlalac;
import komunikacija.Zahtev;
import model.Clan;
import model.Knjiga;
import model.Pozajmica;
import model.Radnik;
import model.StavkaPozajmice;
import model.TerminDezurstva;
import model.TipClanstva;
import server.Server;

/**
 *
 * @author Petar
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    Socket socket;
    Posiljalac posiljalac;
    Primlalac primalac;
    private Radnik radnik;
    boolean kraj = false;
    private Server server;
    
    public ObradaKlijentskihZahteva(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primlalac(socket);
    }

    public Radnik getRadnik() {
        return radnik;
    }


    @Override
    public void run() {
        while(!kraj){
            try{
            Zahtev zahtev = (Zahtev) primalac.primi();
            Odgovor odgovor = new Odgovor();
            
            switch (zahtev.getOperacija()) {
                case LOGIN:
                    Radnik r = (Radnik) zahtev.getParametar();
                    r = Controller.getInstance().login(r);
                    /*
                    if(server.getUlogovaniRadnici().contains(r)){
                        odgovor.setOdgovor(null);
                    }
                    */
                    odgovor.setOdgovor(r);
                    
                    if (r != null) {
                        server.dodajRadnikaUListu(r);
                        radnik = r;
                    }

                    break;
                case UCITAJ_RADNIKE:
                    List<Radnik> radnici = Controller.getInstance().ucitajRadnike();
                    odgovor.setOdgovor(radnici);
                    
                    break;
                case OBRISI_RADNIKA:
                    try{
                        Radnik radnik = (Radnik) zahtev.getParametar();
                        Controller.getInstance().obrisiRadnika(radnik);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_RADNIKA:
                    try{
                        Radnik radnik = (Radnik) zahtev.getParametar();
                        Controller.getInstance().dodajRadnika(radnik);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_RADNIKA:
                    try{
                        Radnik radnik = (Radnik) zahtev.getParametar();
                        Controller.getInstance().azurirajRadnika(radnik);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_KNJIGU:
                    try{
                        Knjiga knjiga = (Knjiga) zahtev.getParametar();
                        Controller.getInstance().dodajKnjigu(knjiga);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case UCITAJ_KNJIGE:
                    List<Knjiga> knjige = Controller.getInstance().ucitajKnjige();
                    odgovor.setOdgovor(knjige);
                    
                    break;
                case OBRISI_KNJIGU:
                    try{
                        
                        Knjiga knjiga = (Knjiga) zahtev.getParametar();
                        Controller.getInstance().obrisiKnjigu(knjiga);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_KNJIGU:
                    try{
                        Knjiga knjiga = (Knjiga) zahtev.getParametar();
                        Controller.getInstance().azurirajKnjigu(knjiga);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_TipClanstva:
                    try{
                        TipClanstva tc = (TipClanstva) zahtev.getParametar();
                        Controller.getInstance().dodajTipClanstva(tc);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case UCITAJ_CLANSTVA:
                    List<TipClanstva> clanstva = Controller.getInstance().ucitajClanstva();
                    odgovor.setOdgovor(clanstva);
                    
                    break;
                case OBRISI_CLANSTVO:
                    try{
                        TipClanstva clanstvo = (TipClanstva) zahtev.getParametar();
                        Controller.getInstance().obrisiClanstvo(clanstvo);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_CLANSTVO:
                    try{
                        TipClanstva clanstvo = (TipClanstva) zahtev.getParametar();
                        Controller.getInstance().azurirajClanstvo(clanstvo);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_DEZURSTVO:
                    try{
                        TerminDezurstva td = (TerminDezurstva) zahtev.getParametar();
                        Controller.getInstance().dodajTerminDezurstva(td);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case UCITAJ_DEZURSTVA:
                    List<TerminDezurstva> dezurstva = Controller.getInstance().ucitajDezurstva();
                    odgovor.setOdgovor(dezurstva);
                    
                    break;
                case OBRISI_DEZURSTVO:
                    try{
                        TerminDezurstva dezurstvo = (TerminDezurstva) zahtev.getParametar();
                        Controller.getInstance().obrisiDezurstvo(dezurstvo);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_DEZURSTVO:
                    try{
                        TerminDezurstva dezurstvo = (TerminDezurstva) zahtev.getParametar();
                        Controller.getInstance().azurirajDezurstvo(dezurstvo);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_CLANA:
                    try{
                        Clan c = (Clan) zahtev.getParametar();
                        Controller.getInstance().dodajClana(c);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case UCITAJ_CLANOVE:
                    List<Clan> clanovi = Controller.getInstance().ucitajClanove();
                    odgovor.setOdgovor(clanovi);
                    
                    break;
                case OBRISI_CLANA:
                    try{
                        Clan c = (Clan) zahtev.getParametar();
                        Controller.getInstance().obrisiClana(c);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_CLANA:
                    try{
                        Clan c = (Clan) zahtev.getParametar();
                        Controller.getInstance().azurirajClana(c);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case UCITAJ_POZAJMICE:
                    List<Pozajmica> pozajmice = Controller.getInstance().ucitajPozajmice();
                    odgovor.setOdgovor(pozajmice);
                    
                    break;
                case UCITAJ_STAVKE:
                    Pozajmica p = (Pozajmica) zahtev.getParametar();
                    List<StavkaPozajmice> stavke = Controller.getInstance().ucitajStavke(p);
                    odgovor.setOdgovor(stavke);
                    
                    break;
                case OBRISI_STAVKU:
                    try{
                        StavkaPozajmice sp = (StavkaPozajmice) zahtev.getParametar();
                        Controller.getInstance().obrisiStavku(sp);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_POZAJMICU:
                    try{
                        Pozajmica pozajmica = (Pozajmica) zahtev.getParametar();
                        Controller.getInstance().azurirajPozajmicu(pozajmica);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case AZURIRAJ_STAVKU:
                    try{
                        StavkaPozajmice stavka = (StavkaPozajmice) zahtev.getParametar();
                        Controller.getInstance().azurirajStavku(stavka);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_STAVKU:
                    try{
                        StavkaPozajmice stavka = (StavkaPozajmice) zahtev.getParametar();
                        Controller.getInstance().dodajStavku(stavka);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case DODAJ_POZAJMICU:
                    try{
                        Pozajmica pozajmica = (Pozajmica) zahtev.getParametar();
                        Controller.getInstance().dodajPozajmicu(pozajmica);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    
                    break;
                case UCITAJ_STAVKE_CLANA:
                    Clan c = (Clan) zahtev.getParametar();
                    List<StavkaPozajmice> stavke2 = Controller.getInstance().ucitajStavkeClana(c);
                    odgovor.setOdgovor(stavke2);
                    
                    break;
                default:
                    System.out.println("GRESKA, TA OPERACIJA NE POSTOJI");
            }
            
            posiljalac.posalji(odgovor);
            }catch(SocketException s){ 
                this.prekini();
                //break;
                return;
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }
    
    
    public void prekini(){
        kraj = true;
        try {
            socket.close();
            if(radnik != null){
                server.izbaciRadnikaIzListe(radnik);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
}
