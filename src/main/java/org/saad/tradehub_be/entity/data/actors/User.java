package org.saad.tradehub_be.entity.data.actors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The User class serves as an abstract base class for different types of users.
 * Both Buyers and Sellers have common attributes such as username, email, and phone number, which are defined here.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    protected String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected String userId;

    protected String password;

    protected String email;

    /**
     * Attribute to denote the account is a Verified account or not.
     */
    protected boolean isVerifiedAccount;

    protected boolean isSeller;

    protected String phoneNumber;

    protected String address;

    protected String zipCode;

    public void updateEmail(String newEmail) {
        if (isValidEmail(newEmail)) {
            this.email = newEmail;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
