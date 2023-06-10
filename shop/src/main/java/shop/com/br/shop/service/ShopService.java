package shop.com.br.shop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.com.br.shop.dto.ShopDto;
import shop.com.br.shop.dto.ShopItemDto;
import shop.com.br.shop.model.Shop;
import shop.com.br.shop.model.ShopItem;
import shop.com.br.shop.model.StatusEnum;
import shop.com.br.shop.repository.ShopItemRepository;
import shop.com.br.shop.repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private KafkaClient kafkaClient;

    public ShopDto insert(ShopDto shopDto){
        Shop shop = new Shop();
        shop = modelMapper.map(shopDto, Shop.class);
        shop.setDataCompra(LocalDate.now());
        shop.setStatus(StatusEnum.SUCCESS.toString());
        shop = shopRepository.saveAndFlush(shop);

        List<ShopItemDto> items = shopDto.getItems();
        for (ShopItemDto dto:items){
            dto.setShopId(shop.getId());
        }

        List<ShopItem> itemsNovos = items
                .stream()
                .map(shopItem -> modelMapper.map(shopItem, ShopItem.class))
                .collect(Collectors.toList());

        shopItemRepository.saveAllAndFlush(itemsNovos);

        ShopDto dtoReturn = modelMapper.map(shop, ShopDto.class);

        kafkaClient.sendMessage(dtoReturn);

        return  modelMapper.map(shop, ShopDto.class);
    }

}
