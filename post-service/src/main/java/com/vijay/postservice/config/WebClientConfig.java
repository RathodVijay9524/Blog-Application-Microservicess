package com.vijay.postservice.config;


import com.vijay.postservice.client.CommentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;


    @Bean
    public WebClient commentWebClient() {
        return WebClient.builder().baseUrl("http://COMMENT-SERVICE")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public CommentClient inventoryClient() {
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(commentWebClient())).build()
                .createClient(CommentClient.class);
    }
}
