/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa zadužena za čitanje i čuvanje konfiguracije servera
 * (parametri konekcije na bazu, port servera) iz config.properties
 * fajla koji se nalazi na classpath-u (src/main/resources).
 *
 * @author Petar
 */
public class Konfiguracija {
    
    private static final String CONFIG_FILE = "config.properties";
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        konfiguracija = new Properties();
        try (InputStream is = Konfiguracija.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is != null) {
                konfiguracija.load(is);
            } else {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, "config.properties nije pronadjen na classpath-u");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Konfiguracija getInstance(){
        if(instance == null){
            
            instance = new Konfiguracija();
        }
        return instance;
    }

    
    public String getProperty(String key){
        return konfiguracija.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value){
        konfiguracija.setProperty(key, value);
    }
 
    public void sacuvajIzmene(){
        // Cuva izmene u config.properties u izvornom resources folderu projekta,
        // tako da ce promene biti vidljive pri sledecem pokretanju iz IDE-a.
        String path = "server/src/main/resources/" + CONFIG_FILE;
        try (java.io.OutputStream os = new java.io.FileOutputStream(path)) {
            konfiguracija.store(os, null);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
