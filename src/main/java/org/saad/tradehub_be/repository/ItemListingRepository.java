package org.saad.tradehub_be.repository;

import org.saad.tradehub_be.data.ItemListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ItemListingRepository defines the contract for a repository that manages ItemListing entities.
 */
@Repository
public interface ItemListingRepository extends JpaRepository<ItemListing, String> {
    List<ItemListing> findAll();

    List<ItemListing> findByNameContaining(String query);

    List<ItemListing> findBySellerInfoUsername(String username);
}
