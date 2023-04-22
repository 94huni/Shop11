package com.shop3.shop.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    private int price;

    private String detail;

    private int stock;

    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime end;


    @ManyToOne
    private Category category;
}
