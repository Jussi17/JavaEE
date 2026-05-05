package fish.payara.examples.javaee.stateful.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

// @Stateful session bean.
// Toisin kuin @Stateless, tilallinen bean muistaa tilan pyyntöjen välillä.
// @SessionScoped sitoo beanin käyttäjän sessioon — yksi instanssi per käyttäjä.
// Kontaineri luo uuden instanssin jokaiselle käyttäjäsessiolle ja tuhoaa sen session päättyessä.
@Stateful
@SessionScoped
public class StatefulTest {

	public StatefulTest() {
	}

	// Tämä laskuri säilyy muistissa käyttäjän session ajan.
    // Jokainen sivulatauskerta kasvattaa laskuria.
    // @Stateless-beanilla tila nollautuisi joka kutsukerralla.
	private int i;


    // @PostConstruct alustetaan laskuri nollaksi kun bean luodaan.
	@PostConstruct
	public void initialize() {
		i = 0;
	}

	// Palauttaa nykyisen laskurin arvon ja kasvattaa sitä.
    // Koska bean on @Stateful, arvo säilyy kutsujen välillä.
	public int getI() {
		return i++;
	}
}
