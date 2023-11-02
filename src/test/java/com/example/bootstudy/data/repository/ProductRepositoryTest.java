package com.example.bootstudy.data.repository;

import com.example.bootstudy.data.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;


import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void GenerateData() {
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 9000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1500, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 5000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));

    }

    private ProductEntity getProduct(String id, int nameNumber, int price, int stock) {
        return new ProductEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundEntities = productRepository.findByProductName("상품4");

        for (ProductEntity productEntity : foundEntities) {
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> queryEntities = productRepository.queryByProductName("상품4");

        for (ProductEntity productEntity : queryEntities) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    void exitTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.existsByProductName("상품4"));
        System.out.println(productRepository.existsByProductName("상품20"));
    }

    @Test
    void countTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.countByProductName("상품4"));
    }

    @Test
    @Transactional
        //이걸 붙여야 동작을 한다.
    void deleteTest() {
        System.out.println("bofore : " + productRepository.count());

        productRepository.deleteByProductId("1");
        productRepository.deleteByProductId("9");

        System.out.println("After : " + productRepository.count());
    }

    @Test
    void topTest() {
        productRepository.save(getProduct("100", 123, 1000, 2000));
        productRepository.save(getProduct("101", 123, 1000, 2000));
        productRepository.save(getProduct("102", 123, 1000, 2000));
        productRepository.save(getProduct("103", 123, 1000, 2000));
        productRepository.save(getProduct("104", 123, 1000, 2000));
        productRepository.save(getProduct("105", 123, 1000, 2000));
        productRepository.save(getProduct("106", 123, 1000, 2000));

        //둘다 id로 정렬된 후에 조회된다

        List<ProductEntity> foundEntities = productRepository.findFirst5ByProductName("상품123");
        for (ProductEntity productEntity : foundEntities) {
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> foundEntities2 = productRepository.findTop3ByProductName("상품123");
        for (ProductEntity productEntity : foundEntities2) {
            System.out.println(productEntity.toString());
        }
    }

    /* ↓↓ 조건자 키워드 테스트 ↓↓ */

    @Test
    void isEqualsTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.findByProductIdIs("1"));
        System.out.println(productRepository.findByProductIdEquals("1"));
    }

    @Test
    void notTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundEntities = productRepository.findByProductIdNot("1");
        for (ProductEntity productEntity : foundEntities) {
            System.out.println(productEntity);
        }
        System.out.println(productRepository.findByProductIdIsNot("1"));
    }

    @Test
    void nullTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.findByProductStockIsNull());
        System.out.println(productRepository.findByProductStockIsNotNull());
    }

    @Test
    void andTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.findTopByProductIdAndProductName("1", "상품1"));
    }

    @Test
    void greaterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.findByProductPriceGreaterThan(5000));
    }

    @Test
    void containTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        System.out.println(productRepository.findByProductNameContaining("1"));
    }


    /*정렬과 페이징*/
    @Test
    void orderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductStockAsc("상품");
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductStockDesc("상품");
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

    }

    @Test
    void multiOrderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductPriceAscProductStockDesc("상품");
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    void orderByWithParameterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContaining(
                "상품", Sort.by(Order.asc("productPrice")));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductNameContaining(
                "상품", Sort.by(Order.asc("productPrice"), Order.asc("productStock")));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    void pagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceGreaterThan(
                200, PageRequest.of(0, 2));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductPriceGreaterThan(
                200, PageRequest.of(4, 2));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }


    /*@Query 사용하기 Test*/

    @Test
    public void queryTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceBasis();
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }

    @Test
    public void nativeQueryTest(){
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====VV Test Data VV====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====^^ Test Data ^^====");

        List<ProductEntity> foundProducts = productRepository.findByProductPriceBasisNativeQuery();
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }
    }


}