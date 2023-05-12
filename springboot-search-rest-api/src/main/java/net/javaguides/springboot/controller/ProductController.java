package net.javaguides.springboot.controller;

import net.javaguides.springboot.entity.Product;
import net.javaguides.springboot.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    // use constructor based dependency injection , thats why no autowired here


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create rest endpoint
    // http://localhost:8080/api/v1/products/search?query=mouse
    // bind query param @RequestParam
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    // this @RequestBody will bind the HTTP request that is json into Product object
    // you go to postman and select POST thats is HTTP post request
    // then go to body go to raw and select json and copy the json string for product object
    // bind this request body thats is json string to pojo with @RequestBody Product product to input
    // this product from postman to ur controller
    // http request body json string input
    //{
    //    "sku": "ABC",
    //        "name": "laptop",
    //        "description": "laptop-description",
    //        "active": true,
    //        "imageUrl": "laptop.png"
    //}
    // Status : 200OK output from postman
    //{
    //    "id": 1,
    //        "sku": "ABC",
    //        "name": "laptop",
    //        "description": "laptop-description",
    //        "active": true,
    //        "imageUrl": "laptop.png",
    //        "dateCreated": "2023-05-10T16:56:05.9921917",
    //        "dateUpdated": "2023-05-10T16:56:05.9921917"
    //}
    // straight away gets saved in sql database
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


}
