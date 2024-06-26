package dev.zenfluxpro.bookstore.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "products")
@Slf4j
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq")
    private Long id;

    @NotEmpty(message = "Product code is required")
    @Column(unique = true, nullable = false)
    private String code;

    @NotEmpty(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    private String description;
    private String imageUrl;

    @NotNull(message = "Product price is required") @DecimalMin("0.1")
    @Column(nullable = false)
    private BigDecimal price;
}
