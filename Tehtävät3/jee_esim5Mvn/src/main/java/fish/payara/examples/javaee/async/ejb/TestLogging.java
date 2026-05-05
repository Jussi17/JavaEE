package fish.payara.examples.javaee.async.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//@Singleton @Startup — käynnistyy automaattisesti
//palvelimen käynnistyessä. Toimii EJB-kontainerissa.
@Startup
@Singleton
public class TestLogging {
	// @EJB injektoi MyLoggingBean-olion automaattisesti.
    // Kontaineri hoitaa injektoinnin — kehittäjä ei luo oliota itse.
	@EJB
	MyLoggingBean logBean;

	// @PostConstruct suoritetaan automaattisesti käynnistyksen jälkeen.
    // Tässä testataan synkronisen ja asynkronisen metodin eroa:
    // - logSync() blokkaa 2 sekuntia ennen kuin jatketaan
    // - logAsync() palauttaa kontrollin heti, metodi suoritetaan taustalla
	// Look execution order from server console / Katso suoritusjärjestys palvelimen konsolista

	@PostConstruct
	public void testLoggers() {
		System.out.println("testLoggers() started");
		System.out.println("call logSync()");
		logBean.logSync("logSync() logging"); // blokkaa 2 sek
		System.out.println("testLoggers() continues after waiting");
		System.out.println("call logAsync()");
		logBean.logAsync("logAsync() logging"); // ei blokkaa
		System.out.println("testLoggers() continues immediately");
		System.out.println("testLoggers() finished");
	}
}