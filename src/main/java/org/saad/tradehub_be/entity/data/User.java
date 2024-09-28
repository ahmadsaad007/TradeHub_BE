package org.saad.tradehub_be.entity.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


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

    @OneToMany(mappedBy = "sellerInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemListing> listings; // Users who are sellers will have listings

    @ElementCollection
    private List<String> purchasedItemIds; // Store the IDs of items purchased by this user

}
