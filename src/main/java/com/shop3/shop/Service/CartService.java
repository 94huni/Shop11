package com.shop3.shop.Service;

import com.shop3.shop.Entity.Cart;
import com.shop3.shop.Entity.Product;
import com.shop3.shop.Entity.User;
import com.shop3.shop.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    public void CreateCart(Long productId, Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        Product product = productService.getProduct(productId).orElseThrow(()->new RuntimeException("상품을 찾을수없습니다"));
        Cart cart = user.getCart();
        if(cart == null){
            cart = new Cart();
            cart.setUser(user);
        }
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    public void removeCart(Long productId, Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        Product product = productService.getProduct(productId).orElseThrow(() -> new RuntimeException("상품을 찾을수 없습니다."));
        Cart cart = user.getCart();
        if(cart == null){
            throw new RuntimeException("장바구니를 찾을수없습니다.");
        }
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }

    //결제완료시 장바구니를 지워주는 메서드
    public void checkoutCart(Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        Cart cart = user.getCart();
        if(cart == null || cart.getProducts().isEmpty()){
            throw new RuntimeException("장바구니가 비어있습니다.");
        }
        cart.getProducts().clear();
        cartRepository.save(cart);
    }
}
