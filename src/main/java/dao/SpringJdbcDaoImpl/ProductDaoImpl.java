package dao.SpringJdbcDaoImpl;

import dao.ProductDao;
import entity.Product;
import dao.SpringJdbcDaoImpl.daoMapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by siren on 2017/6/27.
 */
@Component("productDaoSpringJdbc")
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (pid, pname, price, pimage, pdate, pdesc, pflag, isHot, cid) " +
                "VALUES (?,?,?,?,?,?,1,?,?)";

        jdbcTemplate.update(sql, product.getPid(), product.getPname(), product.getPrice(), product.getPimage(),
                product.getPdate(), product.getPdesc(), product.getIsHot(), product.getCid());
    }

    @Override
    public Product findProductById(String pid) throws SQLException {
        String sql = "SELECT * FROM product WHERE pid=?";

        try {
            return jdbcTemplate.queryForObject(sql, new ProductMapper(), pid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Product> findProductsByCategory(int page, int limit, int cid) throws SQLException {
        String sql = "SELECT * FROM product WHERE cid=? limit ?,?";

        return jdbcTemplate.query(sql, new ProductMapper(), cid, (page-1)*limit, limit);
    }

    @Override
    public List<Product> findNewProducts() throws SQLException {
        String sql = "SELECT * FROM product ORDER BY pdate limit 3";

        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        String sql = "SELECT * FROM product WHERE isHot = 1";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public long countProduct(int cid) throws SQLException {
        String sql = "SELECT COUNT(*) FROM product WHERE cid=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{cid}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
