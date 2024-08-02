package org.saad.tradehub_be.entity.actors;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.saad.tradehub_be.entity.itemcard.ItemListing;

import java.util.List;

/**
 * The Seller class represents a user who lists items or services for sale on the platform
 * This class extends the 'User' abstract class
 * A Seller has a list of Item Listings they have created
 */

@Data
@NoArgsConstructor
@SuperBuilder
public class Seller extends User {

    private List<ItemListing> listings;
}
