package sooram.study.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sooram.study.jpa.domain.Delivery;
import sooram.study.jpa.domain.Member;
import sooram.study.jpa.domain.Order;
import sooram.study.jpa.domain.OrderItem;
import sooram.study.jpa.domain.item.Item;
import sooram.study.jpa.repository.ItemRepository;
import sooram.study.jpa.repository.MemberRepository;
import sooram.study.jpa.repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
        //JPA 사용안하면 취소(상태 업데이트)가 발생시 여기서 추가 작업을 해줘야 함

    }

//    public List<Order> findOrders(OrderSearch orderSearch){
//
//    }


}
