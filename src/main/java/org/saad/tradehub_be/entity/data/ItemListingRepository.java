package org.saad.tradehub_be.entity.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * ItemListingRepository defines the contract for a repository that manages ItemListing entities.
 */
public interface ItemListingRepository extends JpaRepository<ItemListing, Long> {
    List<ItemListing> findAll();
    Optional<ItemListing> findById(String listingId);
    List<ItemListing> findByNameOrDescription(String name, String description);
}
