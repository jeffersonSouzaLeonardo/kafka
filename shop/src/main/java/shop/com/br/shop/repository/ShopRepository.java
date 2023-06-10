package shop.com.br.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.com.br.shop.model.Shop;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
}
