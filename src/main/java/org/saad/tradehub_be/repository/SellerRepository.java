package org.saad.tradehub_be.repository;

import org.saad.tradehub_be.entity.data.actors.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
    Optional<Seller> findByUsername(String sellerUsername);
}
