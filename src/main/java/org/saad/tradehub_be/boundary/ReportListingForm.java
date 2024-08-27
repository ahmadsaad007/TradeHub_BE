package org.saad.tradehub_be.boundary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportListingForm {
    private String iid;
    private String sellerId;
    private String buyerReportedBy;
    private Date timeReported;
    private boolean isReviewed;
    private String reportType;
}
