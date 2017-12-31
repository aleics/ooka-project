package org.ooka.productstore.health;

import com.codahale.metrics.health.HealthCheck;

public class GraphQLHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
