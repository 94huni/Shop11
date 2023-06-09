package com.shop3.shop.Service;

import com.shop3.shop.Entity.Product;
import com.shop3.shop.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    //RequiredArgConstructor 사용시 final 을 사용하지않으면 정상적으로 작동이안됨
    private final ProductRepository productRepository;


    //아이디값에 대한 상품정보 가져오기
    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("이 번호로 제품을 찾을수없습니다!" + id));
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

        product.setStart(LocalDateTime.now());
        product.setEnd(LocalDateTime.now().plusDays(14)); // 시작일 + 14일

        return productRepository.save(product);
    }

    //페이징처리가 완료된 전체 페이지 가져오기
    public Page<Product> getProductPage(int page, int size, String searchKeyword){
        Pageable pageable = PageRequest.of(page, size, Sort.by("start").descending());
        return productRepository.findProductByNameContaining(searchKeyword, pageable);
    }

    //상품변경
    public Product updateProduct(Long productId, Product product){
        Product updateProduct = productRepository.findById(productId).orElseThrow(()->new RuntimeException("상품을 찾을 수 없습니다."));
        updateProduct.setName(product.getName());
        updateProduct.setDetail(product.getDetail());
        updateProduct.setStock(product.getStock());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setImage(product.getImage());
        updateProduct.setCategory(product.getCategory());
        return productRepository.save(updateProduct);
    }

    //상품삭제
    public boolean deleteProduct(Long id){
        productRepository.deleteById(id);
        return true;
    }
}
