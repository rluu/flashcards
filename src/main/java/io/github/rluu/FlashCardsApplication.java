package io.github.rluu;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final FlashCardsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
