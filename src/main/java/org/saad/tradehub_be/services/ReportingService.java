package org.saad.tradehub_be.services;

import org.saad.tradehub_be.boundary.request.ReportListingForm;
import org.saad.tradehub_be.entity.data.ItemListingReport;
import org.saad.tradehub_be.repository.ItemListingReportRespository;
import org.saad.tradehub_be.util.ItemListingUtil;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {

    private final ItemListingReportRespository itemListingReportRespository;

    public ReportingService(ItemListingReportRespository itemListingReportRespository) {
        this.itemListingReportRespository = itemListingReportRespository;
    }

    public void reportListing(String itemId, ReportListingForm reportListingForm) {
        ItemListingReport itemListingReport = ItemListingUtil.getReport(reportListingForm);
        itemListingReportRespository.save(itemListingReport);
    }
}