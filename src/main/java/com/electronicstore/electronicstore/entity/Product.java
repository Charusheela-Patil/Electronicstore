package com.electronicstore.electronicstore.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {

    @Id
    private String productId;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_desc", length = 10000)
    private String description;

    @Column(name = "product_price")
    private int price;

    private int discountedPrice;

    @Column(name = "product_quantity")
    private int quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;

    @Column(name = "product_imagename")
    private String productImageName;


}
