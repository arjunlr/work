package com.philips.itaap.supplychainit.ngh.config;

import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@XSlf4j
public class ApplicationConfig {

    @Autowired
    ApplicationProperties properties;

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .keepAlive(false)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getTimeout() * 1000)
                .responseTimeout(Duration.ofSeconds(properties.getTimeout()));
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(properties.getMemoryBufferSize())).build();
    }
}
