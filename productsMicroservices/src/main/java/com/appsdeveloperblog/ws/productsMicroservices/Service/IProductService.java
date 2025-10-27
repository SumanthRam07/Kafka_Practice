package com.appsdeveloperblog.ws.productsMicroservices.Service;

import com.appsdeveloperblog.ws.productsMicroservices.Producers.CreateProductRestModel;

public interface IProductService {


    String CreateProduct(CreateProductRestModel productRestModel) throws Exception ;
}
