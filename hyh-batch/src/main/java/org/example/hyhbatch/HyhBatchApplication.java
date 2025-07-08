package org.example.hyhbatch;

import io.hyh.hyhapplication.HyhApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {HyhBatchApplication.class, HyhApplicationConfiguration.class})
public class HyhBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyhBatchApplication.class, args);
    }

}
