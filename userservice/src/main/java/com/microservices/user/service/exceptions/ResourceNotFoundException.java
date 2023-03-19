
// we are creating an exception class
// main duty of this class is to throw the user defined exception
// when there is an exception "ResourceNotFoundException"

package com.microservices.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    //if there is no error message from user
    public ResourceNotFoundException() {
        super("Resource not found on server !!");
    }

    //if user wants to throw an error message to display
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
