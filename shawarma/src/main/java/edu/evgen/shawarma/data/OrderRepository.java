package edu.evgen.shawarma.data;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<ShawarmaOrder, Long> {
}
