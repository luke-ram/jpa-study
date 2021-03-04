package sooram.study.jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sooram.study.jpa.domain.Address;
import sooram.study.jpa.domain.Member;
import sooram.study.jpa.domain.Order;
import sooram.study.jpa.domain.OrderStatus;
import sooram.study.jpa.domain.item.Book;
import sooram.study.jpa.domain.item.Item;
import sooram.study.jpa.domain.item.NotEnoughStockException;
import sooram.study.jpa.repository.OrderRepository;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 100);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        Assertions.assertEquals(1, getOrder.getOrderItems().size());
        Assertions.assertEquals(10000 * orderCount, getOrder.getTotalPrice());
        Assertions.assertEquals(98, book.getStockQuantity());

    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원 1");
        member.setAddress(new Address("서울", "경기", "123-123"));

        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        Assertions.assertEquals(10, item.getStockQuantity());


    }

    @Test
    public void 상품주문_재고량초과() throws Exception {
        Member member = createMember();
        Item createBook = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), createBook.getId(), orderCount);
        });

    }

}