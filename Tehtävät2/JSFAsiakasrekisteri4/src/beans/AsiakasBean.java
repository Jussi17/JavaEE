package beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "asiakasBean")
@SessionScoped
public class AsiakasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nimi;
    private String osoite;
    private String puhelin;
    private String email;
    private String salasana;
    private String message;

    @Inject
    private DbBean dbBean;

    public AsiakasBean() {
    }

    public ArrayList<AsiakasBean> getAsiakkaat() {
        return dbBean.getAsiakkaat();
    }

    public String lisaaAsiakas() {
        dbBean.lisaaAsiakas(nimi, osoite, puhelin, email, salasana);
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Onnistui!", "Asiakas " + nimi + " lisätty!"));
        nimi = ""; osoite = ""; puhelin = ""; email = ""; salasana = "";
        return null;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNimi() { return nimi; }
    public void setNimi(String nimi) { this.nimi = nimi; }
    public String getOsoite() { return osoite; }
    public void setOsoite(String osoite) { this.osoite = osoite; }
    public String getPuhelin() { return puhelin; }
    public void setPuhelin(String puhelin) { this.puhelin = puhelin; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSalasana() { return salasana; }
    public void setSalasana(String salasana) { this.salasana = salasana; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}