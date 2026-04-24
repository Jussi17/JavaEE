package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named(value = "dbBean")
@Dependent
public class DbBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public DbBean() {
    }

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jeedb1", "jeedb1", "jeedb1");
            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<AsiakasBean> getAsiakkaat() {
        ArrayList<AsiakasBean> list = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM asiakkaat");
            while (rs.next()) {
                AsiakasBean a = new AsiakasBean();
                a.setId(rs.getString("id"));
                a.setNimi(rs.getString("nimi"));
                a.setOsoite(rs.getString("osoite"));
                a.setPuhelin(rs.getString("puhelin"));
                a.setEmail(rs.getString("email"));
                a.setSalasana(rs.getString("salasana"));
                list.add(a);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Virhe haussa: " + e);
        }
        return list;
    }

    public void lisaaAsiakas(String nimi, String osoite, String puhelin, String email, String salasana) {
        try {
            Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO asiakkaat (nimi, osoite, puhelin, email, salasana) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, nimi);
            ps.setString(2, osoite);
            ps.setString(3, puhelin);
            ps.setString(4, email);
            ps.setString(5, salasana);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Virhe lisäyksessä: " + e);
        }
    }
}