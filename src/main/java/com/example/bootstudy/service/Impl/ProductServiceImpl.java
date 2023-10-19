package com.example.bootstudy.service.Impl;

import com.example.bootstudy.data.dto.ProductDto;
import com.example.bootstudy.data.entity.ProductEntity;
import com.example.bootstudy.data.handler.ProductDataHandler;
import com.example.bootstudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductDataHandler productDataHandler;

    @Autowired
    public ProductServiceImpl(ProductDataHandler productDataHandler) {
        this.productDataHandler = productDataHandler;
    }

    @Override
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName,
                productPrice, productStock);

        ProductDto productDto = new ProductDto(productEntity.getProductId(),
                productEntity.getProductName(), productEntity.getProductPrice(),
                productEntity.getProductStock());

        return productDto;
    }

    @Override
    public ProductDto getProduct(String productId) {
        ProductEntity productEntity = productDataHandler.getProductEntity(productId);

        ProductDto productDto = new ProductDto(productEntity.getProductId(),
                productEntity.getProductName(), productEntity.getProductPrice(),
                productEntity.getProductStock());

        return productDto;
    }
}
