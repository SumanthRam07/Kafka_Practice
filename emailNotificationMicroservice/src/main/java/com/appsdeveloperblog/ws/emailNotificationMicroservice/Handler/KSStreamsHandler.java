package com.appsdeveloperblog.ws.emailNotificationMicroservice.Handler;


import com.appsdeveloperblog.ws.core.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = { "product-created-event-topic" ,"Streams-output-topic2" } , groupId = "product-created-events")
public class KSStreamsHandler {



    private final Logger logger = LoggerFactory.getLogger(this.getClass()) ;


    @KafkaListener(topics = "product-created-event-topic", groupId = "product-created-events")
    public void handleProductCreated(ProductCreatedEvent productCreatedEvent) {
        logger.info("Product created event: " + productCreatedEvent.getPrice());
    }

    @KafkaListener(topics = "Streams-output-topic2", groupId = "product-created-events")
    public void handleStreamsOutput(ProductCreatedEvent productCreatedEvent) {
        logger.info("Streams output event: " + productCreatedEvent.getPrice());
    }



}
