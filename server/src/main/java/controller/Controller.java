/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Clan;
import model.Knjiga;
import model.Pozajmica;
import model.Radnik;
import model.StavkaPozajmice;
import model.TerminDezurstva;
import model.TipClanstva;
import operacija.clanovi.AzurirajClanaSO;
import operacija.clanovi.KreirajClanaSO;
import operacija.clanovi.ObrisiClanaSO;
import operacija.clanovi.UcitajClanaSO;
import operacija.knjige.AzurirajKnjiguSO;
import operacija.knjige.KreirajKnjiguSO;
import operacija.knjige.ObrisiKnjiguSO;
import operacija.knjige.UcitajKnjigeSO;
import operacija.login.LoginOperacija;
import operacija.pozajmice.AzurirajPozajmicuSO;
import operacija.pozajmice.KreirajPozajmicuSO;
import operacija.pozajmice.UcitajPozajmiceSO;
import operacija.radnici.AzurirajRadnikaSO;
import operacija.radnici.KreirajRadnikaSO;
import operacija.radnici.ObrisiRadnikaSO;
import operacija.radnici.UcitajRadnikeSO;
import operacija.stavke.AzurirajStavkuPozajmiceSO;
import operacija.stavke.KreirajStavkuPozajmiceSO;
import operacija.stavke.ObrisiStavkuPozajmiceSO;
import operacija.stavke.UcitajStavkePozajmiceSO;
import operacija.terminDezurstva.AzurirajTerminDezurstvaSO;
import operacija.terminDezurstva.ObrisiTerminDezurstvaSO;
import operacija.terminDezurstva.UbaciTerminDezurstvaSO;
import operacija.terminDezurstva.UcitajTerminDezurstvaSO;
import operacija.tipClanstva.AzurirajTipClanstvaSO;
import operacija.tipClanstva.KreirajTipClanstvaSO;
import operacija.tipClanstva.ObrisiTipClanstvaSO;
import operacija.tipClanstva.UcitajTipClanstvaSO;

/**
 *
 * @author Petar
 */
public class Controller {
    
    private static Controller instance;

    private Controller() {
        
        
    }
    
    public static Controller getInstance(){
        if(instance == null){
        
            instance = new Controller();
        }
        return instance;
    }

    public Radnik login(Radnik r) throws Exception {
        
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(r, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getRadnik());
        return operacija.getRadnik();
        
    }

