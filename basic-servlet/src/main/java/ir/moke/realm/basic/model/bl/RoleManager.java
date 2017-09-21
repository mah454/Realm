package ir.moke.realm.basic.model.bl;

import ir.moke.realm.basic.model.dao.RoleDao;
import ir.moke.realm.basic.model.to.Role;

import java.util.List;

public class RoleManager implements GenericBL<Role> {
    @Override
    public void register(Role role) {
        RoleDao roleDao = new RoleDao();
        roleDao.insert(role);
        roleDao.close();
    }

    @Override
    public void modify(Role role) {
        RoleDao roleDao = new RoleDao();
        roleDao.update(role);
        roleDao.close();
    }

    @Override
    public void remove(long id) {
        RoleDao roleDao = new RoleDao();
        roleDao.delete(id);
        roleDao.close();
    }

    @Override
    public List<Role> find() {
        RoleDao roleDao = new RoleDao();
        List<Role> list = roleDao.select();
        roleDao.close();
        return list;
    }

    @Override
    public Role find(long id) {
        RoleDao roleDao = new RoleDao();
        Role role = roleDao.select(id);
        roleDao.close();
        return role;
    }

    public Role find(String username) {
        RoleDao roleDao = new RoleDao();
        Role role = roleDao.select(username);
        roleDao.close();
        return role;
    }
}
