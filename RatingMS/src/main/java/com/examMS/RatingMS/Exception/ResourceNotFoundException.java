package com.examMS.RatingMS.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("No such rating found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
