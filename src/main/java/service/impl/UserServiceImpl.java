package service.impl;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.UserService;
import utils.MailUtil;
import utils.UUIDUtil;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Siren Chen.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoSpringJdbc")
    private UserDao uDao;

    @Override
    public void register (User user) throws SQLException {
        user.setRegisterTime(new Date());
        user.setUid(UUIDUtil.genId());
        user.setActiveCode(UUIDUtil.genCode());
        user.setState(1);
        uDao.addUser(user);

//        String emailMsg = "<h1>Thanks for register in Siren's Book Store </h1> <br>" +
//                "please click the follow link to active your account, thinks!  <br> " +
//                "<a href='http://coder.sirenchen.com/SirenStore/user?method=doActive&activeCode="+
//                user.getActiveCode() +"'> active your account </a> <br>";
//
//        MailUtil.sendMail(user.getEmail(), emailMsg);
    }

    @Override
    public User login(String username, String password) throws SQLException {
        return uDao.findUserByNameAndPassword(username, password);
    }

    @Override
    public User activeUser (String activeCode) throws SQLException {
        User user = uDao.findUserByActiveCode(activeCode);

        if (user == null) {
            return null;
        }

        user.setState(1);
        uDao.updateUser(user);

        return user;
    }


    @Override
    public boolean isUsernameOK(String username) throws SQLException {

        return uDao.findUserByUsername(username) == null;
    }

}
