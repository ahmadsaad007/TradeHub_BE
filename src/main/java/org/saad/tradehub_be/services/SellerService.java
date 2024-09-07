package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.saad.tradehub_be.util.ItemListingUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final ItemListingRepository itemListingRepository;

    public SellerService(ItemListingRepository itemListingRepository) {
        this.itemListingRepository = itemListingRepository;
    }

    public void createListing(NewListingForm newListingForm) {
        ItemListing itemListing = ItemListingUtil.getItemListing(newListingForm);
        itemListingRepository.save(itemListing);
    }

    public void updateExistingListing(String itemId, UpdateListingForm updateListingForm) {
        Optional<ItemListing> existingListing = itemListingRepository.findById(itemId);
        if (existingListing.isPresent()) {
            ItemListing itemListing = ItemListingUtil.getUpdatedItemListing(updateListingForm);
            itemListingRepository.save(itemListing);
            itemListingRepository.delete(existingListing.get());
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
}
