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

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.interceptor.Interceptors;

/**
 *
 * @author ian
 */


//@Stateless merkitsee tilattomaksi session beaniksi.
//Kontaineri hallinnoi elinkaarta ja voi jakaa instanssin useille asiakkaille.
//@Named tekee beanista käytettävän JSF-sivuilla EL-lausekkeella.
@Stateless
@Named
public class HelloBean {

	protected String name;

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	// @Interceptors kytkee HelloInterceptor-luokan tähän metodiin.
    // Ennen kuin setName() suoritetaan, HelloInterceptor.modifyGreeting() 
    // sieppaa kutsun ja muuntaa parametrin pieniksi kirjaimiksi.
    // Tämä on Decorator-pattern: lisätoiminnallisuus ilman perimistä.
	/**
	 * Set the value of name
	 *
	 * @param name new value of name
	 */
	@Interceptors(HelloInterceptor.class)
	public void setName(String name) {
		this.name = name;
	}

}
