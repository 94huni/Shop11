package com.shop3.shop.Controller;

import com.shop3.shop.DTO.LoginFormDto;
import com.shop3.shop.DTO.UserDto;
import com.shop3.shop.Entity.User;
import com.shop3.shop.Repository.UserRepository;
import com.shop3.shop.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

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
    public ResponseEntity<String> singUp(@RequestBody User user,@RequestParam String password2){
        userService.signUp(user, password2);
        return ResponseEntity.status(HttpStatus.CREATED).body("사용자가 등럭되었습니다."); //201 Created
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginFormDto loginFormDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginFormDto.getUserid(),
                        loginFormDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String userid = "your_userid";
        String password = "your_password";
        String credentials = userid + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic" + encodedCredentials);

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();


    }

    //관리자가 회원정보수정
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, User user){
        User update = userService.updateUser(id, user);
        return ResponseEntity.ok(update);
    }

    //관리자가 ADMIN 권한 부여
    @PutMapping("/admin/authority/{id}")
    public ResponseEntity<User> updateUserAsAdmin(@PathVariable Long id, @Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }

        User updateUser = userService.updateUserAsAdmin(id, user);
        return ResponseEntity.ok(updateUser);
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
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    //현재 접속중인 아이디조회
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        return ResponseEntity.ok(user);
    }

    //userid 조회
    @GetMapping("/admin/{userid}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userid){
        UserDto user = userService.getUser(userid);
        if(user == null){
            return ResponseEntity.notFound().build(); //404
        } else {
            return ResponseEntity.ok(user); //200
        }
    }

    //페이징처리가 된 모든유저정보  가져오기

    //모든유저정보 가져오기
//    @GetMapping("/admin/allUser")
//    public ResponseEntity<List<User>> getAllUser(){
//        List<User> users = userService.allUser();
//        return ResponseEntity.ok(users); //200 Ok
//    }

    //유저정보 페이징처리
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/getAllUser")
    public ResponseEntity<Page<User>> getAllUser(@RequestParam(defaultValue = "0")int page,
                                                 @RequestParam(defaultValue = "10")int size,
                                                 @RequestParam(defaultValue = "") String searchKeyword,
                                                 Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        if(!user.getRoles().equals("ROLE_ADMIN")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); //403 FORBIDDEN
        }
        return ResponseEntity.ok(userService.getAllUser(page, size, searchKeyword)); //200 OK
    }


    //유저정보 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("사용자가 삭제되었습니다."); //204
    }

    //비밀번호 찾기

}
