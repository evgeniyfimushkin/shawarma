package edu.evgen.shawarma.data;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, String> {
}
