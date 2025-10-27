package com.appsdeveloperblog.ws.emailNotificationMicroservice.Handler;


import com.appsdeveloperblog.ws.core.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "product-created-event-topic" , groupId = "product-created-events")
public class ProductCreatedEventHandler {



    private final Logger logger = LoggerFactory.getLogger(this.getClass()) ;

    @KafkaHandler
    public void handle(ProductCreatedEvent productCreatedEvenet)
    {

        logger.info("product has been received "+productCreatedEvenet.getPrice());




    }
}
