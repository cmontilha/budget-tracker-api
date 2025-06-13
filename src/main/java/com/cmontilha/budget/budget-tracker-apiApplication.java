package com.cmontilha.budget;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class budget-tracker-apiApplication extends Application<budget-tracker-apiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new budget-tracker-apiApplication().run(args);
    }

    @Override
    public String getName() {
        return "budget-tracker-api";
    }

    @Override
    public void initialize(final Bootstrap<budget-tracker-apiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final budget-tracker-apiConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
