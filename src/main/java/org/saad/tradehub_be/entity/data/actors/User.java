package org.saad.tradehub_be.entity.data.actors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;


/**
 * The User class serves as an abstract base class for different types of users.
 * Both Buyers and Sellers have common attributes such as username, email, and phone number, which are defined here.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "app_users")
public class User {

    @Column(unique = true, nullable = false)
    protected String username;

    @Id
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
