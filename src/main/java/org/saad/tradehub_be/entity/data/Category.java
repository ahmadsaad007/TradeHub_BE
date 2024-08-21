package org.saad.tradehub_be.entity.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Category class represents the category under which an item or service can be listed.
 * Each category has an associated ID and a display name, which are used to categorize and display listings.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private String categoryId;
    private String name;
    private String description;
}
