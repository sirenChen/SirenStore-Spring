package dao.SpringJdbcDaoImpl.daoMapper;

import entity.order.Order;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();

        order.setOid(resultSet.getString("oid"));
        order.setTotal(resultSet.getDouble("total"));
        order.setOrderTime(resultSet.getDate("orderTime"));
        order.setState(resultSet.getInt("state"));
        order.setReceiverAdd(resultSet.getString("receiverAdd"));
        order.setReceiverName(resultSet.getString("receiverName"));
        order.setReceiverPhone(resultSet.getString("receiverPhone"));

        return order;
    }
}
