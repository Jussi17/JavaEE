package fish.payara.examples.javaee.async.ejb;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Startup;

//@Singleton tarkoittaa ett‰ vain yksi instanssi
//on olemassa koko sovelluksen elinkaaren ajan.
//@Startup k‰ynnist‰‰ beanin automaattisesti palvelimen k‰ynnistyess‰.
//Toimii EJB-kontainerissa.
@Startup
@Singleton
public class MyLoggingBean {
	private Logger logger;
	FileHandler fh;

	// @PostConstruct suoritetaan automaattisesti olion luomisen j‰lkeen.
    // T‰ss‰ alustetaan logger.
	@PostConstruct
	public void start() {
		logger = Logger.getLogger("TestLogger");
		logger.info("start() logging");
	}

	// Synkroninen metodi ó kutsuja j‰‰ odottamaan 2 sekuntia
    // ennen kuin metodi palauttaa kontrollin.
	public void logSync(String msg) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		logger.info(msg);
	}
	
    // @Asynchronous tekee metodista asynkronisen ó kontaineri suorittaa
    // metodin omassa s‰ikeess‰‰n. Kutsuja ei j‰‰ odottamaan vaan saa
    // kontrollin heti takaisin. T‰m‰ on EJB-kontainerin tarjoama palvelu
    // joka toteutetaan proxy-olion avulla (Decorator-pattern).
	@Asynchronous
	public void logAsync(String msg) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}

		logger.info(msg);
	}
}
