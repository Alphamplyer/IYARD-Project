package com.alphamplyer.microservice.orders.repositories.rowMappers;

import com.alphamplyer.microservice.orders.models.Order;
import com.alphamplyer.microservice.orders.models.enums.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Row Mapper to deserialize result of database into Order object
 */
public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order order = new Order();

        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("user_id"));
        order.setCreatorId(rs.getInt("creator_id"));
        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        order.setPaymentId(rs.getInt("payment_id"));
        order.setCreatedAt(rs.getTimestamp("created_at"));
        order.setUpdatedAt(rs.getTimestamp("updated_at"));

        return order;
    }
}
