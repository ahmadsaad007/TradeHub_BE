package org.saad.tradehub_be.services;

import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.actors.Buyer;
import org.saad.tradehub_be.repository.BuyerRepository;
import org.saad.tradehub_be.repository.ItemListingRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHistoryService {

    private final BuyerRepository buyerRepository;

    private final ItemListingRepository itemListingRepository;

    public OrderHistoryService(BuyerRepository buyerRepository, ItemListingRepository itemListingRepository) {
        this.buyerRepository = buyerRepository;
        this.itemListingRepository = itemListingRepository;
    }

    public List<ItemListing> getOrderHistory(String buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        List<String> purchaseIds = buyer.getPurchasedItemIds();
        return purchaseIds.stream()
                .map(itemListingRepository::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }
}

