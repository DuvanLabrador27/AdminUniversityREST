package com.adminuniversity.adminuniversityrest.exception;

public class UserWithSameUsernameAlreadyExists extends RuntimeException {
    public UserWithSameUsernameAlreadyExists(String message) {
        super(message);
    }
}
