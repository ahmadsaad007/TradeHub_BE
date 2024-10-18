package org.saad.tradehub_be.services;

import org.saad.tradehub_be.data.ItemListing;
import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.ItemListingRepository;

import org.saad.tradehub_be.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHistoryService {

    private final UserRepository userRepository;

    private final ItemListingRepository itemListingRepository;

    public OrderHistoryService(UserRepository userRepository, ItemListingRepository itemListingRepository) {
        this.userRepository = userRepository;
        this.itemListingRepository = itemListingRepository;
    }

    /**
     * Returns a list of all previously purchased items
     *
     * @param buyerId is the userId of the buyer
     * @return List<ItemListing> which is a List of all items previously purchased by the buyer
     */
    public List<ItemListing> getOrderHistory(String buyerId) {
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        List<String> purchaseIds = buyer.getPurchasedItemIds();
        return purchaseIds.stream()
                .map(itemListingRepository::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }
}

