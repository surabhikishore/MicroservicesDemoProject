package io.surabhi.discoveryserver;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

public class DiscoveryClientServer {
    @SpringBootApplication
    @RestController
    public class EurekaClientApplication implements GreetingController {

        @Autowired
        @Lazy
        private EurekaClient eurekaClient;

        @Value("${spring.application.name}")
        private String appName;

        public void main(String[] args) {
            SpringApplication.run(EurekaClientApplication.class, args);
        }

        @Override
        public String greeting() {
            return String.format(
                    "Hello from '%s'!", eurekaClient.getApplication(appName).getName());
        }
    }

}
