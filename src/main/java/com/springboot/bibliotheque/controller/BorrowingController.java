package com.springboot.bibliotheque.controller;

import com.springboot.bibliotheque.entity.Book;
import com.springboot.bibliotheque.entity.Borrowing;
import com.springboot.bibliotheque.entity.User;
import com.springboot.bibliotheque.service.BorrowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    public final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("")
    public void borrow(@RequestParam Long userId, @RequestParam Long bookId){
        borrowingService.borrow(userId,bookId);
    }

    @PatchMapping("/return/{id}")
    public void returnBorrow(@PathVariable Long id){
        borrowingService.returnBorrow(id);
    }


}
