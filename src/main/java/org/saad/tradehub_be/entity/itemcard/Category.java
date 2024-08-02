package org.saad.tradehub_be.entity.itemcard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * The Category enum represents the category under which an item or service can be listed.
 * Each category has an associated ID and a display name, which are used to categorize and display listings.
 */

@Getter
@RequiredArgsConstructor
public enum Category {

    ELECTRONICS(1, "Electronics"),
    FASHION(2, "Fashion"),
    HOME(3, "Home"),
    BOOKS(4, "Books"),
    SPORTS(5, "Sports"),
    AUTOMOTIVE(6, "Automotive"),
    TOYS(7, "Toys"),
    HEALTH(8, "Health"),
    BEAUTY(9, "Beauty");

    private final int categoryId;

    private final String displayName;

    //Method to get Category using CategoryID
    public static Category getCategoryById(int id) {
        for (Category category : values()) {
            if (category.getCategoryId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category with ID " + id);
    }
}
