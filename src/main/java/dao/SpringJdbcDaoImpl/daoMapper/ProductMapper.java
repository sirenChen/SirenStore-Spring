package dao.SpringJdbcDaoImpl.daoMapper;

import entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product p = new Product();

        p.setPid(resultSet.getString("pid"));
        p.setPname(resultSet.getString("pname"));
        p.setPrice(resultSet.getDouble("price"));
        p.setPimage(resultSet.getString("pimage"));
        p.setPdate(resultSet.getDate("pdate"));
        p.setPdesc(resultSet.getString("pdesc"));
        p.setPflag(resultSet.getInt("pflag"));
        p.setIsHot(resultSet.getInt("isHot"));
        p.setCid(resultSet.getInt("cid"));

        return p;
    }
}
