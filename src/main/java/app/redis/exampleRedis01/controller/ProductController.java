package app.redis.exampleRedis01.controller;

import app.redis.exampleRedis01.dto.ProductResponse;
import app.redis.exampleRedis01.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app/redis")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<ProductResponse> products() {

        long startTime = System.currentTimeMillis();

        var products = service.getProducts();

        long finalTime = System.currentTimeMillis() - startTime;
        log.info("The service response time has been: {} ms", (finalTime));
        var message = "Response time in ms: " + finalTime;

        var response = ProductResponse.builder()
                .products(products)
                .messageTime(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> productById(@PathVariable Long productId) {

        long startTime = System.currentTimeMillis();

        var products = service.getProductId(productId);

        long finalTime = System.currentTimeMillis() - startTime;
        log.info("EndPoint productById: The service response time has been: {} ms", (finalTime));
        var message = "Response time in ms: " + finalTime;

        var response = ProductResponse.builder()
                .products(List.of(products))
                .messageTime(message)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/clearCacheProducts/{valueCache}")
    public ResponseEntity<Void> clearCache(@PathVariable String valueCache) {
        service.clearProductCache(valueCache);
        return ResponseEntity.noContent().build();
    }

}
