package com.appsdeveloperblog.ws.productsMicroservices.Producers;


import com.appsdeveloperblog.ws.core.ProductCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {


    private KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }




    @Bean
    public Map<String, Object> producerConfigs() {
        return kafkaProperties.buildProducerProperties();
    }

    @Bean
    NewTopic CreateTopic()
    {
        return TopicBuilder.name("product-created-event-topic")
                .partitions(3).replicas(3).configs(Map.of("min.insync.replicas" , "2"))
                .build() ;


    }

    @Bean
    List<NewTopic> ListOfTopics()
    {

        return  List.of(TopicBuilder.name("Streams-input-topic1").partitions(1)
                .replicas(1).configs(Map.of("min.insync.replicas" , "2")).build()  ,
        TopicBuilder.name("Streams-output-topic2").partitions(1)
                .replicas(1).configs(Map.of("min.insync.replicas" , "2")).build()) ;



    }

        @Bean
    ProducerFactory<String, ProductCreatedEvent> producerFactory()
    {

        return new DefaultKafkaProducerFactory<>(producerConfigs()) ;


    }

    @Bean
    KafkaTemplate<String,ProductCreatedEvent> KafkaTemplate()
    {
        return new KafkaTemplate<String , ProductCreatedEvent>(producerFactory());
    }



}
