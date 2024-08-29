package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The SignUpRequestDTO class represents the SignUp Data sent to the BE as part of the
 * Request Body when a Sign up Button is clicked. This class is essentially a Boundary Object but given
 * it's usage in the application of business logic makes an appearance here.
 * I named it DTO cause essentially it is transferring Data from FE to BE. Naming is symbolic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    protected String username;
    protected String password;
    protected String email;
}
