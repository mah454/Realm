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
        if (!adminExist()) {
            logger.debug("Create admin (superuser) account");
            userManager.register(new User("admin", "111111"));
            roleManager.register(new Role("admin", "superuser"));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private boolean adminExist() {
        userManager = new UserManager();
        roleManager = new RoleManager();
        User user = userManager.find("admin");
        Role role = roleManager.find("admin");
        return user != null && role != null;
    }
}
