package dev.zenfluxpro.bookstore.webapp.clients.catalog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface CatalogServiceClient {

    @GetExchange("/catalog/api/products")
    dev.zenfluxpro.bookstore.webapp.clients.catalog.PagedResult<dev.zenfluxpro.bookstore.webapp.clients.catalog.Product>
            getProducts(@RequestParam int page);

    @GetExchange("/catalog/api/products/{code}")
    ResponseEntity<dev.zenfluxpro.bookstore.webapp.clients.catalog.Product> getProductByCode(@PathVariable String code);
}
