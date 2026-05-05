package pankki;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext ctx = 
                new ClassPathXmlApplicationContext("BeansConfigPankki.xml")) {

            Asiakas asiakas = (Asiakas) ctx.getBean("asiakas");

            System.out.println("=== Pankkisovellus ===");
            asiakas.katsoSaldo();

            System.out.println("\n--- Lisätään 200€ ---");
            asiakas.lisaaRahaa(200.0);

            System.out.println("\n--- Nostetaan 150€ ---");
            asiakas.nostaRahaa(150.0);

            System.out.println("\n--- Lopullinen saldo ---");
            asiakas.katsoSaldo();
        }
    }
}