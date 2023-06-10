package shop.com.br.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.com.br.shop.model.ShopItem;

import java.util.UUID;

public interface ShopItemRepository extends JpaRepository<ShopItem, UUID> {
}
