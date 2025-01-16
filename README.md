
# Ejemplo uso de Redis con spring boot

Sencillo ejemplo de una aplicación spring boot con redis.


**Prerrequisitos :** 
- Tener instalado Docker desktop
- Java 21
- A través de ```docker-compose up``` levantar los servicios de redis y postgresql.  
- Lanzar los scripts src/main/resources/sql_script (1º create table 😉)


**Explicación conceptual:**
Las dependencias necesarias puede ser a través de spring-boot-starter-data-redis. Es una dependencia más genérica
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
O más presonalizada
```
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-redis</artifactId>
</dependency>

<dependency>
  <groupId>redis.clients</groupId>
  <artifactId>jedis</artifactId>
  <version>4.0.1</version>
</dependency> 
```

Además se puede habilitar de forma genérica la caché, esto hace que spring cargue por defecto la configuración de caché
```
@SpringBootApplication
@EnableCaching
public class ExampleRedis01Application {
 // ...
 ```
O a través de una clase config, personalizada. Como en esta aplicación
```
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
// .. 
```

Para hacer las pruebas, he creado un endpoint para el borrado de la caché cuyo parámetro se pasa por argumento.
Así nos aseguramos que se limpia la caché y redis no se activa en la 1ª llamada.

