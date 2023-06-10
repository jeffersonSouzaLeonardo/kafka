package br.com.shopconsumidor.shopconsumidor.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_identifier", nullable = false)
    private String productIdentifier;

    @Column(name = "amount", nullable = false)
    private int amount;

}
