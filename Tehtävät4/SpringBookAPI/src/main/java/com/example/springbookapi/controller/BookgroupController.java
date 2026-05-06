package com.example.springbookapi.controller;

import java.util.List;
import com.example.springbookapi.entity.Bookgroup;
import com.example.springbookapi.repository.BookgroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookgroups")
public class BookgroupController {

    @Autowired
    private BookgroupRepository bookgroupRepository;

    @GetMapping
    public List<Bookgroup> getAllBookgroups() {
        return bookgroupRepository.findAll();
    }

    @GetMapping("/{id}")
    public Bookgroup getBookgroupById(@PathVariable Integer id) {
        return bookgroupRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Bookgroup createBookgroup(@RequestBody Bookgroup bookgroup) {
        return bookgroupRepository.save(bookgroup);
    }

    @PutMapping("/{id}")
    public Bookgroup updateBookgroup(@PathVariable Integer id, @RequestBody Bookgroup bookgroupDetails) {
        Bookgroup bookgroup = bookgroupRepository.findById(id).orElse(null);
        if (bookgroup != null) {
            bookgroup.setName(bookgroupDetails.getName());
            return bookgroupRepository.save(bookgroup);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBookgroup(@PathVariable Integer id) {
        bookgroupRepository.deleteById(id);
        return "Bookgroup deleted successfully!";
    }
}