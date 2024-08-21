package org.saad.tradehub_be.entity.data.actors;

import lombok.Data;
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
public class Seller extends User {

    private List<ItemListing> listings;

    public void addListing(ItemListing itemListing) {
        listings.add(itemListing);
    }

    public void removeListing(String listingId) {
        listings.removeIf(listing -> listing.getListingId().equals(listingId));
    }

    public List<ItemListing> getListings() {
        return new ArrayList<>(listings); // Returning a copy to avoid modification outside
    }
}
