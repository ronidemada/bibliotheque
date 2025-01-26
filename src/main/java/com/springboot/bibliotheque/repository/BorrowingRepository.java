package com.springboot.bibliotheque.repository;

import com.springboot.bibliotheque.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

}
