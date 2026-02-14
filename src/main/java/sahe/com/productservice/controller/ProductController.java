package sahe.com.productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sahe.com.productservice.dto.ProductRequest;
import sahe.com.productservice.dto.ProductResponse;
import sahe.com.productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    // OBTENER TODOS LOS PRODUCTOS
    // GET http://localhost:8083/products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("GET /products - Get all products");
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // OBTENER PRODUCTO POR ID
    // GET http://localhost:8083/products/1
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("GET /products/{} - Get product by id", id);
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // BUSCAR PRODUCTOS POR NOMBRE
    // GET http://localhost:8083/products/search?name=laptop
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String name) {
        log.info("GET /products/search?name={} - Search products", name);
        List<ProductResponse> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }

    // OBTENER PRODUCTOS POR CATEGORÍA
    // GET http://localhost:8083/products/category/Electronics
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable String category) {
        log.info("GET /products/category/{} - Get products by category", category);
        List<ProductResponse> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // OBTENER PRODUCTOS ACTIVOS/INACTIVOS
    // GET http://localhost:8083/products/active/true
    @GetMapping("/active/{active}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public ResponseEntity<List<ProductResponse>> getProductsByActiveStatus(@PathVariable Boolean active) {
        log.info("GET /products/active/{} - Get products by active status", active);
        List<ProductResponse> products = productService.getProductsByActiveStatus(active);
        return ResponseEntity.ok(products);
    }

    // OBTENER PRODUCTOS POR CATEGORÍA Y ESTADO
    // GET http://localhost:8083/products/category/Electronics/active/true
    @GetMapping("/category/{category}/active/{active}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryAndActive(
            @PathVariable String category,
            @PathVariable Boolean active) {
        log.info("GET /products/category/{}/active/{}", category, active);
        List<ProductResponse> products = productService.getProductsByCategoryAndActive(category, active);
        return ResponseEntity.ok(products);
    }

    // CREAR PRODUCTO
    // POST http://localhost:8083/products
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        log.info("POST /products - Create product: {}", request.getName());
        ProductResponse createdProduct = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // ACTUALIZAR PRODUCTO
    // PUT http://localhost:8083/products/1
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        log.info("PUT /products/{} - Update product", id);
        ProductResponse updatedProduct = productService.updateProduct(id, request);
        return ResponseEntity.ok(updatedProduct);
    }

    // ELIMINAR PRODUCTO (Soft Delete)
    // DELETE http://localhost:8083/products/1
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /products/{} - Delete product (soft delete)", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ELIMINAR PRODUCTO PERMANENTEMENTE
    // DELETE http://localhost:8083/products/1/permanent
    @DeleteMapping("/{id}/permanent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProductPermanently(@PathVariable Long id) {
        log.info("DELETE /products/{}/permanent - Delete product permanently", id);
        productService.deleteProductPermanently(id);
        return ResponseEntity.noContent().build();
    }

    // REACTIVAR PRODUCTO
    // PATCH http://localhost:8083/products/1/reactivate
    @PatchMapping("/{id}/reactivate")
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    public ResponseEntity<ProductResponse> reactivateProduct(@PathVariable Long id) {
        log.info("PATCH /products/{}/reactivate - Reactivate product", id);
        ProductResponse reactivatedProduct = productService.reactivateProduct(id);
        return ResponseEntity.ok(reactivatedProduct);
    }
}
