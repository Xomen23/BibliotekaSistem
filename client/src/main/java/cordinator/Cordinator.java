package cordinator;

import forme.DetaljiClanaForma;
import forme.DodajClanaForma;
import forme.DodajKnjiguForma;
import forme.DodajPozajmicuForma;
import forme.DodajRadnikaForma;
import forme.DodajStavkuForma;
import forme.DodajTerminDezurstvaForma;
import forme.DodajTipClanstvaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazClanaForma;
import forme.PrikazKnjigaForma;
import forme.PrikazPozajmicaForma;
import forme.PrikazRadnikaForma;
import forme.PrikazTerminaDezurstvaForma;
import forme.PrikazTipaClanstvaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DetaljiClanaController;
import kontroleri.DodajClanaController;
import kontroleri.DodajKnjiguController;
import kontroleri.DodajPozajmicuController;
import kontroleri.DodajRadnikaController;
import kontroleri.DodajStavkuController;
import kontroleri.DodajTerminDezurstvaController;
import kontroleri.DodajTipClanstvaController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazClanaController;
import kontroleri.PrikazKnjigaController;
import kontroleri.PrikazPozajmicaController;
import kontroleri.PrikazRadnikaController;
import kontroleri.PrikazTerminDezurstvaController;
import kontroleri.PrikazTipClanstvaController;
import model.Radnik;

/**
 *
 * @author Petar
 */
public class Cordinator {
    
    private static Cordinator instance;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazRadnikaController prController;
    private PrikazKnjigaController pkController;
    private PrikazClanaController pcController;
    private PrikazTipClanstvaController ptcController;
    private PrikazTerminDezurstvaController ptdController;
    private PrikazPozajmicaController ppController;
    private DodajRadnikaController drController;
    private DodajKnjiguController dkController;
    private DodajTipClanstvaController dtcController;
    private DodajTerminDezurstvaController dtdController;
    private DodajClanaController dcController;
    private DodajPozajmicuController dpController;
    private DodajStavkuController dsController;
    private DetaljiClanaController detaljiClanaController;
    private Radnik ulogovani;
    private Map<String, Object> parametri;
    private boolean kreirajnaPozajmica = false;
    
    private Cordinator() {
        parametri = new HashMap<>();
    }
    
    public static Cordinator getInstance(){
        if(instance == null){
            instance = new Cordinator();
        }
        return instance;
    }

    public boolean isKreirajnaPozajmica() {
        return kreirajnaPozajmica;
    }

    public void setKreirajnaPozajmica(boolean kreirajnaPozajmica) {
        this.kreirajnaPozajmica = kreirajnaPozajmica;
    }
    
    

    public void otvoriLoginFormu() {
        
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
        
    }

    public void otvoriGlavnuFormu() {
        
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
        
    }

    public Radnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriPrikazRadnikaFormu() {
        
        prController = new PrikazRadnikaController(new PrikazRadnikaForma());
        prController.otvoriFormu();
        
    }

    public void otvoriDodajRadnikaFormu() {
        
        drController = new DodajRadnikaController(new DodajRadnikaForma());
        drController.otvoriFormu(FormaMod.DODAJ);
    }

    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriIzmeniRadnikaFormu() {
        
        drController = new DodajRadnikaController(new DodajRadnikaForma());
        drController.otvoriFormu(FormaMod.IZMENI);
        
    }

    public void osveziFormuRadnika() {
        
        prController.otvoriFormu();
        
    }

    public void otvoriDodajKnjiguFormu() {
        
        dkController = new DodajKnjiguController(new DodajKnjiguForma());
        dkController.otvoriFormu(FormaMod.DODAJ);
        
    }

    public void otvoriPrikazKnjigaFormu() {
        
        pkController = new PrikazKnjigaController(new PrikazKnjigaForma());
        pkController.otvoriFormu();
        
    }

    public void otvoriIzmeniKnjigaFormu() {
        
        dkController = new DodajKnjiguController(new DodajKnjiguForma());
        dkController.otvoriFormu(FormaMod.IZMENI);
        
    }

