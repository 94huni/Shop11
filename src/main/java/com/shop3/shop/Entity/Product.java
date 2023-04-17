package com.shop3.shop.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String company;

    private String detail;

    private int stock;

    private LocalDateTime start;

    private LocalDateTime end;

    @ManyToMany
    private List<Tag> tag;
}
