package ir.moke.realm.basic.model.dao;

import ir.moke.realm.basic.model.DatabaseUtils;
import ir.moke.realm.basic.model.to.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends DatabaseUtils implements GenericDao<Role> {
    public RoleDao() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS roles(id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,username VARCHAR(20),role_name VARCHAR(20));";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Role role) {
        try {
            String sql = "INSERT INTO roles (username,role_name) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getUsername());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try {
            String sql = "UPDATE roles SET username=? AND role_name=? WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getUsername());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.setLong(3, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            String sql = "DELETE FROM roles WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> select() {
        List<Role> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM roles";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(unmarshaller(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Role select(long id) {
        Role role = new Role();
        try {
            String sql = "SELECT * FROM roles WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = unmarshaller(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public Role select(String username) {
        Role role = new Role();
        try {
            String sql = "SELECT * FROM roles WHERE username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = unmarshaller(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    private static Role unmarshaller(ResultSet resultSet) {
        Role role = new Role();
        try {
            role.setId(resultSet.getLong("id"));
            role.setUsername(resultSet.getString("username"));
            role.setRoleName(resultSet.getString("role_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
