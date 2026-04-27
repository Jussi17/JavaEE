package jpajsf;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "asiakkaat")
@NamedQueries({
    @NamedQuery(name = "Asiakas.findAll", query = "SELECT a FROM Asiakas a")
})
public class Asiakas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nimi")
    private String nimi;

    @Column(name = "osoite")
    private String osoite;

    @Column(name = "puhelin")
    private String puhelin;

    @Column(name = "email")
    private String email;

    @Column(name = "salasana")
    private String salasana;

    public Asiakas() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
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
}