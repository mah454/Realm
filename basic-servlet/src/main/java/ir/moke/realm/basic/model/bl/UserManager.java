package ir.moke.realm.basic.model.bl;

import ir.moke.realm.basic.model.dao.UserDao;
import ir.moke.realm.basic.model.to.User;

import java.util.List;

public class UserManager implements GenericBL<User> {
    @Override
    public void register(User user) {
        UserDao userDao = new UserDao();
        userDao.insert(user);
        userDao.close();
    }

    @Override
    public void modify(User user) {
        UserDao userDao = new UserDao();
        userDao.update(user);
        userDao.close();
    }

    @Override
    public void remove(long id) {
        UserDao userDao = new UserDao();
        userDao.delete(id);
        userDao.close();
    }

    @Override
    public User show(long id) {
        UserDao userDao = new UserDao();
        User user = userDao.select(id);
        userDao.close();
        return user;
    }

    @Override
    public List<User> showAll() {
        UserDao userDao = new UserDao();
        List<User> list = userDao.select();
        userDao.close();
        return list;
    }
}
