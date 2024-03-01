package com.example.warehouse.service;

import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    /*
   Implement the business logic for the ProductService  operations in this class
   Make sure to add required annotations
    */


    private ProductRepository productRepository;

    ProductService (ProductRepository productRepository){
        this.productRepository=productRepository;
    }


    //to post all the Product details
    //created->201
    //badRequest->400
    public Object postProduct(Product product) {
        try{
            Product productData= productRepository.save(product);
            return new ResponseEntity<>(productData, HttpStatusCode.valueOf(201));
        }
        catch(Exception err) {
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
    }

    //to get all the Product details
    //ok->200
    //badRequest->400
    public Object getProduct() {
        try{
            List<Product> productList= productRepository.findAll();
            if(productList.isEmpty())throw new ResponseStatusException(HttpStatus.valueOf(400));
            return  new ResponseEntity<>(productList,HttpStatusCode.valueOf(200));
        }catch(Exception err){
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
    }

    //to get the product with the value(pathVariable)
    //ok()->200
    //badRequest()->400
    public ResponseEntity<Object> getSimilarVendor(String value) {
        try{
            List<Product> productList= productRepository.findAllByVendor(value);
            if(productList.isEmpty())throw new ResponseStatusException(HttpStatus.valueOf(400));
            return  new ResponseEntity<>(productList,HttpStatusCode.valueOf(200));
        }catch(Exception err){
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
    }


    //to update the Product with id as pathVariable and Product as object in RequestBody
    //ok->200
    //badRequest->400
    public ResponseEntity<Object> updateProduct(int id, Product product) {
        try{
            Optional<Product> productData= productRepository.findById(id);
            if(productData.isEmpty()) throw new ResponseStatusException(HttpStatus.valueOf(400));
            var productGetData = getProduct(product, productData);
            Product updatedData= productRepository.save(productGetData);
            return new ResponseEntity<>(updatedData, HttpStatusCode.valueOf(200));
        }
        catch(Exception err) {
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
    }

    private static Product getProduct(Product product, Optional<Product> productData) {
        var productGetData = productData.get();
        productGetData.setPrice(product.getPrice());
        productGetData.setStock(product.getStock());

        return productGetData;
    }

    // to delete the product by using id as PathVariable
    //ok->200
    //badRequest->400
    public ResponseEntity<Object> deleteProductById(int id) {
        try{
            Optional<Product> productData= productRepository.findById(id);
            if(productData.isEmpty()) throw new ResponseStatusException(HttpStatus.valueOf(400));
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        catch(Exception err) {
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }
    }



}
