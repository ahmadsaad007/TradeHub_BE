package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The SignUpRequest class represents the SignUp Data sent to the BE as part of the
 * Request Body when a Sign up Button is clicked.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    protected String username;
    protected String password;
    protected String email;
}
