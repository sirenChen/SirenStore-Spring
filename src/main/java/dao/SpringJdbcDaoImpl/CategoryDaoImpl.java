package dao.SpringJdbcDaoImpl;

import dao.CategoryDao;
import dao.SpringJdbcDaoImpl.daoMapper.CategoryMapper;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;


@Component("categoryDaoSpringJdbc")
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> queryAll() throws SQLException {
        String sql = "SELECT * FROM category";

        return jdbcTemplate.query(sql, new CategoryMapper());
    }

    @Override
    public Category findCategoryById(int cid) throws SQLException {
        String sql = "SELECT * FROM category WHERE cid=?";

        try {
            return jdbcTemplate.queryForObject(sql, new CategoryMapper(), cid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
