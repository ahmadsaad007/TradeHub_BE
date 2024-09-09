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

    public void createListing(NewListingForm newListingForm) {
        // 1. Find or create the seller
        Seller seller = findOrCreateSeller(newListingForm.getSellerUsername());

        // 2. Find the category (throw an error if not found)
        Category category = getCategory(newListingForm.getCategoryName());

        ItemListing itemListing = ItemListingUtil.buildItemListing(newListingForm, seller, category);
        itemListingRepository.save(itemListing);
    }

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

    public void deleteListing(String itemId) {
        Optional<ItemListing> existingListing = itemListingRepository.findById(itemId);
        existingListing.ifPresent(itemListingRepository::delete);
    }

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
