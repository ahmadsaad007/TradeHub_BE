package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.data.Category;
import org.saad.tradehub_be.data.ItemListing;
import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.CategoryRepository;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.saad.tradehub_be.repository.UserRepository;
import org.saad.tradehub_be.util.ItemListingUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final ItemListingRepository itemListingRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public SellerService(ItemListingRepository itemListingRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.itemListingRepository = itemListingRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    /**
     * This method is part of the Seller creates listing use case
     *
     * @param newListingForm is the data filled out with the info needed to create a new Listing
     */
    @Transactional
    public void createListing(NewListingForm newListingForm) {
        User user = findOrPromoteToSeller(newListingForm.getSellerUsername());
        Category category = getCategory(newListingForm.getCategoryName());

        ItemListing itemListing = ItemListingUtil.buildItemListing(newListingForm, user, category);
        itemListingRepository.save(itemListing);

    }

    /**
     * This method is part of the Seller updates listing use case
     *
     * @param itemId            is the itemId of the listing being updated
     * @param updateListingForm is the data filled out with the info needed to create a new Listing
     */
    @Transactional
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
    @Transactional
    public void deleteListing(String itemId) {
        Optional<ItemListing> existingListing = itemListingRepository.findById(itemId);

        if (existingListing.isPresent()) {
            itemListingRepository.delete(existingListing.get());
        } else {
            throw new RuntimeException("Listing with ID " + itemId + " not found");
        }
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

    private User findOrPromoteToSeller(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isSeller()) {
            user.setSeller(true);
            userRepository.save(user);
        }

        return user;
    }

    private Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category with name " + categoryName + " not found"));
    }
}
