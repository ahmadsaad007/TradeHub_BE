package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.data.ItemListingReport;
import org.saad.tradehub_be.services.ReportingService;
import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.data.ItemListing;
import org.saad.tradehub_be.services.ItemListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemListingController {

    @Autowired
    private ItemListingService itemListingService;

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{itemId}")
    public ResponseEntity<Object> viewItemListing(@PathVariable String itemId) {
        try {
            return ResponseEntity.ok(itemListingService.findById(itemId));
        } catch (Exception e) {
            return new ResponseEntity<>("Item Search Failed " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/report")
    public ResponseEntity<Object> reportItemListing(@RequestBody String report) {
        try {
            ItemListingReport itemListingReport = objectMapperUtil.mapRequestBody(report, ItemListingReport.class);
            reportingService.reportListing(itemListingReport);
            return ResponseEntity.ok("Item Reported Successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("Item Report Fail " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }

    }
}