    public void osveziFormuKnjiga() {
        
        pkController.otvoriFormu();
        
    }
    
    public void otvoriDodajTipClanstvaFormu() {
        
        dtcController = new DodajTipClanstvaController(new DodajTipClanstvaForma());
        dtcController.otvoriFormu(FormaMod.DODAJ);
        
    }
    
    public void otvoriPrikazTipClanstvaFormu() {
        
        ptcController = new PrikazTipClanstvaController(new PrikazTipaClanstvaForma());
        ptcController.otvoriFormu();
        
    }

    public void otvoriIzmeniTipClanstvaFormu() {
        
        dtcController = new DodajTipClanstvaController(new DodajTipClanstvaForma());
        dtcController.otvoriFormu(FormaMod.IZMENI);
        
    }

    public void osveziFormuTipClanstva() {
        
        ptcController.otvoriFormu();
        
    }
    
    public void otvoriDodajTerminDezurstvaFormu() {
        
        dtdController = new DodajTerminDezurstvaController(new DodajTerminDezurstvaForma());
        dtdController.otvoriFormu(FormaMod.DODAJ);
        
    }
    
    public void otvoriPrikazTerminDezurstvaFormu() {
        
        ptdController = new PrikazTerminDezurstvaController(new PrikazTerminaDezurstvaForma());
        ptdController.ootvoriFormu();
        
        
    }
    
    public void otvoriIzmeniTerminDezurstvaFormu() {
        
        dtdController = new DodajTerminDezurstvaController(new DodajTerminDezurstvaForma());
        dtdController.otvoriFormu(FormaMod.IZMENI);
        
    }
    
    public void osveziFormuTerminDezurstva() {
        
        ptdController.ootvoriFormu();
        
    }
    
   public void otvoriDodajClanaFormu() {
        
        dcController = new DodajClanaController(new DodajClanaForma());
        dcController.otvoriFormu(FormaMod.DODAJ);
        
    }

    public void otvoriPrikazClanovaFormu() {
        
        pcController = new PrikazClanaController(new PrikazClanaForma());
        pcController.otvoriFormu();
        
        
    }

    public void otvoriIzmeniClanaFormu() {
        
        dcController = new DodajClanaController(new DodajClanaForma());
        dcController.otvoriFormu(FormaMod.IZMENI);
        
    }

    public void osveziFormuClanova() {
        
        pcController.otvoriFormu();
        
    }
    
    
    public void otvoriDodajPozajmicuFormu() {
        
        dpController = new DodajPozajmicuController(new DodajPozajmicuForma());
        dpController.otvoriFormu(FormaMod.DODAJ);
        
        
    }

    public void otvoriPrikazPozajmicaFormu() {
        
        ppController = new PrikazPozajmicaController(new PrikazPozajmicaForma());
        ppController.otvoriFormu();
        
        
    }

    public void otvoriIzmeniPozajmicuFormu() {
        
        dpController = new DodajPozajmicuController(new DodajPozajmicuForma());
        dpController.otvoriFormu(FormaMod.IZMENI);
        
        
    }

    public void osveziFormuPozajmica() {
        
        ppController.otvoriFormu();
        
    }
    
    public void otvoriDodajStavkuFormu() {
        
        dsController = new DodajStavkuController(new DodajStavkuForma());
        dsController.otvoriFormu(FormaMod.DODAJ); 
        
        
    }
    
    public void otvoriIzmeniStavkuFormu() {
        
        dsController = new DodajStavkuController(new DodajStavkuForma());
        dsController.otvoriFormu(FormaMod.IZMENI);
        
        
    }
    
    public void osveziDodajPozajmicuFormu() {
        
        dpController.osveziFormu();
        //ppController.otvoriFormu();
        
    }
    
    public void osveziFormuStavki() {
        
        dpController.osveziFormu();
        ppController.osveziFormu();
        
    }
    
     public void otvoriDetaljiClanaFormu() {
        
        detaljiClanaController = new DetaljiClanaController(new DetaljiClanaForma());
        detaljiClanaController.otvoriFormu(FormaMod.IZMENI);

    }
     
     
     
}
