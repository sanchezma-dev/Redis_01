package app.redis.exampleRedis01.repository;

import app.redis.exampleRedis01.models.ProductMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductMO, Long> {

}
