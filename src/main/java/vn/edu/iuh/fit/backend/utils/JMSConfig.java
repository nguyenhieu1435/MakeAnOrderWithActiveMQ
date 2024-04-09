package vn.edu.iuh.fit.backend.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class JMSConfig {
    @Bean
    public DefaultJmsListenerContainerFactory containerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSessionTransacted(true);
        factory.setConcurrency("1-5");
        factory.setMaxMessagesPerTask(1);
        return factory;
    }
}
