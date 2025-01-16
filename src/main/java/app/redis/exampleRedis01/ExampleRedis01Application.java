package app.redis.exampleRedis01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Configuración automática de caché en Spring, así evitas hacer una clase config personalizada
//@EnableCaching
public class ExampleRedis01Application {

    public static void main(String[] args) {
        SpringApplication.run(ExampleRedis01Application.class, args);
    }

}
