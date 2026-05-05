package fish.payara.examples.javaee.facade.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

// @Stateless session bean.
// T‰m‰ on Session Facade -suunnittelumalli:
// BankServiceFacade tarjoaa yksinkertaisen rajapinnan monimutkaisiin pankkioperaatioihin.
// Asiakas kutsuu vain t‰t‰ facadia eik‰ tarvitse tiet‰‰ yksitt‰isist‰ palveluista mit‰‰n.

@Stateless
public class BankServiceFacade {
	
	// @Inject injektoi CDI:n avulla palvelut automaattisesti.
    // Kontaineri luo ja hallinnoi n‰it‰ olioita.
	@Inject
	CustomerService customerService;

	@Inject
	LoanService loanService;

	@Inject
	AccountService accountService;

	// Facadin yksi metodi yhdist‰‰ kolmen eri palvelun toiminnallisuuden:
    // 1. Haetaan asiakas session perusteella
    // 2. Tarkistetaan asiakkaan id
    // 3. Tarkistetaan luottoluokitus
    // 4. Haetaan laina ja asetetaan saldo
    // Kaikki t‰m‰ tapahtuu yhdell‰ metodikutsulla asiakkaan puolelta.
	public boolean getLoan(int sessionId, double amount) {
		boolean result = false;
		long id = customerService.getCustomer(sessionId);

		if (customerService.checkId(id)) {
			if (loanService.checkCreditRating(id, amount)) {
				if (accountService.getLoan(amount)) {
					result = accountService.setCustomerBalance(id, amount);
				}
			}
		}
		return result;
	}
}
