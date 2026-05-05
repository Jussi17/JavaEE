package pankki;

public class Tili1 implements Tili {

    private String tilinumero;
    private double saldo;

    public String getTilinumero() {
        return tilinumero;
    }

    public void setTilinumero(String tilinumero) {
        this.tilinumero = tilinumero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}