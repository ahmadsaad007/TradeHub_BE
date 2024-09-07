package org.saad.tradehub_be.entity.data.actors;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.saad.tradehub_be.entity.data.ItemListing;

import java.util.ArrayList;
import java.util.List;

/**
 * The Seller class represents a user who lists items or services for sale on the platform
 * This class extends the 'User' class
 * A Seller has a list of Item Listings they have created
 */
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = false)
public class Seller extends User {

    @OneToMany(mappedBy = "sellerInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemListing> listings;
}
