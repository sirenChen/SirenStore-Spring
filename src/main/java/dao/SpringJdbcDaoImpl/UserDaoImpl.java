package dao.SpringJdbcDaoImpl;

import dao.SpringJdbcDaoImpl.daoMapper.UserMapper;
import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("userDaoSpringJdbc")
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (uid, username, password, gender, email, phone, activeCode, state, birthday, registerTime) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(), user.getPhone(),
                user.getActiveCode(), user.getState(), user.getBirthday(),user.getRegisterTime());
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET username=?,password=?,gender=?,email=?,phone=?,activeCode=null, state=? WHERE uid=?";

        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(),user.getGender(),user.getEmail(), user.getPhone(),
                user.getState(), user.getUid());
    }

    @Override
    public User findUserByActiveCode(String activeCode) throws SQLException {
        String sql = "SELECT * FROM user WHERE activeCode=?";

        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), activeCode);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User findUserByNameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=? and password=?";

        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";

        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}