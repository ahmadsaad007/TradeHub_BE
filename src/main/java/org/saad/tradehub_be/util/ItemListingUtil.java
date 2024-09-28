package org.saad.tradehub_be.util;

import lombok.experimental.UtilityClass;
import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.ReportListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.entity.data.Category;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.ItemListingReport;
import org.saad.tradehub_be.entity.data.User;

import java.util.Date;

@UtilityClass
public class ItemListingUtil {

    public static ItemListing buildItemListing(NewListingForm newListingForm, User user, Category category) {
        return ItemListing.builder()
                .itemId(newListingForm.getListingId())
                .price(newListingForm.getPrice())
                .listingAddress(newListingForm.getListingAddress())
                .zip(newListingForm.getZip())
                .sellerInfo(user)
                .isAvailable(Boolean.TRUE)
                .description(newListingForm.getDescription())
                .createdAt(newListingForm.getCreatedAt())
                .category(category)
                .name(newListingForm.getName())
                .imageUrls(newListingForm.getImageUrls())
                .build();
    }

    public static ItemListing getUpdatedItemListing(ItemListing existingListing, UpdateListingForm updateListingForm, Category category) {
        if (updateListingForm.getName() != null && !updateListingForm.getName().isEmpty()) {
            existingListing.setName(updateListingForm.getName());
        }

        if (updateListingForm.getDescription() != null && !updateListingForm.getDescription().isEmpty()) {
            existingListing.setDescription(updateListingForm.getDescription());
        }

        if (updateListingForm.getPrice() > 0) {
            existingListing.setPrice(updateListingForm.getPrice());
        }

        if (updateListingForm.getImageUrls() != null && !updateListingForm.getImageUrls().isEmpty()) {
            existingListing.setImageUrls(updateListingForm.getImageUrls());
        }

        if (updateListingForm.getListingAddress() != null && !updateListingForm.getListingAddress().isEmpty()) {
            existingListing.setListingAddress(updateListingForm.getListingAddress());
        }

        if (updateListingForm.getZip() != null && !updateListingForm.getZip().isEmpty()) {
            existingListing.setZip(updateListingForm.getZip());
        }

        if (category != null) {
            existingListing.setCategory(category);
        }

        if (updateListingForm.getCreatedAt() != null) {
            existingListing.setCreatedAt(updateListingForm.getCreatedAt());
        }

        return existingListing;
    }

    public static ReportListingForm getReport(ItemListingReport itemListingReport) {
        return ReportListingForm.builder()
                .itemId(itemListingReport.getItemId())
                .sellerId(itemListingReport.getSellerId())
                .buyerReportedBy(itemListingReport.getReportedBy())
                .timeReported(itemListingReport.getReportedAt() != null ? itemListingReport.getReportedAt() : new Date())
                .isReviewed(Boolean.FALSE)
                .reportType(itemListingReport.getReason())
                .build();
    }
}
