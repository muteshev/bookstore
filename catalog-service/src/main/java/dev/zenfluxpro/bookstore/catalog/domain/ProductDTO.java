package dev.zenfluxpro.bookstore.catalog.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String code;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
