package com.smartpoke.api.feature.product.controller;

import com.smartpoke.api.feature.product.dto.ProductDto;
import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAll().stream().map(ProductDto::new).toList();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        ProductDto product = new ProductDto(productService.findById(id));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
        ProductDto productDto = new ProductDto(productService.createProduct(product.toEntity()));
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto product){
        ProductDto productDto = new ProductDto(productService.updateProduct(id, product.toEntity()));
        return ResponseEntity.ok().body(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){throw new UnsupportedOperationException("Not implemented yet");}

    @GetMapping("/syncOpenFoodFacts")
    public ResponseEntity<String> syncOpenFoodFacts(){
        try{
            List<ProductDto> products = productService.syncProducts().stream().map(ProductDto::new).toList();
            return ResponseEntity.ok().body("Initializing Sync openfoodfacts data manually\n"+products.toString());
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("An error has occurred"+e.getMessage());
        }

    }

    @GetMapping("/fetchProductInfo/{barcode}")
    public ResponseEntity<ProductDto> fetchProductInfo(@PathVariable String barcode){
        ProductDto productDto = new ProductDto(productService.fetchProductDetails(barcode));
        return ResponseEntity.ok().body(productDto);
    }

}
