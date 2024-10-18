package org.saad.tradehub_be.services;

import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerService {

    private final UserRepository userRepository;

    public BuyerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method adds the item to the purchase History of the buyer
     *
     * @param userId is the userId of the Buyer
     * @param itemId is the itemId of the itemListing purchased
     */
    public void addPurchaseOrder(String userId, String itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> purchasedItemIds = user.getPurchasedItemIds();
        if (purchasedItemIds == null) {
            purchasedItemIds = new ArrayList<>();
        }

        if (!purchasedItemIds.contains(itemId)) {
            purchasedItemIds.add(itemId);
            user.setPurchasedItemIds(purchasedItemIds);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Item is already in purchase history.");
        }
    }
}
