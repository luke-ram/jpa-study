package sooram.study.jpa.repository.order.simplequery;

import lombok.Data;
import sooram.study.jpa.domain.Address;
import sooram.study.jpa.domain.Order;
import sooram.study.jpa.domain.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SimpleOrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}

