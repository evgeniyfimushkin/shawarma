package edu.evgen.shawarma.repositories;

import edu.evgen.shawarma.entities.ShawarmaOrder;

public interface OrderRepository {
    ShawarmaOrder save(ShawarmaOrder shawarmaOrder);
}
