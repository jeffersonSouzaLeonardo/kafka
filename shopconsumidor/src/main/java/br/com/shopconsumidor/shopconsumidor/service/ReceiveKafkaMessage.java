package br.com.shopconsumidor.shopconsumidor.service;


import br.com.shopconsumidor.shopconsumidor.dto.ShopDto;
import br.com.shopconsumidor.shopconsumidor.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
    private final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    @Autowired
    private ProductRepository productRepository;

    private KafkaTemplate<String, ShopDto> kafkaTemplate;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(ShopDto shopDto){
        try{
            log.info("Compra recebida no tópico: {}", shopDto.getId());
            shopSucess(shopDto);
        }catch (Exception e){
            log.error("Erro no processamento da compra {}", shopDto.getId());
        }
    }

    private void shopSucess(@NotNull ShopDto shopDto){
        shopDto.setStatus("SUCCESS");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDto);
    }
}
