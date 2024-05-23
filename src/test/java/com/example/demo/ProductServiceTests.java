package com.example.demo;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TestProduct");
        productDTO.setPrice(10.0);

        Product product = new Product();
        product.setId(1L);
        product.setName("TestProduct");
        product.setPrice(10.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertNotNull(createdProduct);
        assertEquals("TestProduct", createdProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("TestProduct");
        product.setPrice(10.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<ProductDTO> productDTO = productService.getProductById(1L);

        assertTrue(productDTO.isPresent());
        assertEquals("TestProduct", productDTO.get().getName());
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("TestProduct");
        productDTO.setPrice(10.0);

        Product product = new Product();
        product.setId(1L);
        product.setName("TestProduct");
        product.setPrice(10.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO updatedProduct = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProduct);
        assertEquals("TestProduct", updatedProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
