package pankki;

public class Asiakas {

    private String nimi;
    private Tili tili; // rajapinnan kautta

    public Asiakas(String nimi, Tili tili) {
        this.nimi = nimi;
        this.tili = tili;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Tili getTili() {
        return tili;
    }

    public void setTili(Tili tili) {
        this.tili = tili;
    }

    public void lisaaRahaa(double maara) {
        tili.setSaldo(tili.getSaldo() + maara);
        System.out.println(nimi + " lisäsi " + maara + "€ tilille " + tili.getTilinumero());
        System.out.println("Uusi saldo: " + tili.getSaldo() + "€");
    }

    public void nostaRahaa(double maara) {
        if (tili.getSaldo() >= maara) {
            tili.setSaldo(tili.getSaldo() - maara);
            System.out.println(nimi + " nosti " + maara + "€ tililtä " + tili.getTilinumero());
            System.out.println("Uusi saldo: " + tili.getSaldo() + "€");
        } else {
            System.out.println("Ei riittävästi katetta!");
        }
    }

    public void katsoSaldo() {
        System.out.println(nimi + " tilin " + tili.getTilinumero() + " saldo: " + tili.getSaldo() + "€");
    }
}