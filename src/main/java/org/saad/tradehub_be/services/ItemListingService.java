package org.saad.tradehub_be.services;

import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class provides methods for ItemListing lookup from the Database
 */
@Service
public class ItemListingService {

    private final ItemListingRepository itemListingRepository;

    public ItemListingService(ItemListingRepository itemListingRepository) {
        this.itemListingRepository = itemListingRepository;
    }

    /**
     * Returns a listing based on the ListingId.
     * This method would generally be called in the Select Item usecase where the user selects a
     * particular item
     *
     * @param listingId the listing ID
     * @return List<ItemListing> if the listing was successfully found
     */
    public List<ItemListing> findById(String listingId) {
        return itemListingRepository.findById(listingId)
                .stream().toList();
    }

    /**
     * Returns a listing based on the user provided query string.
     * This method is called in user searches for item use case (using name of the item)
     *
     * @param query is a String that contains text describing the name of the item
     * @return List<ItemListing> if the listing was successfully found or else empty list
     */
    public List<ItemListing> searchItemListings(String query) {
        return Optional.ofNullable(itemListingRepository.findByNameContaining(query))
                .orElse(List.of());
    }
}
