package edu.evgen.kitchen.messaging;

import edu.evgen.kitchen.entities.ShawarmaOrder;

public interface OrderReciever {
    ShawarmaOrder receiveOrder();
}
