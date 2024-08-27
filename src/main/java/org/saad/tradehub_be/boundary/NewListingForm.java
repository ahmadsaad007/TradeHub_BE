package org.saad.tradehub_be.boundary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
