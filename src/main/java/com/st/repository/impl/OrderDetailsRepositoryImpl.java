package com.st.repository.impl;

import com.st.db.tables.records.OrderDetailRecord;

import com.st.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;


import static com.st.db.tables.OrderDetail.ORDER_DETAIL;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    private final DSLContext dsl;

    @Override
    public boolean addProduct(long orderId, long productId) {
//        _OrderDetailsRecord orderDelailsRecord = dsl.insertInto(_ORDER_DETAILS, _ORDER_DETAILS.ORDERID, _ORDER_DETAILS.PRODUCTID)
//                .values(orderId,productId);
//        return orderDelailsRecord.getValue(_ORDER_DETAILS.ID);
        return false;
    }

    @Override
    public boolean deleteProduct(long orderId, long id) {
        return false;
    }

    @Override
    public List getAllProducts(long orderId) {
        return null;
    }

    @Override
    public boolean deleteAllProducts(long orderId) {
        return false;
    }

//    private boolean insert(Long orderId, Long productId) {
//        try {
//            dsl.insertInto(_ORDER_DETAILS, _ORDER_DETAILS.ORDERID, _ORDER_DETAILS.PRODUCTID)
//                    .values(orderId,productId);
//            return true;
//        }catch (Exception ex){
//            log.error(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public boolean addProduct(long orderId, long productId) {
//        return insert(orderId,productId);
//    }
//
//    @Override
//    public boolean deleteProduct(long orderId, long productId) {
//        try {
//            dsl.deleteFrom(_ORDER_DETAILS)
//                    .where(_ORDER_DETAILS.ORDERID.eq(orderId)).and(_ORDER_DETAILS.PRODUCTID.eq(productId)).execute();
//            return true;
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public List<Long> getAllProducts(long orderId) {
//        return dsl.select(_ORDER_DETAILS.PRODUCTID).from(_ORDER_DETAILS)
//                .orderBy(_ORDER_DETAILS.ORDERID)
//                .fetchInto(Long.class);
//    }
//
//    @Override
//    public boolean deleteAllProducts(long orderId) {
//        try {
//            dsl.deleteFrom(_ORDER_DETAILS)
//                    .where(_ORDER_DETAILS.ORDERID.eq(orderId)).execute();
//            return true;
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return false;
//        }
//    }
}
