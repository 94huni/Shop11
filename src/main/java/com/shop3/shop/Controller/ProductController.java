package com.shop3.shop.Controller;

import com.shop3.shop.Entity.Product;
import com.shop3.shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    //상품이름으로 가져오기
    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProduct(@PathVariable String productName){
        Product product = productService.getProductName(productName);
        return ResponseEntity.ok(product);
    }
    //전체상품
    @GetMapping("/allProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }
    //상품아이디로 가져오기
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId){
        Product product = productService.getProduct(productId);
        if(product != null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }
    //상품등록
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }
        Product createProduct = productService.createProduct(product);
        return ResponseEntity.ok(createProduct);
    }
    //상품수정
    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }
        Product updateProduct = productService.updateProduct(productId, product);

        return ResponseEntity.ok(updateProduct);
    }
    //상품삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        boolean isDelete = productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
