package br.com.shopconsumidor.shopconsumidor.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShopItemDto {
    private Long id;
    private Long productId;
    private BigDecimal price;
    private int amount;
    private Long shopId;

}
