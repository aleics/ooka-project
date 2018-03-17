package org.ooka.productstore;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.ooka.productstore.api.GraphQLResource;
import org.ooka.productstore.health.GraphQLHealthCheck;
import org.ooka.productstore.security.AuthRequestFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class App extends Application<AppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "productstore";
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final AppConfiguration configuration,
                    final Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "*");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        environment.healthChecks().register("graphQL", new GraphQLHealthCheck());
        environment.jersey().register(new GraphQLResource(jdbi));

        // Register authorization filter for all the resources of th service
        environment.jersey().register(AuthRequestFilter.class);
    }

}
