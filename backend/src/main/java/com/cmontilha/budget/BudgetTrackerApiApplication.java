package com.cmontilha.budget;

import com.cmontilha.budget.api.UserResource;
import com.cmontilha.budget.core.Transaction;
import com.cmontilha.budget.core.User;
import com.cmontilha.budget.db.TransactionDAO;
import com.cmontilha.budget.db.UserDAO;
import com.cmontilha.budget.health.BudgetHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.db.DataSourceFactory;

public class BudgetTrackerApiApplication extends Application<BudgetTrackerApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BudgetTrackerApiApplication().run(args);
    }

    private final HibernateBundle<BudgetTrackerApiConfiguration> hibernateBundle =
            new HibernateBundle<BudgetTrackerApiConfiguration>(User.class, Transaction.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(BudgetTrackerApiConfiguration config) {
                    return config.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "budget-tracker-api";
    }

    @Override
    public void initialize(final Bootstrap<BudgetTrackerApiConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final BudgetTrackerApiConfiguration config, final Environment environment) {
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        final TransactionDAO transactionDAO = new TransactionDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new UserResource(userDAO, transactionDAO));
        environment.healthChecks().register("app", new BudgetHealthCheck());
    }
}