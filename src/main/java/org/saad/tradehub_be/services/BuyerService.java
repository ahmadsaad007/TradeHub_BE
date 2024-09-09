package org.saad.tradehub_be.services;

import org.saad.tradehub_be.entity.data.actors.Buyer;
import org.saad.tradehub_be.repository.BuyerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public void addPurchaseOrder(String userId, String itemId) {
        Buyer buyer = buyerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        List<String> purchasedItemIds = buyer.getPurchasedItemIds();
        if (purchasedItemIds == null) {
            purchasedItemIds = new ArrayList<>();
        }

        if (!purchasedItemIds.contains(itemId)) {
            purchasedItemIds.add(itemId);
            buyer.setPurchasedItemIds(purchasedItemIds);
            buyerRepository.save(buyer);
        } else {
            throw new RuntimeException("Item is already in the purchase history.");
        }
    }
}
