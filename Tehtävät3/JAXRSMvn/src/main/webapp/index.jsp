<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JAXRSMvn</title>
    </head>
    <body>
        <h2>JAXRSMvn</h2>
        <p>JAXRSMvn on Maven -projekti, jossa demotaan REST-Apin toteutusta JAX-RS -kirjaston avulla.</p>
        <p>Sovelluksessa on kaksi eri tiedostoa jotka tarjoavat saman apin eri muodoissa (JSON ja XML).<br/> 
        Sovelluksen data tulee muistissa olevasta ArrayListista, joten kokeilua varten ei tarvitse<br/>
        viritellä tietokantaa. Aja sovellus Payara -sovelluspalvelimella. Datan voi hakea (GET) selaimeen<br/> 
        seuraavista osoitteista:</p>
        <ul>
        <li><a href="http://localhost:8080/JAXRSMvn/jsonapi/jsonbook">http://localhost:8080/JAXRSMvn/jsonapi/jsonbook</a></li>
        <li><a href="http://localhost:8080/JAXRSMvn/xmlapi/xmlbook">http://localhost:8080/JAXRSMvn/xmlapi/xmlbook</a></li>
        </ul>
        <p>POST, DELETE ja PUT -metodeja täytyy käyttää esim. <a href="https://www.getpostman.com/">Postmanilla</a>.<br/>
        Muokkausreitit pitäisi myös suojata esim. <a href="https://antoniogoncalves.org/2016/10/03/securing-jax-rs-endpoints-with-jwt/">tämän ohjeen</a> mukaisesti.</p>  
    </body>
</html>
