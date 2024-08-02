package org.saad.tradehub_be.entity.actors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The User class serves as an abstract base class for different types of users.
 * Both Buyers and Sellers have common attributes such as username, email, and phone number, which are defined here.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {

    protected String username;

    protected String userId;

    protected String email;

    /**
     * Attribute to denote the account is a Verified account or not.
     */
    protected boolean isVerifiedAccount;

    protected String phoneNumber;

    protected String address;

    protected String zipCode;
}
