package org.saad.tradehub_be.entity.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.saad.tradehub_be.entity.data.actors.Seller;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * The ItemListing class represents a product or service listed on the TradeHub platform.
 * This class corresponds to the 'Item Listing' entity object in the proposal report.

 * An Item Listing includes attributes such as name, description, price, seller information,
 * images, and category.
 */

@Entity
@Table(name = "item_listings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemListing {

    @Id
    private String itemId;

    private String name;

    private String description;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private Seller sellerInfo;

    private List<String> imageUrls;

    private String listingAddress;

    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    private boolean isAvailable;

    private Date createdAt;

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Item ID: ").append(itemId).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Description: ").append(description).append("\n");
        details.append("Price: $").append(price).append("\n");
        details.append("Seller: ").append(sellerInfo.getUsername()).append("\n");
        details.append("Address: ").append(listingAddress).append(", ZIP: ").append(zip).append("\n");

        if (imageUrls != null && !imageUrls.isEmpty()) {
            details.append("Images:\n");
            for (String image : imageUrls) {
                details.append(" - ").append(image).append("\n");
            }
        }

        return details.toString();
    }
}
