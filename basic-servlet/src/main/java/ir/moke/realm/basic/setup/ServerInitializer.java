package ir.moke.realm.basic.setup;

import ir.moke.realm.basic.model.bl.RoleManager;
import ir.moke.realm.basic.model.bl.UserManager;
import ir.moke.realm.basic.model.to.Role;
import ir.moke.realm.basic.model.to.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerInitializer implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(ServerInitializer.class);
    private UserManager userManager;
    private RoleManager roleManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (adminUserExist()) {
            logger.debug("Create Admin User");
            userManager.register(new User("admin", "111111"));
        }

        if (adminRoleExist()) {
            logger.debug("Create Admin Role");
            roleManager.register(new Role("admin", "admin"));
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private boolean adminUserExist() {
        userManager = new UserManager();
        User user = userManager.find("admin");
        return user != null;
    }

    private boolean adminRoleExist() {
        roleManager = new RoleManager();
        Role role = roleManager.find("admin");
        return role != null;
    }
}
