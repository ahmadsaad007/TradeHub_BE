package org.saad.tradehub_be.boundary.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The ReportListingForm class represents the use case for Reporting a ItemListing for
 * going against standards and policies or for being incorrectly listed
 * <p>
 * Use Case: Report Listing
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportListingForm {
    private String itemId;
    private String sellerId;
    private String buyerReportedBy;
    private Date timeReported;
    private boolean isReviewed;
    private String reportType;
}
