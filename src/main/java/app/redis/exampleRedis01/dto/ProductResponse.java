package app.redis.exampleRedis01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {


    private List<ProductDTO> products;

    // To see the response time
    private String messageTime;
}
