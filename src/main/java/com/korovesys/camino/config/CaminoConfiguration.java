package com.korovesys.camino.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.korovesys.camino.FlowInitializer;
import com.korovesys.camino.executor.FlowExecutor;
import com.korovesys.camino.model.FlowContext;
import com.korovesys.camino.validation.FlowValidator;

@Configuration
@ConditionalOnProperty(prefix = "spring.camino", name = "enabled", havingValue = "true")
public class CaminoConfiguration {

    @Bean
    public FlowInitializer flowInitializer() {
        return new FlowInitializer();
    }

    @Bean
    public FlowContext caminoContext() {
        return new FlowContext();
    }

    @Bean
    public FlowExecutor flowExecutor() {
        return new FlowExecutor();
    }
}
