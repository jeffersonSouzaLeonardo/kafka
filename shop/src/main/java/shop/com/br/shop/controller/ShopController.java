package shop.com.br.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.com.br.shop.dto.ShopDto;
import shop.com.br.shop.service.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopDto> shop(@RequestBody ShopDto shopDto){
        return ResponseEntity.ok(shopService.insert(shopDto)) ;
    }


}
