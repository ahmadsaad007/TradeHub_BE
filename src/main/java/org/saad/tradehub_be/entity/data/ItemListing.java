package org.saad.tradehub_be.entity.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.saad.tradehub_be.entity.data.actors.Seller;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * The ItemListing class represents a product or service listed on the TradeHub platform.
 * This class corresponds to the 'Item Listing' entity object in the proposal report.

 * An Item Listing includes attributes such as name, description, price, seller information,
 * images, and category.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemListing {

    private String listingId;

    private String name;

    private String description;

    private double price;

    private Seller sellerInfo;

    private List<Image> images;

    private String listingAddress;

    private String zip;

    private Category category;

    private boolean isAvailable;

    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Item ID: ").append(listingId).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Description: ").append(description).append("\n");
        details.append("Price: $").append(price).append("\n");
        details.append("Seller: ").append(sellerInfo.getUsername()).append("\n");
        details.append("Category: ").append(category.getName()).append("\n");
        details.append("Address: ").append(listingAddress).append(", ZIP: ").append(zip).append("\n");

        if (images != null && !images.isEmpty()) {
            details.append("Images:\n");
            for (Image image : images) {
                details.append(" - ").append(image.getUrl()).append("\n");
            }
        }

        return details.toString();
    }

    public void updateDetails(String newName, String newDescription, double newPrice, Category newCategory, String newAddress, String newZip) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newDescription != null && !newDescription.isEmpty()) {
            this.description = newDescription;
        }
        if (newPrice > 0) {
            this.price = newPrice;
        }
        if (newCategory != null) {
            this.category = newCategory;
        }
        if (newAddress != null && !newAddress.isEmpty()) {
            this.listingAddress = newAddress;
        }
        if (StringUtils.hasLength(newZip)) {
            this.zip = newZip;
        }
    }
}
