package operacija.pozajmice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Clan;
import model.Pozajmica;
import model.Radnik;
import model.TipClanstva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KreirajPozajmicuSOTest {

    private KreirajPozajmicuSO so;
    private Radnik radnik;
    private Clan clan;
    private Date datumPozajmice;
    private Date rokVracanja;

    @BeforeEach
    void setUp() throws Exception {
        so = new KreirajPozajmicuSO();
        TipClanstva tip = new TipClanstva(1, "Standardni", 500.0, 5);
        clan = new Clan(1, "Petar", "Mitrovic", "petar@gmail.com", "0641234567", tip);
        radnik = new Radnik(1, "Stefan", "Jovanovic", "0101990123456", "sjovanovic", "0641111111", "tajna123");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        datumPozajmice = sdf.parse("2026-06-01");
        rokVracanja = sdf.parse("2026-06-15");
    }

    private Pozajmica validPozajmica() throws Exception {
        return new Pozajmica(1, datumPozajmice, rokVracanja, "Aktivna", "Licno preuzimanje", 1, 0, radnik, clan, new ArrayList<>());
    }

    @Test
    void predusloviProlaziZaValidnuPozajmicu() {
        assertDoesNotThrow(() -> so.preduslovi(validPozajmica()));
    }

    @Test
    void predusloviBacaGreskuZaNullObjekat() {
        assertThrows(Exception.class, () -> so.preduslovi(null));
    }

    @Test
    void predusloviBacaGreskuZaPogresanTip() {
        assertThrows(Exception.class, () -> so.preduslovi("nije pozajmica"));
    }
}
