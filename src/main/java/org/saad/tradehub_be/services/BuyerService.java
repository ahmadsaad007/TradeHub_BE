package org.saad.tradehub_be.services;

import org.saad.tradehub_be.entity.data.actors.Buyer;
import org.saad.tradehub_be.repository.BuyerRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public void addPurchasedItem(String buyerId, String itemId) {
        Buyer buyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        buyer.getPurchasedItemIds().add(itemId);
        buyerRepository.save(buyer);
    }

}
