package net.threadix.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String displayName;
    private String email;
    private String password;

    public UserDTO(String username, String displayName, String email, String password) {
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

}
