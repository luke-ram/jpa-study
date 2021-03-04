package sooram.study.jpa.repository;

import lombok.Getter;
import lombok.Setter;
import sooram.study.jpa.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
