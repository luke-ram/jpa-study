package sooram.study.jpa.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {

    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;

    public OrderItemQueryDto(Long oderId, String itemName, int orderPrice, int count) {
        this.orderId = oderId;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
