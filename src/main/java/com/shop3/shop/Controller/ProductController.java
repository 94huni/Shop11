package com.shop3.shop.Controller;

import com.shop3.shop.Entity.Product;
import com.shop3.shop.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }
    //상품아이디로 가져오기
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    //페이징 처리가된 전체상품정보

    //상품등록
    @PostMapping("/admin/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }
        Product createProduct = productService.createProduct(product);
        return ResponseEntity.ok(createProduct);
    }
    //상품수정
    @PutMapping("/admin/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }
        Product updateProduct = productService.updateProduct(productId, product);

        return ResponseEntity.ok(updateProduct);
    }
    //상품삭제
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        boolean isDelete = productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
