package org.saad.tradehub_be.entity.data;

import java.util.Optional;

/**
 * This class provides methods for ItemListing lookup from the Database
 */
public class ItemListingDomainService {

    private final ItemListingRepository itemListingRepository;

    public ItemListingDomainService(ItemListingRepository itemListingRepository) {
        this.itemListingRepository = itemListingRepository;
    }

    /**
     * Returns a listing based on the ListingId.
     * This method would generally be called in the Select Item usecase where the user selects a
     * particular item
     *
     * @param listingId  the listing ID
     * @return Optional<ItemListing> if the listing was successfully found
     */
    public Optional<ItemListing> findById(String listingId) {
        return itemListingRepository.findById(listingId);
    }
}
