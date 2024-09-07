package org.saad.tradehub_be.entity.data.actors;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.saad.tradehub_be.entity.data.ItemListing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The Buyer class represents a user who browses the TradeHub platform to purchase items or services.
 * This class extends the 'User' class
 */
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Buyer extends User {

    private List<String> purchasedItemIds;
}
