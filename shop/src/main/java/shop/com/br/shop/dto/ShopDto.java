package shop.com.br.shop.dto;

import lombok.Getter;
import lombok.Setter;
import shop.com.br.shop.model.ShopItem;
import shop.com.br.shop.model.StatusEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ShopDto {

    private Long id;
    private String status;
    private LocalDate dataCompra;
    private List<ShopItemDto> items;

}
