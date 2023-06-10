package shop.com.br.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shop.com.br.shop.dto.ShopDto;

@Service
@RequiredArgsConstructor
public class KafkaClient {

    private final KafkaTemplate<String, ShopDto> kafkaTemplate;
    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    public void sendMessage(ShopDto msg){
        kafkaTemplate.send(SHOP_TOPIC_NAME, msg);
    }

}
