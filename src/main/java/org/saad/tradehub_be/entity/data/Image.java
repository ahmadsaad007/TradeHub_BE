package org.saad.tradehub_be.entity.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Image class represents an image associated with an item or service in the TradeHub platform.
 * This class corresponds to the 'images' attribute in the Item Listing entity object.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

    private String url;

    /**
     * The format or type of the image, such as 'jpg', 'png', etc.
     */
    private String format;

    private int width;

    private int height;

    /**
     * The alternate Text to display if the image doesn't load
     */
    private String altText;
}
