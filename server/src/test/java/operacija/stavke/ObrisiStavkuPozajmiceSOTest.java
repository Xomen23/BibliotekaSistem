package operacija.stavke;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Clan;
import model.Knjiga;
import model.Pozajmica;
import model.Radnik;
import model.StavkaPozajmice;
import model.TipClanstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObrisiStavkuPozajmiceSOTest {

    private ObrisiStavkuPozajmiceSO so;
    private Pozajmica pozajmica;
    private Knjiga knjiga;

    @BeforeEach
    void setUp() throws Exception {
        so = new ObrisiStavkuPozajmiceSO();
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        Clan clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        Radnik radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        Date datumPozajmice = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-01");
        Date rokVracanja = new SimpleDateFormat("yyyy-MM-dd").parse("2026-06-15");
        pozajmica = new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
        knjiga = new Knjiga(1, "Na Drini cuprija", "Ivo Andric", "9788671023033", "Roman");
    }

    private StavkaPozajmice validStavka() throws Exception {
        return new StavkaPozajmice(1, pozajmica, null, false, 0, knjiga);
    }

    @Test
    void predusloviProlaziZaValidnuStavku() {
        assertDoesNotThrow(() -> so.preduslovi(validStavka()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije stavka"));
    }
}
