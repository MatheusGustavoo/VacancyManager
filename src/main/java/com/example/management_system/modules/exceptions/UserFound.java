package com.example.management_system.modules.exceptions;

public class UserFound extends RuntimeException{
    public UserFound(String message) {
        super(message);
    }
}
