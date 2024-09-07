package org.saad.tradehub_be.repository;

import org.saad.tradehub_be.entity.data.ItemListingReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemListingReportRespository extends JpaRepository<ItemListingReport, String> {
}
