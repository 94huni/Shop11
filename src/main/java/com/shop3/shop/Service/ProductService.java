package com.shop3.shop.Service;

import com.shop3.shop.Entity.Product;
import com.shop3.shop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    //아이디값에 대한 상품정보 가져오기
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }

    //상품를 입력받아 상품정보 가져오기
    public Product getProductName(String name){
        return productRepository.findByName(name);
    }

    //제조사를 입력받아 상품정보 가져오기
    public List<Product> getProductCompany(String company){
        return productRepository.findByCompany(company);
    }

    //모든상품정보
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    //상품등록
    public Product createProduct(Product product){
        Product create = new Product();
        create.setName(product.getName());
        create.setDetail(product.getDetail());
        create.setCompany(product.getCompany());
        create.setStart(LocalDateTime.now());
        create.setTag(product.getTag());
        create.setStock(product.getStock());
        return create;
    }
    //상품삭제
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
