package com.shop3.shop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //로그인 아이디
    private String userid;

    //닉네임
    private String username;

    private String password;

    private String email;

    private String address;

    private String phone;

    private String roles;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @OneToOne
    //@JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "user")
    //@JsonIgnore
    private List<Order> orderList;
}
