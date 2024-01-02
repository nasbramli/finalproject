package com.sctp.jpa.exception;

public class MessageNotReadableException extends RuntimeException{

    public MessageNotReadableException(){
        super("Data sent is not valid. Please check again.");
    }
}
