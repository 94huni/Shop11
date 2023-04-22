package com.shop3.shop.Controller;

import com.shop3.shop.Entity.Cart;
import com.shop3.shop.Entity.User;
import com.shop3.shop.Service.CartService;
import com.shop3.shop.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;


    @PostMapping("/addCart")
    public ResponseEntity<Cart> createCart(Authentication authentication, Long product_id){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(product_id, authentication));
    }

    @PutMapping("/removeCart")
    public ResponseEntity<Cart> removeCart(Authentication authentication, Long product_id){
        return ResponseEntity.ok(cartService.removeCart(product_id, authentication));
    }



//    @DeleteMapping("/checkOutCart")
//    public ResponseEntity<Void> checkOutCart(Authentication authentication){
//        cartService.checkoutCart(authentication);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
