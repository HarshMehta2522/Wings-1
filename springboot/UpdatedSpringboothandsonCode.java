//service
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

//data.sql
DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS Product(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    description varchar(255),
    vendor varchar(255),
    price int,
    stock int,
    currency varchar(255),
    image_url varchar(255),
    sku varchar(255) UNIQUE
);
//controller
package com.example.warehouse.controller;


import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    /*
  This controller would be responsible for the ProductController endpoints
  Add the required annotations and create the endpoints
   */

   
    private ProductService productService;

    ProductController (ProductService productService){
        this.productService=productService;
    }
    //to add the Product details using Product object
    @PostMapping("/product/add")
    private Object postProduct(@RequestBody Product product){
        return productService.postProduct(product);
    }

    //to get all the Product details
    @GetMapping("/product/get")
    private Object getProduct(){
        return productService.getProduct();

    }
    //to update the product with id as pathVariable and product as object in RequestBody
    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    // to delete the product by using id as PathVariable
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable int id){
        return productService.deleteProductById(id);
    }

    //to get the product items by vendor
    @GetMapping("/product/vendor")
    public ResponseEntity<Object> getSimilarVendor( String value){
        return productService.getSimilarVendor(value);
    }
}


//repo
package com.example.warehouse.repository;


import com.example.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
        List<Product> findAllByVendor(String vendor);
}
