package com.acme.mainframe;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.api.BasicCacheContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BasicCacheContainer remoteCacheContainer(@Value("${infinispan.host}") String host, @Value("${infinispan.port}") String port) {
        ConfigurationBuilder builder = new ConfigurationBuilder().addServers(String.format("%s:%s", host, port));

        return new RemoteCacheManager(builder.create(), false);
    }
}
