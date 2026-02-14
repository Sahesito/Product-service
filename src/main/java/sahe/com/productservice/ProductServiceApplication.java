package sahe.com.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        System.out.println("""
                PRODUCT SERVICE - RUNNING
                Port: 8083
                GET /products - Get all products
                GET /products/{id} - Get product by id
                GET /products/search - Search products
                POST /products - Create product
                PUT /products/{id} - Update product
                DELETE /products/{id} - Delete product
                """);
    }

}
