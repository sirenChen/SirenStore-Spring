package service.impl;

import dao.CategoryDao;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Siren Chen.
 */

@Component("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    @Qualifier("categoryDaoSpringJdbc")
    private CategoryDao categoryDao;

    @Override
    public List<Category> showAllCategory() throws SQLException {

        return categoryDao.queryAll();
    }

    @Override
    public Category findCategoryById(int cid) throws SQLException {

        return categoryDao.findCategoryById(cid);
    }
}
