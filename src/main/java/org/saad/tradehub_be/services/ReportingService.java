package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.ReportListingForm;
import org.saad.tradehub_be.data.ItemListingReport;
import org.saad.tradehub_be.repository.ItemListingReportRespository;
import org.saad.tradehub_be.util.ItemListingUtil;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {

    private final ItemListingReportRespository itemListingReportRespository;

    public ReportingService(ItemListingReportRespository itemListingReportRespository) {
        this.itemListingReportRespository = itemListingReportRespository;
    }

    /**
     * This method is part of the Buyer reports Listing use case
     *
     * @param itemListingReport is the data filled out with the report info
     */
    public void reportListing(ItemListingReport itemListingReport) {
        if (itemListingReport == null) {
            throw new IllegalArgumentException("ItemListingReport cannot be null");
        }

        ReportListingForm reportListingForm = ItemListingUtil.getReport(itemListingReport);
        itemListingReportRespository.save(reportListingForm);
    }
}