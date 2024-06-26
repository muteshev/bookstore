package dev.zenfluxpro.bookstore.catalog.web.controllers;

import dev.zenfluxpro.bookstore.catalog.BaseIntegrationTest;
import dev.zenfluxpro.bookstore.catalog.domain.ProductDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Sql("/test-data.sql")
public class ProductControllerTest extends BaseIntegrationTest {
    @Test
    public void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode() {
        ProductDTO product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDTO.class);

        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getName()).isEqualTo("The Hunger Games");
        assertThat(product.getDescription()).isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }

}