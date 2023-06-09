package com.shop3.shop.Service;

import com.shop3.shop.DTO.UserDto;
import com.shop3.shop.Entity.User;
import com.shop3.shop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void signUp(User user, String password2){
        if (userRepository.findByUserid(user.getUserid())!=null) {
            throw new IllegalArgumentException("아이디가 " + user.getUserid() + "인 사용자가 이미 있습니다.");
        }
        if(user.getPassword().equals(password2)){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setCreateTime(LocalDateTime.now());
            user.setRoles("ROLE_USER");
        }else {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
        userRepository.save(user);
    }

    public User updateUser(Long id ,User user){
        User update = userRepository.findById(id).orElseThrow(()->new RuntimeException("찾을수없습니다."));
        update.setPassword(user.getPassword());
        update.setEmail(user.getEmail());
        update.setUsername(user.getUsername());
        update.setPhone(user.getPhone());
        update.setAddress(user.getAddress());
        return update;
    }

    //페이징처리를 적용시킨 allUser
    public Page<User> getAllUser(int page, int size, String searchKeyword){
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findUserByUseridContaining(searchKeyword ,pageable);
    }

    //유저아이디로 정보를 보여주는 service
    public UserDto getUser(String userid){
        return userRepository.getByUserid(userid);
    }

    //현재접속자의 정보를 보여주는 service
    public User getCurrentUser(Authentication authentication){
        return userRepository.findByUserid(authentication.getName());
    }

    public UserDto getUser(Long id){
        return userRepository.getUserById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User updateUserAsAdmin(Long id, User user) {
        User updateUser = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("유저를 찾을수 없습니다."));
        updateUser.setRoles("ROLE_ADMIN");
        return userRepository.save(updateUser);
    }
}
