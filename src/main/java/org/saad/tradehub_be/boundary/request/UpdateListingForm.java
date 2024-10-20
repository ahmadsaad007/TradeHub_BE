package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * The UpdateListingForm class represents the use case for Updating existing ItemListing on TradeHub platform
 * <p>
 * Use Case: Update Listing
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateListingForm {
    private String name;
    private String description;
    private double price;
    private String sellerUsername;
    private List<String> imageUrls;
    private String listingAddress;
    private String zip;
    private String categoryName;
    private Date createdAt;
}
