package com.example.springbookapi.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "bookgroup")
public class Bookgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookgroup_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bookgroup", cascade = CascadeType.ALL)
    private List<Book> books;

    public Bookgroup() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}