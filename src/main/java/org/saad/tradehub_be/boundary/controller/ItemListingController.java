package org.saad.tradehub_be.boundary.controller;

import org.saad.tradehub_be.entity.data.ItemListingReport;
import org.saad.tradehub_be.services.ReportingService;
import org.saad.tradehub_be.util.ObjectMapperUtil;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.services.ItemListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemListingController {

    @Autowired
    private ItemListingService itemListingService;

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @GetMapping("/{itemId}")
    public List<ItemListing> viewItemListing(@PathVariable String itemId) {
        return itemListingService.findById(itemId);
    }

    @PostMapping("/report/")
    public ResponseEntity<String> reportItemListing(@RequestBody String report) throws Exception {
        ItemListingReport itemListingReport = objectMapperUtil.mapRequestBody(report, ItemListingReport.class);
        reportingService.reportListing(itemListingReport);
        return ResponseEntity.ok("Item Reported Successfully");
    }
}
