package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.services.ItemListingService;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/buyer")
public class BuyerController {

    @Autowired
    private ItemListingService buyerListingService;

    @GetMapping("/search")
    public List<ItemListing> searchItemListings(@RequestParam("query") String query) {
        return buyerListingService.searchItemListings(query);
    }
}
