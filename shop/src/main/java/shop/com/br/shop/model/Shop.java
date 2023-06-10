package shop.com.br.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "shop")
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="STATUSENUM", nullable = false)
    private String status;

    @Column(name="DATACOMPRA", nullable = false)
    private LocalDate dataCompra;

}
