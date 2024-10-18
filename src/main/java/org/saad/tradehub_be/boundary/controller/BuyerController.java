package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.services.BuyerService;
import org.saad.tradehub_be.services.ItemListingService;
import org.saad.tradehub_be.data.ItemListing;
import org.saad.tradehub_be.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private ItemListingService itemListingService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private BuyerService buyerService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/search")
    public List<ItemListing> searchItemListings(@RequestParam("query") String query) {
        return itemListingService.searchItemListings(query);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{userId}/order-history")
    public List<ItemListing> orderItemListings(@PathVariable String userId) {
        return orderHistoryService.getOrderHistory(userId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{userId}/purchase")
    public ResponseEntity<String> addPurchaseItem(@PathVariable String userId, @RequestParam("iid") String itemId) {
        buyerService.addPurchaseOrder(userId, itemId);
        return ResponseEntity.ok("Item Added");
    }
}
