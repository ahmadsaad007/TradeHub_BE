package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.entity.data.Category;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.actors.Seller;
import org.saad.tradehub_be.repository.CategoryRepository;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.saad.tradehub_be.repository.SellerRepository;
import org.saad.tradehub_be.util.ItemListingUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final ItemListingRepository itemListingRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    public SellerService(ItemListingRepository itemListingRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.itemListingRepository = itemListingRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * This method is part of the Seller creates listing use case
     *
     * @param newListingForm is the data filled out with the info needed to create a new Listing
     */
    public void createListing(NewListingForm newListingForm) {

        Seller seller = findOrCreateSeller(newListingForm.getSellerUsername());

        Category category = getCategory(newListingForm.getCategoryName());

        ItemListing itemListing = ItemListingUtil.buildItemListing(newListingForm, seller, category);
        itemListingRepository.save(itemListing);
    }

    /**
     * This method is part of the Seller updates listing use case
     *
     * @param itemId            is the itemId of the listing being updated
     * @param updateListingForm is the data filled out with the info needed to create a new Listing
     */
    public void updateExistingListing(String itemId, UpdateListingForm updateListingForm) {
        Optional<ItemListing> existingListing = itemListingRepository.findById(itemId);

        if (existingListing.isPresent()) {
            Category category = getCategory(updateListingForm.getCategoryName());

            ItemListing itemListing = ItemListingUtil.getUpdatedItemListing(existingListing.get(), updateListingForm, category);
            itemListingRepository.save(itemListing);
        } else {
            throw new RuntimeException("Listing with ID " + itemId + " not found");
        }
    }

    /**
     * This method is part of the Seller deletes listing use case
     *
     * @param itemId is the itemId of the listing being deleted
     */
    public void deleteListing(String itemId) {
        Optional<ItemListing> existingListing = itemListingRepository.findById(itemId);
        existingListing.ifPresent(itemListingRepository::delete);
    }

    /**
     * This method is part of the Seller views all his listings use case
     *
     * @param username is the username of the seller
     */
    public List<ItemListing> findItemListingBySellerInfo(String username) {
        return Optional.ofNullable(itemListingRepository.findBySellerInfoUsername(username))
                .orElse(List.of());
    }

    private Seller findOrCreateSeller(String sellerUsername) {
        return sellerRepository.findByUsername(sellerUsername).orElseGet(() -> {
            Seller newSeller = new Seller();
            newSeller.setUsername(sellerUsername);
            newSeller.setEmail("");
            newSeller.setAddress("Unknown Address");
            return sellerRepository.save(newSeller);
        });
    }

    private Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category with name " + categoryName + " not found"));
    }
}
