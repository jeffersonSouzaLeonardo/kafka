package shop.com.br.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShopDto {

    private Long id;
    private String status;
    private LocalDate dataCompra;
    private List<ShopItemDto> items;

}
