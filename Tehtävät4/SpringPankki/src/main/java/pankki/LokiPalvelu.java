package pankki;

import java.util.Date;

public class LokiPalvelu {

    private Tili tili; // rajapinnan kautta

    public Tili getTili() {
        return tili;
    }

    public void setTili(Tili tili) {
        this.tili = tili;
    }

    public void logLisays() {
        System.out.println("LOKI: Tilille " + tili.getTilinumero() + 
            " lisättiin rahaa " + new Date().toString());
    }

    public void logNosto() {
        System.out.println("LOKI: Tililtä " + tili.getTilinumero() + 
            " nostettiin rahaa " + new Date().toString());
    }
}