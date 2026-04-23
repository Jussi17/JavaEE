package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named(value = "dbBean")
@Dependent
public class DbBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    public DbBean() {
        this.message = "";
    }

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        }
        String url = "jdbc:mysql://localhost/jeedb1";
        try {
            conn = DriverManager.getConnection(url, "jeedb1", "jeedb1");
            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public String getMessage(String value) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM users WHERE username='" + value + "'");
            int rivilkm = 0;
            while (rs.next()) {
                rivilkm++;
            }
            if (rivilkm == 1) {
                message = "On jo käytössä, valitse joku muu!";
            } else {
                message = "Käytettävissä!";
            }
            conn.close();
            return message;
        } catch (Exception ex) {
            System.out.println("Tuli poikkeus " + ex);
            return null;
        }
    }

    public String saveUser(String username, String password) {
        try {
            Connection conn = connect();
            // Tarkistetaan onko tunnus jo käytössä
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM users WHERE username='" + username + "'");
            int rivilkm = 0;
            while (rs.next()) {
                rivilkm++;
            }
            if (rivilkm > 0) {
                conn.close();
                return "exists";
            }
            // Tallennetaan uusi käyttäjä
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users (username, password) VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            conn.close();
            return "success";
        } catch (Exception ex) {
            System.out.println("Tuli poikkeus " + ex);
            return "error";
        }
    }
}