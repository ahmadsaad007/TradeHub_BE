package org.saad.tradehub_be.entity.itemcard;

import lombok.*;

/**
 * The Image class represents an image associated with an item or service in the TradeHub platform.
 * This class corresponds to the 'images' attribute in the Item Listing entity object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    /**
     * URL of the image that points to the location where the image is stored.
     */
    private String url;

    /**
     * The format or type of the image, such as 'jpg', 'png', etc.
     */
    private String format;

    /**
     * The width of the image
     */
    private int width;

    /**
     * The height of the image
     */
    private int height;

    /**
     * The alternate Text to display if the image doesn't load
     */
    private String altText;
}
