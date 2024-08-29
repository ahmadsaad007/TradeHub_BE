package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.services.ItemListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemListingController {

    @Autowired
    private ItemListingService itemListingService;

    @GetMapping("/{itemId}")
    public List<ItemListing> viewItemListing(@PathVariable String itemId) {
        return itemListingService.findById(itemId);
    }

    @PostMapping("/report/{itemId}")
    public void reportItemListing(@PathVariable String itemId) {
        itemListingService.reportListing(itemId);
    }
}
