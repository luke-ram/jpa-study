package sooram.study.jpa.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SimpleOrderQueryRepository {

    private final EntityManager em;

    public List<SimpleOrderQueryDto> findOrderDtos() {
        return em.createQuery(
                "SELECT new sooram.study.jpa.repository.order.simplequery.SimpleOrderQueryDto(o.id,m.name,o.orderDate,o.status,d.address) from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderQueryDto.class)
                .getResultList();
    }
}
