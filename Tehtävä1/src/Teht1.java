
public class Teht1 {

	public static void main(String[] args) {
		// Servlet/JSP:ss‰ on 3 eri scopea (n‰kyvyysaluetta),
		// joilla hallitaan muuttujien elinkaarta ja n‰kyvyytt‰.

		/*1) Request Scope
			-El‰‰ yhden HTTP-pyynnˆn verran
			request.SetAttribute("nimi", "arvo");
			
			-Luetaan JSP:ss‰
			${nimi}
		
			-Esimerkki:
			L‰hetet‰‰n lomake -> servlet k‰sittelee -> N‰ytt‰‰ tuloksen JSP:ss‰
			*/
		
		/*2) Session Scope
			-El‰‰ session ajan
			request.getSession().setAttribute("user", "Jussi");
			${user}

			Esimerkki:
			K‰ytt‰j‰ kirjautuu -> tieto s‰ilyy sivulta toiselle 
			*/
		
		/*3) Application Scope
			-El‰‰ koko sovelluksen ajan
			getServletContext().setAttribute("count", 0);
			${count}
			
			-Esimerkki:
			Montako k‰ytt‰j‰‰ on k‰ynyt sivulla
			*/
			
	}

}
