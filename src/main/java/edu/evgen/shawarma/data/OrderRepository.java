package edu.evgen.shawarma.data;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, Long> {
    //ShawarmaOrder save(ShawarmaOrder shawarmaOrder);
    //Spring сам по названию и возвращаемому значению поймёт, как реализовать этот метод
    List<ShawarmaOrder> findByDeliveryName(String deliveryName);

    // глагод read/find/get равны
    List<ShawarmaOrder> readOrdersByDeliveryStreetAndPlacedAtBetween(
            String deliveryStreet,
            Date startDate,
            Date endDate
    );

    /*
     Spring Data в названии методов может читать различные типы операторов
     - isAfter,After, IsLessThanEqual, IsNull, IsNot, Between, IsBetween... и тд, их множество
     */
    @Query("SELECT o FROM ShawarmaOrder o WHERE o.deliveryStreet = 'Lenina'")
    List<ShawarmaOrder> readOrdersDeliveredInLenina();
}
