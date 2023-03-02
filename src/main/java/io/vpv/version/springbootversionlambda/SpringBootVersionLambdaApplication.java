package io.vpv.version.springbootversionlambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@SpringBootApplication
//https://howtodoinjava.com/spring-boot2/spring-boot-cache-example/
//@EnableCaching
public class SpringBootVersionLambdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVersionLambdaApplication.class, args);
    }

}
