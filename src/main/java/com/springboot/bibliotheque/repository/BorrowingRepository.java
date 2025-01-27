package com.springboot.bibliotheque.repository;

import com.springboot.bibliotheque.entity.Borrowing;
import com.springboot.bibliotheque.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    int countByUser(User user);
}
