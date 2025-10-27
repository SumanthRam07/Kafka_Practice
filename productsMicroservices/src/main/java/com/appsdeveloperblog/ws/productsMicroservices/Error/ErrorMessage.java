package com.appsdeveloperblog.ws.productsMicroservices.Error;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ErrorMessage {

    private LocalDateTime localDateTime ;
   private String message ;

    public ErrorMessage(LocalDateTime localDateTime, String message) {
        this.localDateTime = localDateTime;
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

