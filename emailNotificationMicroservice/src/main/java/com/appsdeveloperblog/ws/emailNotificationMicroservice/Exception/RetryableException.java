package com.appsdeveloperblog.ws.emailNotificationMicroservice.Exception;

public class RetryableException extends RuntimeException{


    public RetryableException(String message) {
        super(message);
    }


    public RetryableException(Throwable cause) {
        super(cause);
    }


}
