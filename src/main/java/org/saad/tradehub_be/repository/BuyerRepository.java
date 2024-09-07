package org.saad.tradehub_be.repository;

import org.saad.tradehub_be.entity.data.actors.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, String> {
}
