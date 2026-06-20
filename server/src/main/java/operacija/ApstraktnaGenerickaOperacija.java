/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 * Bazna klasa za sve sistemske operacije na serveru.
 * Definiše opšti tok izvršavanja operacije: provera preduslova, otvaranje
 * transakcije, izvršavanje konkretne operacije nad bazom, potvrda ili
 * poništavanje transakcije u slučaju greške. Svaka konkretna sistemska
 * operacija nasleđuje ovu klasu i implementira metode preduslovi i
 * izvrsiOperaciju u skladu sa svojom poslovnom logikom.
 *
 * @author Petar
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }
    
    /**
     * Pokreće izvršavanje sistemske operacije nad zadatim objektom.
     * Prvo proverava preduslove, zatim otvara transakciju, izvršava
     * konkretnu operaciju i potvrđuje transakciju. U slučaju greške
     * transakcija se poništava i greška se prosleđuje pozivaocu.
     *
     * @param objekat domenski objekat nad kojim se izvršava operacija
     * @param kljuc dodatni ključ/kriterijum potreban pojedinim operacijama
     * @throws Exception ukoliko preduslovi nisu ispunjeni ili dođe do greške pri izvršavanju
     */
    public final void izvrsi(Object objekat, String kljuc) throws Exception{
    
        try{
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        }catch(Exception e){
            ponistiTransakciju();
            throw e;
        } finally{
            //ugasiKonekciju();
        }
    
    
    }

    /**
     * Proverava da li su ispunjeni svi preduslovi za izvršavanje operacije
     * (validnost tipa i sadržaja prosleđenog objekta).
     *
     * @param param objekat koji se proverava
     * @throws Exception ukoliko neki od preduslova nije ispunjen
     */
    protected abstract void preduslovi(Object param) throws Exception;
    
    /**
     * Izvršava konkretnu poslovnu logiku operacije nad bazom podataka.
     *
     * @param param objekat nad kojim se izvršava operacija
     * @param kljuc dodatni ključ/kriterijum potreban pojedinim operacijama
     * @throws Exception ukoliko dođe do greške pri izvršavanju operacije
     */
    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;
    
    private void ponistiTransakciju() throws Exception {
        ((DBRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws Exception {
        ((DBRepository) broker).disconnect();
    }
   

    private void zapocniTransakciju() throws Exception {
       ((DBRepository) broker).connect();
    }


    private void potvrdiTransakciju() throws Exception {
        ((DBRepository) broker).commit();
    }
    
}
