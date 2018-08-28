package dao.SpringJdbcDaoImpl.daoMapper;

import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setUid(resultSet.getString("uid"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setGender(resultSet.getString("gender"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setActiveCode(resultSet.getString("activeCode"));
        user.setState(resultSet.getInt("state"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setRegisterTime(resultSet.getDate("registerTime"));

        return user;
    }
}
