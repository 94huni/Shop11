package com.shop3.shop.Entity;

import lombok.Data;

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

    private LocalDateTime createTime;

    @OneToOne
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;
}
