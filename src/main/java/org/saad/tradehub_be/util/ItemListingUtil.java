package org.saad.tradehub_be.util;

import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.ReportListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.ItemListingReport;

public class ItemListingUtil {

    public static ItemListing getItemListing(NewListingForm newListingForm) {
        return ItemListing.builder().build();
    }

    public static ItemListing getUpdatedItemListing(UpdateListingForm updateListingForm) {
        return ItemListing.builder().build();
    }

    public static ItemListingReport getReport(ReportListingForm reportListingForm) {
        return ItemListingReport.builder().build();
    }
}
