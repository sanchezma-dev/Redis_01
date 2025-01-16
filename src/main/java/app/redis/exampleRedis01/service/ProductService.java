package app.redis.exampleRedis01.service;

import app.redis.exampleRedis01.dto.ProductDTO;
import app.redis.exampleRedis01.models.ProductMO;
import app.redis.exampleRedis01.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final RedisTemplate<String, Object> redisTemplate;
    private CacheManager cacheManager;


    @Cacheable(value = "products")
    public List<ProductDTO> getProducts() {
        // Service interruption for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        var productMOs = repo.findAll();

        return convertProductsDTO(productMOs);
    }

    @Cacheable(value = "product", key = "#idProduct")
    public ProductDTO getProductId(Long idProduct) {
        // Service interruption for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return repo.findById(idProduct)
                .map(p -> ProductDTO.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .categoryProduct(p.getCategoryProduct())
                        .price(p.getPrice())
                        .build())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + idProduct));
    }


    // Clear cache in memory redis
    public void clearProductCache(String value) {
        Cache cache = cacheManager.getCache(value);
        if (cache != null) {
            cache.clear();
        }
    }


    private List<ProductDTO> convertProductsDTO(List<ProductMO> productMOS) {
        return productMOS.stream()
                .map(p -> ProductDTO.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .categoryProduct(p.getCategoryProduct())
                        .price(p.getPrice())
                        .build())
                .collect(toList());
    }


}
