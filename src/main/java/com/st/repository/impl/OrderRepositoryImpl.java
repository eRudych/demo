package com.st.repository.impl;

import com.st.db.tables.records.OrderRecord;
import com.st.entity.Order;
import com.st.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.st.db.tables.Order.ORDER;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final DSLContext dsl;

    private long insert(Order order) {
        OrderRecord ordersRecord = dsl.insertInto(ORDER, ORDER.USERID, ORDER.PHONE, ORDER.ADDRESS, ORDER.DESCRIPTION, ORDER.ACTION, ORDER.CREATED_AT)
                .values(order.getIdUser(), order.getPhone(), order.getAddress(), order.getDescription(), order.isAction(), order.getCreate_at())
                .returning(ORDER.ID)
                .fetchOne();
        return ordersRecord.getValue(ORDER.ID);
    }

    @Override
    public Order create(Order order) {
        return getOrder(insert(order));
    }

    @Override
    public boolean close(long id) {
        try {
            dsl.update(ORDER)
                    .set(ORDER.ACTION, false)
                    .where(ORDER.ID.eq(id)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Order getOrder(long id) {
        return dsl.selectFrom(ORDER)
                .where(ORDER.ID.eq(id))
                .fetchOneInto(Order.class);
    }

}