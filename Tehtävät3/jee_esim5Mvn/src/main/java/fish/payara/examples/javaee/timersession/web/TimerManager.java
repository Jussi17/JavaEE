/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/tutorial-examples/LICENSE.txt
 */
package fish.payara.examples.javaee.timersession.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fish.payara.examples.javaee.timersession.ejb.TimerSessionBean;

/**
 *
 * @author ian
 */
// Ei ole EJB-komponentti ó t‰m‰ on CDI/JSF-bean.
// @Named tekee beanista k‰ytett‰v‰n JSF-sivuilla EL-lausekkeella #{timerManager}.
// @SessionScoped ó yksi instanssi per k‰ytt‰j‰sessio.
// Toimii web-kontainerissa CDI:n hallinnoimana.
// Rooli: v‰litt‰‰ TimerSessionBean EJB:n tiedot JSF-k‰yttˆliittym‰lle.
@Named
@SessionScoped
public class TimerManager implements Serializable {

	private static final long serialVersionUID = 1L;

    // @EJB injektoi TimerSessionBean EJB-komponentin automaattisesti.
    // Kontaineri hoitaa injektoinnin ó kehitt‰j‰ ei luo oliota itse.
    // TimerManager on siis silta web-kontainerin ja EJB-kontainerin v‰lill‰.
	@EJB
	private TimerSessionBean timerSession;

	private String lastProgrammaticTimeout;
	private String lastAutomaticTimeout;

	/** Creates a new instance of TimerManager */
	public TimerManager() {
		this.lastProgrammaticTimeout = "never";
		this.lastAutomaticTimeout = "never";
	}

	/**
	 * @return the lastTimeout
	 */
    // Hakee viimeisimm‰n ohjelmallisen timeout-ajan EJB:lt‰.
    // JSF-sivu k‰ytt‰‰ t‰t‰ #{timerManager.lastProgrammaticTimeout} -lausekkeella.
	public String getLastProgrammaticTimeout() {
		lastProgrammaticTimeout = timerSession.getLastProgrammaticTimeout();
		return lastProgrammaticTimeout;
	}

	/**
	 * @param lastTimeout the lastTimeout to set
	 */
	public void setLastProgrammaticTimeout(String lastTimeout) {
		this.lastProgrammaticTimeout = lastTimeout;
	}

	// K‰ynnist‰‰ ohjelmallisen ajastimen 8 sekunnin viiveell‰.
    // Kutsutaan JSF-sivulta napin painalluksella.
	public void setTimer() {
		long timeoutDuration = 8000;
		timerSession.setTimer(timeoutDuration);
	}

	/**
	 * @return the lastAutomaticTimeout
	 */
	// Hakee viimeisimm‰n automaattisen timeout-ajan EJB:lt‰.
	public String getLastAutomaticTimeout() {
		lastAutomaticTimeout = timerSession.getLastAutomaticTimeout();
		return lastAutomaticTimeout;
	}

	/**
	 * @param lastAutomaticTimeout the lastAutomaticTimeout to set
	 */
	public void setLastAutomaticTimeout(String lastAutomaticTimeout) {
		this.lastAutomaticTimeout = lastAutomaticTimeout;
	}

}
