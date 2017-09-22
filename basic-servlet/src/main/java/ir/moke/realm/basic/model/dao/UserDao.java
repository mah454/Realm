package ir.moke.realm.basic.model.dao;

import ir.moke.realm.basic.model.DatabaseUtils;
import ir.moke.realm.basic.model.to.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DatabaseUtils implements GenericDao<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public UserDao() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,username VARCHAR(20) UNIQUE,password VARCHAR(20));";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public void insert(User user) {
        try {
            String sql = "INSERT INTO users (username,password) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE users SET username=? AND password=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try {
            String sql = "DELETE FROM users WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public List<User> select() {
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(unmarshaller(resultSet));
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return list;
    }

    @Override
    public User select(long id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = unmarshaller(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return user;
    }

    public User select(String username) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = unmarshaller(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return user;
    }

    private static User unmarshaller(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return user;
    }
}
