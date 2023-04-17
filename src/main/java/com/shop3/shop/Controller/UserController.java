package com.shop3.shop.Controller;

import com.shop3.shop.Entity.User;
import com.shop3.shop.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user){
        if(userRepository.findByUserid(user.getUserid()) != null){
            return ResponseEntity.badRequest().body("아이디가 " + user.getUserid() + " 인사용자가 이미 존재합니다");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        userRepository.save(user);

        return ResponseEntity.ok("사용자가 등록되었습니다");
    }
}
