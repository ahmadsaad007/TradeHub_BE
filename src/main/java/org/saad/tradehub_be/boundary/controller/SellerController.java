package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.boundary.util.ObjectMapperUtil;
import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.services.SellerControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Autowired
    private SellerControlService sellerControlService;

    @PostMapping("/create-item")
    public ResponseEntity<HttpStatus> createListing(@RequestBody String requestBody) {
        try {
            NewListingForm newListingForm = objectMapperUtil.mapRequestBody(requestBody, NewListingForm.class);
            sellerControlService.createNewListing(newListingForm);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-item/{itemId}")
    public ResponseEntity<HttpStatus> updateListing(@RequestBody String requestBody,
                                                    @PathVariable String itemId) {
        try {
            UpdateListingForm updateListingForm = objectMapperUtil.mapRequestBody(requestBody, UpdateListingForm.class);
            sellerControlService.updateListing(itemId, updateListingForm);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
