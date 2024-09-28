package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The UserProfileUpdate class represents the usecase where the user can update his profile information
 * Note that certain fields like userId and username cannot be updated
 * <p>
 * Used in use case for Update User Info
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdate {
    private String email;
    private String phoneNumber;
    private String address;
    private String zipCode;
}
