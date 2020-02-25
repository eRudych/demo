/*
 * This file is generated by jOOQ.
*/
package com.st.db.tables.records;


import com.st.db.tables.OrderDetail;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrderDetailRecord extends UpdatableRecordImpl<OrderDetailRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 363990063;

    /**
     * Setter for <code>public.order_detail.orderId</code>.
     */
    public void setOrderid(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.order_detail.orderId</code>.
     */
    public Long getOrderid() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.order_detail.productId</code>.
     */
    public void setProductid(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.order_detail.productId</code>.
     */
    public Long getProductid() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return OrderDetail.ORDER_DETAIL.ORDERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return OrderDetail.ORDER_DETAIL.PRODUCTID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getOrderid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getProductid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getOrderid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getProductid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailRecord value1(Long value) {
        setOrderid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailRecord value2(Long value) {
        setProductid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderDetailRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrderDetailRecord
     */
    public OrderDetailRecord() {
        super(OrderDetail.ORDER_DETAIL);
    }

    /**
     * Create a detached, initialised OrderDetailRecord
     */
    public OrderDetailRecord(Long orderid, Long productid) {
        super(OrderDetail.ORDER_DETAIL);

        set(0, orderid);
        set(1, productid);
    }
}
