package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.services.ItemListingService;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/buyer")
public class BuyerController {

    @Autowired
    private ItemListingService itemListingService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/search")
    public List<ItemListing> searchItemListings(@RequestParam("query") String query) {
        return itemListingService.searchItemListings(query);
    }

    @GetMapping("/{userId}/order-history")
    public List<ItemListing> orderItemListings(@PathVariable String userId) {
        return orderHistoryService.getOrderHistory(userId);
    }
}
