package io.hyh.hyhapi;

import io.hyh.hyhapplication.HyhApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {HyhApiApplication.class, HyhApplicationConfiguration.class})
public class HyhApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyhApiApplication.class, args);
    }

}
