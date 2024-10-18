package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.data.ItemListing;
import org.saad.tradehub_be.services.SellerService;
import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Autowired
    private SellerService sellerService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create-item")
    public ResponseEntity<Object> createListing(@RequestBody String requestBody) {
        try {
            NewListingForm newListingForm = objectMapperUtil.mapRequestBody(requestBody, NewListingForm.class);
            sellerService.createListing(newListingForm);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Create an Item Listing Fail " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/update-item/{itemId}")
    public ResponseEntity<Object> updateListing(@RequestBody String requestBody,
                                                    @PathVariable String itemId) {
        try {
            UpdateListingForm updateListingForm = objectMapperUtil.mapRequestBody(requestBody, UpdateListingForm.class);
            sellerService.updateExistingListing(itemId, updateListingForm);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Update an Item Listing Fail " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/delete-item/{itemId}")
    public ResponseEntity<Object> deleteListing(@PathVariable String itemId) {
        try {
            sellerService.deleteListing(itemId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete an Item Listing Fail " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get-listings/{username}")
    public List<ItemListing> getAvailableListings(@PathVariable String username) {
        return sellerService.findItemListingBySellerInfo(username);
    }
}
