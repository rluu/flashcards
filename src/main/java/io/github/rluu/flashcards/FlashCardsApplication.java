package io.github.rluu.flashcards;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.github.rluu.flashcards.FlashCardsApplication;
import io.github.rluu.flashcards.FlashCardsConfiguration;
import io.github.rluu.flashcards.db.UserDAO;

public class FlashCardsApplication extends Application<FlashCardsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FlashCardsApplication().run(args);
    }

    @Override
    public String getName() {
        return "FlashCards";
    }

    @Override
    public void initialize(final Bootstrap<FlashCardsConfiguration> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new MigrationsBundle<FlashCardsConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(FlashCardsConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final FlashCardsConfiguration configuration,
                    final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
//        environment.jersey().register(new UserResource(dao));
    }

}
