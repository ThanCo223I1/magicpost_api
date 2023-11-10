package com.magicpost.repo;

import com.magicpost.model.OrderType;
import com.magicpost.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderTypeRepo extends JpaRepository<OrderType, Long> {
    @Query("SELECT ot.type FROM OrderType ot WHERE ot.order.id = :orderId")
    List<Type> findTypesByOrderId(@Param("orderId") Long orderId);
}
