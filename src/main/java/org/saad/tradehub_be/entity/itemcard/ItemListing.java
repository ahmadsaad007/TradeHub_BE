package org.saad.tradehub_be.entity.itemcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.saad.tradehub_be.entity.actors.Seller;

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

    /**
     * Information about the seller who created this listing.
     */
    private Seller sellerInfo;

    /**
     * A list of images associated with the item or service.
     */
    private List<Image> images;

    /**
    * address where the item is available or the service is provided.
    */
    private String listingAddress;

    /**
     * zip code associated with the item or the service.
     */
    private String zip;

    /**
     * The category under which this listing falls.
     */
    private Category category;
}
