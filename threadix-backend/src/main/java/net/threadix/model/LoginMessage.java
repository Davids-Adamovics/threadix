package net.threadix.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMessage {
    
    String message;
    Boolean status;
    String token;

    public LoginMessage(String message, Boolean status, String token) {
        this.message = message;
        this.status = status;
        this.token = token;
    }
}
