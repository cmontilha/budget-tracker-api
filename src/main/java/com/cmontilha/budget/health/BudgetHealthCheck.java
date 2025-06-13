package com.cmontilha.budget.health;

import com.codahale.metrics.health.HealthCheck;

public class BudgetHealthCheck extends HealthCheck {
    @Override
    protected Result check() {
        return Result.healthy();
    }
}
