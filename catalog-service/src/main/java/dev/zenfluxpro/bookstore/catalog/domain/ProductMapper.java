package dev.zenfluxpro.bookstore.catalog.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProductMapper {
    @NonNull private final ModelMapper modelMapper;

    ProductDTO getProductDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    ProductEntity getProductEntity(ProductDTO productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }

    static ProductDTO toProductDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .code(productEntity.getCode())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .imageUrl(productEntity.getImageUrl())
                .price(productEntity.getPrice())
                .build();
    }

    static ProductEntity toProductEntity(ProductDTO productDto) {
        return ProductEntity.builder()
                .code(productDto.getCode())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .price(productDto.getPrice())
                .build();
    }
}