    public List<Radnik> ucitajRadnike() throws Exception {
        UcitajRadnikeSO operacija = new UcitajRadnikeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getRadnici());
        return operacija.getRadnici();
    }

    public void obrisiRadnika(Radnik radnik) throws Exception {
        
        ObrisiRadnikaSO operacija = new ObrisiRadnikaSO();
        operacija.izvrsi(radnik, null);
        
    }

    public void dodajRadnika(Radnik radnik) throws Exception {
        
        KreirajRadnikaSO operacija = new KreirajRadnikaSO();
        operacija.izvrsi(radnik, null);
        
    }

    public void azurirajRadnika(Radnik radnik) throws Exception {
        
        AzurirajRadnikaSO operacija = new AzurirajRadnikaSO();
        operacija.izvrsi(radnik, null);
        
    }

    public void dodajKnjigu(Knjiga knjiga) throws Exception {
        
        KreirajKnjiguSO operacija = new KreirajKnjiguSO();
        operacija.izvrsi(knjiga, null);
        
        
    }

    public List<Knjiga> ucitajKnjige() throws Exception {
        
        UcitajKnjigeSO operacija = new UcitajKnjigeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getKnjige());
        return operacija.getKnjige();
        
    }

    public void obrisiKnjigu(Knjiga knjiga) throws Exception {
        
        ObrisiKnjiguSO operacija = new ObrisiKnjiguSO();
        operacija.izvrsi(knjiga, null);
        
    }

    public void azurirajKnjigu(Knjiga knjiga) throws Exception {
        
        AzurirajKnjiguSO operacija = new AzurirajKnjiguSO();
        operacija.izvrsi(knjiga, null);
        
    }

    public void dodajTipClanstva(TipClanstva tc) throws Exception {
        
        KreirajTipClanstvaSO operacija = new KreirajTipClanstvaSO();
        operacija.izvrsi(tc, null);
        
    }

    public List<TipClanstva> ucitajClanstva() throws Exception {
        
        UcitajTipClanstvaSO operacija = new UcitajTipClanstvaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getClanstva());
        return operacija.getClanstva();
        
    }

    public void obrisiClanstvo(TipClanstva clanstvo) throws Exception {
        
        ObrisiTipClanstvaSO operacija = new ObrisiTipClanstvaSO();
        operacija.izvrsi(clanstvo, null);
        
    }

    public void azurirajClanstvo(TipClanstva clanstvo) throws Exception {
        
        AzurirajTipClanstvaSO operacija = new AzurirajTipClanstvaSO();
        operacija.izvrsi(clanstvo, null);
        
    }

    public void dodajTerminDezurstva(TerminDezurstva td) throws Exception {
        
        UbaciTerminDezurstvaSO operacija = new UbaciTerminDezurstvaSO();
        operacija.izvrsi(td, null);
        
    }

    public List<TerminDezurstva> ucitajDezurstva() throws Exception {
        
        UcitajTerminDezurstvaSO operacija = new UcitajTerminDezurstvaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getDezurstva());
        return operacija.getDezurstva();
        
    }

    public void obrisiDezurstvo(TerminDezurstva dezurstvo) throws Exception {
        
        ObrisiTerminDezurstvaSO operacija = new ObrisiTerminDezurstvaSO();
        operacija.izvrsi(dezurstvo, null);
        
    }

    public void azurirajDezurstvo(TerminDezurstva dezurstvo) throws Exception {
        
        AzurirajTerminDezurstvaSO operacija = new AzurirajTerminDezurstvaSO();
        operacija.izvrsi(dezurstvo, null);
        
    }

    public void dodajClana(Clan c) throws Exception {
        
        KreirajClanaSO operacija = new KreirajClanaSO();
        operacija.izvrsi(c, null);
        
    }

    public List<Clan> ucitajClanove() throws Exception {
        
        UcitajClanaSO operacija = new UcitajClanaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getClanovi());
        return operacija.getClanovi();
        
    }

    public void obrisiClana(Clan c) throws Exception {
        
        ObrisiClanaSO operacija = new ObrisiClanaSO();
        operacija.izvrsi(c, null);
        
    }

    public void azurirajClana(Clan c) throws Exception {
        
        AzurirajClanaSO operacija = new AzurirajClanaSO();
        operacija.izvrsi(c, null);
        
    }

    public List<Pozajmica> ucitajPozajmice() throws Exception {
        
        UcitajPozajmiceSO operacija = new UcitajPozajmiceSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getPozajmice());
        return operacija.getPozajmice();
        
    }

    public List<StavkaPozajmice> ucitajStavke(Pozajmica p) throws Exception {
        
        UcitajStavkePozajmiceSO operacija = new UcitajStavkePozajmiceSO();
        operacija.izvrsi(p, null);
        System.out.println("KLASA CONTROLLER: "+operacija.getStavke());
        return operacija.getStavke();
        
    }

    public void obrisiStavku(StavkaPozajmice sp) throws Exception {
        
        ObrisiStavkuPozajmiceSO operacija = new ObrisiStavkuPozajmiceSO();
        operacija.izvrsi(sp, null);
        
    }

    public void azurirajPozajmicu(Pozajmica pozajmica) throws Exception {
        
        AzurirajPozajmicuSO operacija = new AzurirajPozajmicuSO();
        operacija.izvrsi(pozajmica, null);
        
    }

    public void azurirajStavku(StavkaPozajmice stavka) throws Exception {
        
        AzurirajStavkuPozajmiceSO operacija = new AzurirajStavkuPozajmiceSO();
        operacija.izvrsi(stavka, null);
        
    }

    public void dodajStavku(StavkaPozajmice stavka) throws Exception {
        
        KreirajStavkuPozajmiceSO operacija = new KreirajStavkuPozajmiceSO();
        operacija.izvrsi(stavka, null);
        
    }

    public void dodajPozajmicu(Pozajmica pozajmica) throws Exception {
        
        KreirajPozajmicuSO operacija = new KreirajPozajmicuSO();
        operacija.izvrsi(pozajmica, null);
        
    }

    public List<StavkaPozajmice> ucitajStavkeClana(Clan c) throws Exception {
        
        UcitajStavkePozajmiceSO operacija = new UcitajStavkePozajmiceSO();
        operacija.izvrsi(c, "clan");
        System.out.println("KLASA CONTROLLER: "+operacija.getStavke());
        return operacija.getStavke();
        
    }
    
    
}
