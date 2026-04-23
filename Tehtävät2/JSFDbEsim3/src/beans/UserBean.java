package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String ajaxmessage;
    private String savemessage;

    @Inject
    private DbBean dbBean;

    public UserBean() {
    }

    public String getAjaxmessage() {
        if (username != null && !username.isEmpty()) {
            ajaxmessage = dbBean.getMessage(username);
        }
        return ajaxmessage;
    }

    public String save() {
        String result = dbBean.saveUser(username, password);
        if (result.equals("success")) {
            savemessage = "Käyttäjä " + username + " tallennettu onnistuneesti!";
        } else if (result.equals("exists")) {
            savemessage = "Tunnus on jo käytössä!";
        } else {
            savemessage = "Virhe tallennuksessa!";
        }
        return null;
    }

    public String getSavemessage() { return savemessage; }
    public void setSavemessage(String savemessage) { this.savemessage = savemessage; }
    public String getAjaxmessageValue() { return ajaxmessage; }
    public void setAjaxmessage(String ajaxmessage) { this.ajaxmessage = ajaxmessage; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}