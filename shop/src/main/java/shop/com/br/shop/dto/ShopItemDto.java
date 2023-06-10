package shop.com.br.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ShopItemDto {
    private Long id;
    private Long productId;
    private BigDecimal price;
    private int amount;
    private Long shopId;

}
