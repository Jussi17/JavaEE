package com.example.springbookapi.repository;

import com.example.springbookapi.entity.Bookgroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookgroupRepository extends JpaRepository<Bookgroup, Integer> {
}