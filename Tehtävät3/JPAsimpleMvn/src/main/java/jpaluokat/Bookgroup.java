package jpaluokat;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "bookgroup")
@NamedQueries({
    @NamedQuery(name = "Bookgroup.findAll", query = "SELECT b FROM Bookgroup b"),
    @NamedQuery(name = "Bookgroup.findById", query = "SELECT b FROM Bookgroup b WHERE b.bookgroup_id = :bookgroup_id")
})
public class Bookgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookgroup_id")
    private Integer bookgroup_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bookgroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Bookgroup() {}

    public Bookgroup(String name) {
        this.name = name;
    }

    public Integer getBookgroup_id() { return bookgroup_id; }
    public void setBookgroup_id(Integer bookgroup_id) { this.bookgroup_id = bookgroup_id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}