package dev.zenfluxpro.bookstore.catalog.web.controllers;

import dev.zenfluxpro.bookstore.catalog.domain.ProductDTO;
import dev.zenfluxpro.bookstore.catalog.domain.ProductNotFoundException;
import dev.zenfluxpro.bookstore.catalog.domain.ProductPageResult;
import dev.zenfluxpro.bookstore.catalog.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    ProductPageResult<ProductDTO> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/products/{code}")
    ResponseEntity<ProductDTO> getProductByCode(@PathVariable String code) {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
