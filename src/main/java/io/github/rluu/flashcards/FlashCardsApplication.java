package io.github.rluu.flashcards;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.github.rluu.flashcards.api.FlashCard;
import io.github.rluu.flashcards.api.FlashCardList;
import io.github.rluu.flashcards.api.Role;
import io.github.rluu.flashcards.api.User;

public class FlashCardsApplication extends Application<FlashCardsConfiguration> {

    private final HibernateBundle<FlashCardsConfiguration> hibernate = 
	    new HibernateBundle<FlashCardsConfiguration>(User.class, 
                                                         Role.class,
                                                         FlashCard.class,
                                                         FlashCardList.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(FlashCardsConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new FlashCardsApplication().run(args);
    }

    @Override
    public String getName() {
        return "FlashCards";
    }

    @Override
    public void initialize(final Bootstrap<FlashCardsConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<FlashCardsConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(FlashCardsConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final FlashCardsConfiguration configuration,
                    final Environment environment) {

//	final UserDAO userDao = new UserDAO(hibernate.getSessionFactory());
//	final SimpleAuthenticator simpleAuthenticator = new SimpleAuthenticator(userDao);

//	final CachingAuthenticator<BasicCredentials, SimplePrincipal> cachingAuthenticator = 
//		new CachingAuthenticator<>(metricRegistry, 
//                                           simpleAuthenticator,
//                                           configuration.getAuthenticationCachePolicy());

//	environment.jersey().register(new AuthDynamicFeature(
//		new BasicCredentialAuthFilter.Builder<SimplePrincipal>()
//		.setAuthenticator(simpleAuthenticator)
//		.buildAuthFilter()));
//	environment.jersey().register(RolesAllowedDynamicFeature.class);

	//If you want to use @Auth to inject a custom Principal type into your resource
	//environment.jersey().register(new AuthValueFactoryProvider.Binder<>(SimplePrincipal.class));

	//        environment.jersey().register(new UserResource(userDao));
    }

}
