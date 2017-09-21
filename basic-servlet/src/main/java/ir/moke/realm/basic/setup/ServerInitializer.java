package ir.moke.realm.basic.setup;

import ir.moke.realm.basic.model.bl.UserManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerInitializer implements ServletContextListener {
    private UserManager userManager ;
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private boolean adminExist() {
        userManager = new UserManager();
        userManager.
    }
}
