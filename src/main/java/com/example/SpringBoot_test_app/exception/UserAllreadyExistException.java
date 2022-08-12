package com.example.SpringBoot_test_app.exception;

public class UserAllreadyExistException extends Exception {
    public UserAllreadyExistException(String string) {
        super(string);
    }
}
