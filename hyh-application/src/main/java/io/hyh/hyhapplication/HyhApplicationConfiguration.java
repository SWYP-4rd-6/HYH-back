package io.hyh.hyhapplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {
        "io.hyh.hyhapplication.*.infra"
})
@EntityScan(basePackages = {
        "io.hyh.hyhapplication.*.domain",
        "io.hyh.hyhapplication.*.infra"
})
public class HyhApplicationConfiguration {
}
