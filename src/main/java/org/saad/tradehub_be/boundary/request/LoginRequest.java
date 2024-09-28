package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The LoginRequest class represents the Incoming Request Object
 * with the username and encrypted Password
 * <p>
 * Used in use case for Loggin In a yser
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
