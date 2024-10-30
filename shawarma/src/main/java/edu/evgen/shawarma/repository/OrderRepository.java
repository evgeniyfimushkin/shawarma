package edu.evgen.shawarma.repository;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ShawarmaOrder, Long> {
}
