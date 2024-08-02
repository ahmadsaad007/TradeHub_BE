package org.saad.tradehub_be.entity.actors;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The Buyer class represents a user who browses the TradeHub platform to purchase items or services.
 * This class extends the 'User' abstract class
 */

@Data
@NoArgsConstructor
@SuperBuilder
public class Buyer extends User {
}
