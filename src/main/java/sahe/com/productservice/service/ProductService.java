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

    public List<ProductResponse> getAllProducts() {
        log.info("All products");
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        log.info("Showing product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return new ProductResponse(product);
    }

    public List<ProductResponse> searchProductsByName(String name) {
        log.info("Searching product by name: {}", name);
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByCategory(String category) {
        log.info("Showing products by category: {}", category);
        return productRepository.findByCategory(category)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByActiveStatus(Boolean active) {
        log.info("Showing products with active status: {}", active);
        return productRepository.findByActive(active)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByCategoryAndActive(String category, Boolean active) {
        log.info("Showing products by category: {} and assets: {}", category, active);
        return productRepository.findByCategoryAndActive(category, active)
                .stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating product: {}", request.getName());

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setActive(request.getActive());

        Product savedProduct = productRepository.save(product);
        log.info("Product successfully created with id: {}", savedProduct.getId());

        return new ProductResponse(savedProduct);
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        log.info("Updating product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
        product.setActive(request.getActive());
        Product updatedProduct = productRepository.save(product);
        log.info("Product successfully updated with ID: {}", updatedProduct.getId());

        return new ProductResponse(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        product.setActive(false);
        productRepository.save(product);

        log.info("Product successfully removed (deactivated) with id: {}", id);
    }

    @Transactional
    public void deleteProductPermanently(Long id) {
        log.info("Product permanently deleted with id: {}", id);

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }

        productRepository.deleteById(id);
        log.info("Product permanently removed with id: {}", id);
    }

    @Transactional
    public ProductResponse reactivateProduct(Long id) {
        log.info("Reactivating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        product.setActive(true);
        Product reactivatedProduct = productRepository.save(product);

        log.info("Product successfully reactivated with ID: {}", reactivatedProduct.getId());
        return new ProductResponse(reactivatedProduct);
    }
}
