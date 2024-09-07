package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * The NewListingForm class represents the use case for Creating a new ItemListing on TradeHub platform
 * <p>
 * Use Case: Create a new Listing
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewListingForm {
    private String listingId;
    private String name;
    private String description;
    private double price;
    private String sellerUsername;
    private List<String> imageUrls;
    private String listingAddress;
    private long zip;
    private String categoryName;
    private Date createdAt;
}
