package dao.SpringJdbcDaoImpl.daoMapper;

import entity.Product;
import entity.order.Order;
import entity.order.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderItemMapper implements RowMapper<OrderItem> {

    private Order order;

    public OrderItemMapper(Order order) {
        this.order = order;
    }

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        Product p = new Product();
        OrderItem oi = new OrderItem();

        p.setPid(resultSet.getString("pid"));
        p.setPname(resultSet.getString("pname"));
        p.setPrice(resultSet.getDouble("price"));
        p.setPimage(resultSet.getString("pimage"));
        p.setPdate(resultSet.getDate("pdate"));
        p.setPdesc(resultSet.getString("pdesc"));
        p.setPflag(resultSet.getInt("pflag"));
        p.setIsHot(resultSet.getInt("isHot"));
        p.setCid(resultSet.getInt("cid"));

        oi.setOoid(resultSet.getString("ooid"));
        oi.setQuantity(resultSet.getInt("quantity"));
        oi.setSubtotal(resultSet.getDouble("subtotal"));

        oi.setOrder(this.order);
        oi.setProduct(p);

        return oi;
    }
}
