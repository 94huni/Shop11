package com.shop3.shop.Controller;

import com.shop3.shop.Entity.Product;
import com.shop3.shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    //전체상품
    @GetMapping("/allProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> product = productService.getAllProduct();
        return ResponseEntity.ok(product); //200 OK
    }
    //상품아이디로 가져오기
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product); //200 OK
    }

    //상품 이름으로 가져오기
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name){
        Product product = productService.getProductName(name);
        return ResponseEntity.ok(product); //200 Ok
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<List<Product>> getProductByCompany(@PathVariable String company){
        List<Product> product = productService.getProductCompany(company);
        return ResponseEntity.ok(product); //200 Ok
    }

    //페이징 처리가된 전체상품정보

    //상품등록
    @PostMapping("/admin/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }
        Product createProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct); //201 Created
    }
    //상품수정
    @PutMapping("/admin/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }
        Product updateProduct = productService.updateProduct(productId, product);

        return ResponseEntity.ok(updateProduct); //200 OK
    }
    //상품삭제
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        boolean isDelete = productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.noContent().build(); //204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}
