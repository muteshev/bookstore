package dev.zenfluxpro.bookstore.catalog.domain;

import dev.zenfluxpro.bookstore.catalog.ConfigProperties;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ConfigProperties configProperties;

    public ProductPageResult<ProductDTO> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, configProperties.pageSize(), sort);
        Page<ProductDTO> productPage =
                productRepository.findAll(pageable).map(productEntity -> productMapper.getProductDTO(productEntity));
        //            productRepository.findAll(pageable).map(ProductMapper::toProductDTO);
        log.info("total pages: {}", productPage.getTotalPages());
        log.info("page number: {}/{}", productPage.getNumber() + 1, productPage.getTotalPages());
        log.info("records on the page: {} (max={})", productPage.getNumberOfElements(), productPage.getSize());
        return new ProductPageResult<ProductDTO>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getNumber() + 1,
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious());
    }

    public Optional<ProductDTO> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProductDTO);
    }
}
