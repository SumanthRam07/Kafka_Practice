package com.appsdeveloperblog.ws.productsMicroservices.RestController;

import com.appsdeveloperblog.ws.productsMicroservices.Error.ErrorMessage;
import com.appsdeveloperblog.ws.productsMicroservices.Producers.CreateProductRestModel;
import com.appsdeveloperblog.ws.productsMicroservices.Service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/products" , produces = MediaType.APPLICATION_JSON_VALUE)
public class  ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()) ;


    ProductServiceImpl productService ;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRestModel product)
    {

        String productID ;
      try {

         productID = productService.CreateProduct(product) ;


      }
      catch (Exception exception)
      {

       return   ResponseEntity.status(HttpStatus.OK).body(new ErrorMessage(LocalDateTime.now() , exception.getMessage())) ;


      }

        return ResponseEntity.status(HttpStatus.OK).body(productID) ;




    }
}
