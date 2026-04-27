package jpajsf;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "asiakasController")
@RequestScoped
public class AsiakasController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    AsiakasService as;

    private Asiakas asiakas;

    public AsiakasController() {
        this.asiakas = new Asiakas();
    }

    public Asiakas getAsiakas() { return asiakas; }
    public void setAsiakas(Asiakas asiakas) { this.asiakas = asiakas; }

    public void saveAsiakas() {
        String nimi = asiakas.getNimi();
        this.as.save(asiakas);
        this.asiakas = new Asiakas();
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Onnistui!", "Asiakas " + nimi + " lisätty!"));
    }

    public List<Asiakas> listAllAsiakkaat() {
        return this.as.getAllAsiakkaat();
    }
}