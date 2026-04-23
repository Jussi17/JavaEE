package beans;

import java.io.Serializable;
import java.util.Random;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "NoppaBean")
@ViewScoped
public class NoppaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int tulos = 0;
    private int summa = 0;
    private int lukumaara = 0;
    private Random rand = new Random();

    public void heita(ActionEvent ae) {
        tulos = rand.nextInt(6) + 1;
        summa += tulos;
        lukumaara++;
    }

    public void nollaa(ActionEvent ae) {
        tulos = 0;
        summa = 0;
        lukumaara = 0;
    }

    public double getKeskiarvo() {
        if (lukumaara == 0) return 0;
        return (double) summa / lukumaara;
    }

    public int getTulos() { return tulos; }
    public int getSumma() { return summa; }
    public int getLukumaara() { return lukumaara; }
}