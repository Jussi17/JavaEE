package luokat;

import java.util.Random;

public class Noppa {

    private int summa = 0;
    private int tulos = 0;
    private int lukumaara = 0;
    private Random rand = new Random();

    public int getSumma() {
        return summa;
    }

    public int getTulos() {
        return tulos;
    }

    public int getLukumaara() {
        return lukumaara;
    }

    public double getKeskiarvo() {
        if (lukumaara == 0) return 0;
        return (double) summa / lukumaara;
    }

    public void heita() {
        tulos = rand.nextInt(6) + 1;
        summa += tulos;
        lukumaara++;
    }

    public void nollaa() {
        summa = 0;
        tulos = 0;
        lukumaara = 0;
    }
}