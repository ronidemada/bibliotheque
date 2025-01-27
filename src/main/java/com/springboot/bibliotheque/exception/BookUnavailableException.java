package com.springboot.bibliotheque.exception;

public class BookUnavailableException extends RuntimeException{
    public BookUnavailableException() {
        super ("Book already borrow by another user");
    }
}
