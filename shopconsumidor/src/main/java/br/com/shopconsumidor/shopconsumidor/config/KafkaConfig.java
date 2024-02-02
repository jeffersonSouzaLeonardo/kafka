package br.com.shopconsumidor.shopconsumidor.config;

import br.com.shopconsumidor.shopconsumidor.dto.ShopDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value = "${kafka.bootstrapAddress:localhost:9092}")
    private String bootstrapAddress;

    public ProducerFactory<String, ShopDto> producerFactory(){

        JsonDeserializer<Container> deserializer = new JsonDeserializer<>(Container.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>();
        props.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress );
        props.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class );
        props.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, deserializer );
        props.put( ProducerConfig.CLIENT_ID_CONFIG, "shop-api" );

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_one");
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);


        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);


        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, ShopDto> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    public ConsumerFactory<String, ShopDto> consumerFactory(){
        JsonDeserializer<ShopDto> deserializer = new JsonDeserializer<>(ShopDto.class);
        Map<String, Object> props = new HashMap<>();
        props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress );
        return new DefaultKafkaConsumerFactory<>( props, new StringDeserializer(), deserializer );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShopDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String ,ShopDto> factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
