/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import cordinator.Cordinator;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Clan;
import model.Knjiga;
import model.Pozajmica;
import model.Radnik;
import model.StavkaPozajmice;
import model.TerminDezurstva;
import model.TipClanstva;

/**
 *
 * @author Petar
 */
public class Komunikacija {
    
    private static Komunikacija instance;
    private Socket socket;
    private Posiljalac posiljalac;
    private Primlalac primlalac;
    
    private Komunikacija() {
        
        
    }
    
    public static Komunikacija getInstance(){
        if(instance == null){
            instance = new Komunikacija();
        }
        return instance;
    }
    
    public void konekcija() {
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primlalac = new Primlalac(socket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
            ex.printStackTrace();
        }
    
    }

    public Radnik login(String username, String pass) {
        try {
            Radnik r = new Radnik();
            r.setKorisnickoIme(username);
            r.setSifra(pass);
            Zahtev zahtev = new Zahtev(Operacija.LOGIN, r);
            posiljalac.posalji(zahtev);

            Odgovor odg = (Odgovor) primlalac.primi();
            r = (Radnik) odg.getOdgovor();
            return r;
        } catch (Exception ex) {
            System.out.println("Greska prilikom login-a ili je server ugasen!");
            ex.printStackTrace();
            return null;
        }  
    }

    public List<Radnik> ucitajRadnike() {
        
        
        
        try{
            List<Radnik> radnici = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RADNIKE, null);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            radnici = (List<Radnik>) odg.getOdgovor();

            return radnici;

        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
    }

    public void obisiRadnika(Radnik r) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_RADNIKA, r);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno obrisano");
        }else{
            System.out.println("Ne moze da se obrise");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void dodajRadnika(Radnik r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_RADNIKA, r);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
        
    }

    public void azurirajRadnika(Radnik r) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_RADNIKA, r);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuRadnika();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void dodajKnjigu(Knjiga k) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
        
    }

    public List<Knjiga> ucitajKnjige() {
        
        try{
            List<Knjiga> knjige = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KNJIGE, null);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            knjige = (List<Knjiga>) odg.getOdgovor();

            return knjige;
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
    }

    public void obisiKnjigu(Knjiga k) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KNJIGU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno obrisano");
        }else{
            System.out.println("Ne moze da se obrise");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void azurirajKnjigu(Knjiga k) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuKnjiga();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void dodajTipClanstva(TipClanstva tc) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TipClanstva, tc);
        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public List<TipClanstva> ucitajTipClanstva() {
        
        try{
        
            List<TipClanstva> clanstva = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_CLANSTVA, null);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            clanstva = (List<TipClanstva>) odg.getOdgovor();

            return clanstva;
            
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
    }

    public void obisiTipClanstva(TipClanstva tc) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_CLANSTVO, tc);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno obrisano");
        }else{
            System.out.println("Ne moze da se obrise");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void azurirajClanstvo(TipClanstva tc) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_CLANSTVO, tc);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuTipClanstva();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void dodajDezurstvo(TerminDezurstva td) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_DEZURSTVO, td);
        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public List<TerminDezurstva> ucitajDezurstva() {
        
        
        try{ 
            
            List<TerminDezurstva> dezurstva = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_DEZURSTVA, null);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            dezurstva = (List<TerminDezurstva>) odg.getOdgovor();

            return dezurstva;
        
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
    }

    public void obisiDezurstvo(TerminDezurstva td) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_DEZURSTVO, td);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno obrisano");
        }else{
            System.out.println("Ne moze da se obrise");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void azurirajDezurstvo(TerminDezurstva td) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_DEZURSTVO, td);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuTerminDezurstva();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void dodajClana(Clan c) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_CLANA, c);
        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public List<Clan> ucitajClanove() {
        
        try{
            List<Clan> clanovi = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_CLANOVE, null);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            clanovi = (List<Clan>) odg.getOdgovor();

            return clanovi;
        
        
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
        
    }

    public void obisiClana(Clan c) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_CLANA, c);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno obrisano");
        }else{
            System.out.println("Ne moze da se obrise");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public void azurirajClana(Clan c) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_CLANA, c);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuClanova();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public List<Pozajmica> ucitajPozajmice() {
        
        try{
            
            List<Pozajmica> pozajmice = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_POZAJMICE, null);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            pozajmice = (List<Pozajmica>) odg.getOdgovor();

            return pozajmice;
            
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
    }

    public List<StavkaPozajmice> ucitajStavke(Pozajmica p) {
        
        try{
            List<StavkaPozajmice> stavke = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, p);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            stavke = (List<StavkaPozajmice>) odg.getOdgovor();

            return stavke;
        
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
    }

    public void obisiStavku(StavkaPozajmice sp) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STAVKU, sp);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno obrisano");
        }else{
            System.out.println("Ne moze da se obrise");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }


    public void azurirajPozajmicu(Pozajmica p) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_POZAJMICU, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuPozajmica();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
        
    }

    public void azurirajStavku(StavkaPozajmice sp) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_STAVKU, sp);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno azuirano");
            Cordinator.getInstance().osveziFormuStavki();
        }else{
            System.out.println("Ne moze da se azurira");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
        
    }

    public void dodajStavku(StavkaPozajmice sp) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_STAVKU, sp);
        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
            Cordinator.getInstance().osveziFormuStavki();
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }
    
    public void dodajPozajmicu(Pozajmica p) throws Exception {
        
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_POZAJMICU, p);
        posiljalac.posalji(zahtev);
        
        
        Odgovor odg = (Odgovor) primlalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("Uspesno dodato");
            //Cordinator.getInstance().osveziDodajPozajmicuFormu();
        }else{
            System.out.println("Ne moze da se doda");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
        
    }

    public List<StavkaPozajmice> ucitajStavkeClana(Clan c) {
        
        try{
            List<StavkaPozajmice> stavke = new ArrayList<>();
            Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_CLANA, c);
            posiljalac.posalji(zahtev);

            ///////////////

            Odgovor odg = (Odgovor) primlalac.primi();
            stavke = (List<StavkaPozajmice>) odg.getOdgovor();

            return stavke;
        
        }catch(SocketException ex){
            System.out.println("SERVER JE UGASEN!");
            ex.printStackTrace();
            return null;
        }
        
        
        
        
    }

    
    
}
