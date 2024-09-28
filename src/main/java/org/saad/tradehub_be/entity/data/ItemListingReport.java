package org.saad.tradehub_be.entity.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemListingReport {

    private String itemId;

    private String sellerId;

    private String reason;

    private String reportedBy;

    private Date reportedAt;
}
