package dev.zenfluxpro.bookstore.catalog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
//@ConfigurationPropertiesScan
@EnableConfigurationProperties(ConfigProperties.class)
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
