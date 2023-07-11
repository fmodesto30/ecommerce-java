//package com.domain.dao.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.management.domain.dao.impl.ProductDAOImpl;
//import com.management.domain.model.ProductEntity;
//
//public class ProductDAOImplTest {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private ProductDAOImpl productDAO;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void findByAsin_ValidAsin_ReturnsProductEntity() throws Exception {
//        // Arrange
//        String asin = "B07G3JY491";
//        ProductEntity expectedProduct = new ProductEntity();
//        expectedProduct.setAsin(asin);
//        expectedProduct.setName("Product 1");
//        expectedProduct.setTime(new Date());
//
//        String sql = "SELECT * FROM TB_PRODUCT WHERE asin = ?";
//        Object[] args = {asin};
//
//        Mockito.when(jdbcTemplate.queryForObject(Mockito.eq(sql), Mockito.eq(args),
//                Mockito.any(BeanPropertyRowMapper.class))).thenReturn(expectedProduct);
//
//        // Act
//        Optional<ProductEntity> result = productDAO.findByAsin(asin);
//
//        // Assert
//        Assertions.assertThat(result.isPresent());
//        ProductEntity actualProduct = result.get();
//        Assertions.assertThat(actualProduct.getAsin()).isEqualTo(expectedProduct.getAsin());
//        Assertions.assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());
//        Assertions.assertThat(actualProduct.getTime()).isEqualTo(expectedProduct.getTime());
//    }
//
//    @Test
//    public void findByAsin_InvalidAsin_ReturnsEmptyOptional() throws Exception {
//        // Arrange
//        String asin = "B07G3JY499";
//
//        String sql = "SELECT * FROM TB_PRODUCT WHERE asin = ?";
//        Object[] args = {asin};
//
//        Mockito.when(jdbcTemplate.queryForObject(Mockito.eq(sql), Mockito.eq(args),
//                Mockito.any(BeanPropertyRowMapper.class))).thenThrow(EmptyResultDataAccessException.class);
//
//        // Act
//        Optional<ProductEntity> result = productDAO.findByAsin(asin);
//
//        // Assert
//        Assertions.assertThat(result.isEmpty());
//    }
//
//    @Test
//    public void saveProduct_ValidProduct_ReturnsSavedProductEntity() throws Exception {
//        // Arrange
//        ProductEntity productToSave = new ProductEntity();
//        productToSave.setAsin("B07G3JY491");
//        productToSave.setName("Product 1");
//        productToSave.setTime(new Date());
//
//        String sql = "INSERT INTO TB_PRODUCT (ASIN, NAME, TIME) VALUES (?, ?, ?)";
//        Object[] args = {productToSave.getAsin(), productToSave.getName(), productToSave.getTime()};
//
//        Mockito.when(jdbcTemplate.update(Mockito.eq(sql), Mockito.eq(args))).thenReturn(1);
//
//        // Act
//        Optional<ProductEntity> result = productDAO.saveProduct(productToSave);
//
//        // Assert
//        Assertions.assertThat(result.isPresent());
//        ProductEntity savedProduct = result.get();
//        Assertions.assertThat(savedProduct.getAsin()).isEqualTo(productToSave.getAsin());
//        Assertions.assertThat(savedProduct.getName()).isEqualTo(productToSave.getName());
//        Assertions.assertThat(savedProduct.getTime()).isEqualTo(productToSave.getTime());
//    }
//
//    @Test
//    public void listAll_ValidData_ReturnsListOfProducts() throws Exception {
//        // Arrange
//        List<ProductEntity> expectedProducts = new ArrayList<>();
//        // Add some expected products to the list
//
//        String sql = "SELECT * FROM TB_PRODUCT";
//
//        Mockito.when(jdbcTemplate.query(Mockito.eq(sql),
//                Mockito.any(BeanPropertyRowMapper.class))).thenReturn(expectedProducts);
//
//        // Act
//        Optional<List<ProductEntity>> result = productDAO.listAll();
//
//        // Assert
////        Assertions.assertThat(result).hasValue(expectedProducts.size());
//        // Add additional assertions as needed
//    }
//}
