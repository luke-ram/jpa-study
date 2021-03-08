package sooram.study.jpa.repository.order.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sooram.study.jpa.domain.Address;
import sooram.study.jpa.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "orderId")
public class OrderFlatDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private Address address;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderFlatDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address,  String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
