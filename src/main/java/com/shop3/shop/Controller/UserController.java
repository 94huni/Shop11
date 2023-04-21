package com.shop3.shop.Controller;

import com.shop3.shop.Entity.User;
import com.shop3.shop.Repository.UserRepository;
import com.shop3.shop.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    //회원가입
//    @PostMapping("/signup")
//    public ResponseEntity<String> signUp(@RequestBody User user){
//        if(userRepository.findByUserid(user.getUserid()) != null){
//            return ResponseEntity.badRequest().body("아이디가 " + user.getUserid() + " 인사용자가 이미 존재합니다");
//        }
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setRoles("USER");
//        userRepository.save(user);
//
//        return ResponseEntity.ok("사용자가 등록되었습니다");
//    }

    //로직을 service 로 옮겨구현
    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody User user, String password2){
        userService.signUp(user, password2);
        return ResponseEntity.ok("사용자가 등록되었습니다");
    }

    //관리자가 회원정보수정
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, User user){
        User update = userService.updateUser(id, user);
        return ResponseEntity.ok(update);
    }

    //사용자가 직접 회원정보 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        if(!user.getId().equals(id)){
            throw new RuntimeException("본인정보만 수정할 수 있습니다!");
        }
        User update = userService.updateUser(id, user);
        return ResponseEntity.ok(update);
    }

    //id 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    //현재 접속중인 아이디조회
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        return ResponseEntity.ok(user);
    }

    //userid 조회
    @GetMapping("/admin/{userid}")
    public ResponseEntity<User> getUser(@PathVariable String userid){
        User user = userService.getUser(userid);
        if(user == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    //페이징처리가 된 모든유저정보  가져오기

    //모든유저정보 가져오기
    @GetMapping("/admin/allUser")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.allUser();
        return ResponseEntity.ok(users);
    }

    //유저정보 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("사용자가 삭제되었습니다");
    }

    //비밀번호 찾기

}
