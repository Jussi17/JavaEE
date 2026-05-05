/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/tutorial-examples/LICENSE.txt
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fish.payara.examples.javaee.interceptor.ejb;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author ian
 */
//Interceptori-luokka ó EI ole EJB-komponentti.
//Kontaineri luo proxy-olion HelloBeanin ymp‰rille joka kutsuu t‰t‰
//interceptoria ennen ja j‰lkeen varsinaisen metodin suorituksen.
//T‰m‰ on selkein esimerkki Decorator-patternista koko projektissa.
public class HelloInterceptor {
	protected String greeting;
	private static final Logger logger = Logger.getLogger("interceptor.ejb.HelloInterceptor");

	public HelloInterceptor() {
	}
	// @AroundInvoke merkitsee metodin joka suoritetaan kohdeluokan
    // metodin ymp‰rill‰. InvocationContext antaa p‰‰syn kutsun parametreihin.
    // ctx.proceed() kutsuu varsinaisen metodin (HelloBean.setName()).
    // T‰ss‰ parametri muutetaan pieniksi kirjaimiksi ennen varsinaista kutsua.
	@AroundInvoke
	public Object modifyGreeting(InvocationContext ctx) throws Exception {
		Object[] parameters = ctx.getParameters();
		String param = (String) parameters[0];
		param = param.toLowerCase(); // muunnetaan pieniksi kirjaimiksi
		parameters[0] = param;
		ctx.setParameters(parameters);
		try {
			return ctx.proceed(); // kutsutaan varsinainen metodi
		} catch (Exception e) {
			logger.warning("Error calling ctx.proceed in modifyGreeting()");
			return null;
		}
	}

}
