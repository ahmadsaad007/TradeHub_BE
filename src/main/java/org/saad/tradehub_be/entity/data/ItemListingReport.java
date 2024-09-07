package org.saad.tradehub_be.entity.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ItemListingReport {

    @Id
    private String itemId;

    private String reason;

    private String reportedBy;

    private Date reportedAt;
}
