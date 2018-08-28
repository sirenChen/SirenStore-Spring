package dao.SpringJdbcDaoImpl.daoMapper;

import entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();

        category.setCid(resultSet.getInt("cid"));
        category.setCdesc(resultSet.getString("cdesc"));
        category.setCname(resultSet.getString("cname"));

        return category;
    }
}
