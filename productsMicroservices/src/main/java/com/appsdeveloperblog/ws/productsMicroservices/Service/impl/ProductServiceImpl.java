package com.appsdeveloperblog.ws.productsMicroservices.Service.impl;

import com.appsdeveloperblog.ws.core.ProductCreatedEvent;
import com.appsdeveloperblog.ws.productsMicroservices.Producers.CreateProductRestModel;
import com.appsdeveloperblog.ws.productsMicroservices.Service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class ProductServiceImpl implements IProductService {




    ProductCreatedEvent productCreatedEvent ;
    @Autowired
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate ;
//
//    public ProductServiceImpl( KafkaTemplate<String , ProductCreatedEvent> kafkaTemplate) {
//
//        this.kafkaTemplate = kafkaTemplate ;
//    }




    private final Logger logger = LoggerFactory.getLogger(this.getClass()) ;



    @Override
    public String CreateProduct(CreateProductRestModel productRestModel) throws Exception{

        String productID = UUID.randomUUID().toString() ;

        ExecutorService executorService = Executors.newFixedThreadPool(6);



        // still we need to persisit the data to the database before publishing the evenet // do lateet


        CompletableFuture<CompletableFuture<SendResult<String, ProductCreatedEvent>>> future1 = CompletableFuture.supplyAsync(
                ()->

                kafkaTemplate.send( "product-created-event-topic" , productID , productCreatedEvent)

        )

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productID , productRestModel.getTittle() ,productRestModel.getPrice()  ,productRestModel.getQuantity()) ;
      CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send( "product-created-event-topic" , productID , productCreatedEvent) ;

       future.whenComplete( (result , exception) ->
               {
                   if(exception != null)
                   {
                       logger.error("error occured" + exception.getMessage());
                   }
                   else
                   {
                       logger.info(" successfully" + result.getRecordMetadata());


                   }


               }




               ) ;


       future.join() ; // if we add this this becomes synchrounous  ;


        logger.info("*********** before sending the result");

     SendResult<String ,ProductCreatedEvent > result   =  kafkaTemplate.send( "product-created-event-topic" , productID , productCreatedEvent).get() ;

        logger.info("*********** Returining product id");
        logger.info("parition" + result.getRecordMetadata().partition());
        logger.info("topic name" + result.getRecordMetadata().topic());
        logger.info("off set" + result.getRecordMetadata().offset());


       return productID ;

    }
}
