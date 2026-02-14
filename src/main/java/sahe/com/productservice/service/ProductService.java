package sahe.com.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sahe.com.productservice.dto.ProductRequest;
import sahe.com.productservice.dto.ProductResponse;
import sahe.com.productservice.model.Product;
import sahe.com.productservice.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Obtener Productos
    public List<ProductResponse> getAllProducts() {
        log.info("Todos los productos");
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    // Producto x Id
    public ProductResponse getProductById(Long id) {
        log.info("Mostrando producto con id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return new ProductResponse(product);
    }

    // Producto x nombre
    public List<ProductResponse> searchProductsByName(String name) {
        log.info("Buscando producto por nombre: {}", name);
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    // Producto x categoria
    public List<ProductResponse> getProductsByCategory(String category) {
        log.info("Mostrando productos por categoria: {}", category);
        return productRepository.findByCategory(category)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    // Productos x ACTIVOS/INACTIVOS
    public List<ProductResponse> getProductsByActiveStatus(Boolean active) {
        log.info("Mostrando productos con status activo: {}", active);
        return productRepository.findByActive(active)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    // Producto x CATEGORÍA Y ESTADO
    public List<ProductResponse> getProductsByCategoryAndActive(String category, Boolean active) {
        log.info("Mostrando productos por categoria: {} y activos: {}", category, active);
        return productRepository.findByCategoryAndActive(category, active)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    // Crear Producto
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creando producto: {}", request.getName());

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setActive(request.getActive());

        Product savedProduct = productRepository.save(product);
        log.info("Producto creado satisfactoriamente con id: {}", savedProduct.getId());

        return new ProductResponse(savedProduct);
    }

    // Actualizar Producto
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        log.info("Actualizando producto con id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setActive(request.getActive());

        Product updatedProduct = productRepository.save(product);
        log.info("Producto actualizado correctamente con id: {}", updatedProduct.getId());

        return new ProductResponse(updatedProduct);
    }

    // Eliminar Producto(Soft Delete)
    @Transactional
    public void deleteProduct(Long id) {
        log.info("Borrando producto con id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setActive(false);
        productRepository.save(product);

        log.info("Producto eliminado (deactivated) correctamente con id: {}", id);
    }

    // Eliminar Producto PERMANENTEMENTE
    @Transactional
    public void deleteProductPermanently(Long id) {
        log.info("Producto borrado de forma permanente con id: {}", id);

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }

        productRepository.deleteById(id);
        log.info("Producto eliminado de forma permanente con id: {}", id);
    }

    // Reactivar Producto
    @Transactional
    public ProductResponse reactivateProduct(Long id) {
        log.info("Reactivando producto con id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setActive(true);
        Product reactivatedProduct = productRepository.save(product);

        log.info("Producto reactivado correctamente con id: {}", reactivatedProduct.getId());
        return new ProductResponse(reactivatedProduct);
    }
}
