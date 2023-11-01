package com.example.bootstudy.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.bootstudy.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    /*쿼리 메소드의 주제 키워드*/
    //조회
    List<ProductEntity> findByProductName(String name);
    List<ProductEntity> queryByProductName(String name);

    //존재 유무
    boolean existsByProductName(String name);

    //쿼리 결과 개수
    long countByProductName(String name);

    //삭제
    void deleteByProductId(String Id);
    long removeByProductId(String Id);

    //값 개수 제한
    List<ProductEntity> findFirst5ByProductName(String name);
    List<ProductEntity> findTop3ByProductName(String name);

    /*쿼리 메소드의 조건자 키워드*/

    // Is, Equals (생략 가능)
    // Logical Keyword : IS , Keyword Expressions : Is, Equals, (or no keyword)
    // findByProductId와 동일
    ProductEntity findByProductIdIs(String id);
    ProductEntity findByProductIdEquals(String id);

    // (Is)Not
    List<ProductEntity> findByProductIdNot(String id);
    List<ProductEntity> findByProductIdIsNot(String id);

    //(Is)Null, (Is)NotNull
    List<ProductEntity> findByProductStockIsNull();
    List<ProductEntity> findByProductStockIsNotNull();

    // And, Or
    List<ProductEntity> findTopByProductIdAndProductName(String id, String name);

    //(Is)GreaterThan, (Is)LessThan, (Is)Between
    List<ProductEntity> findByProductPriceGreaterThan(Integer price);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<ProductEntity> findByProductNameContaining(String name);


    /*정렬과 페이징*/

    //Asc, Desc
    List<ProductEntity> findByProductNameContainingOrderByProductStockAsc(String name);
    List<ProductEntity> findByProductNameContainingOrderByProductStockDesc(String name);

    //여러 정렬 기준 사용
    List<ProductEntity> findByProductNameContainingOrderByProductPriceAscProductStockDesc(String name);

    //매개변수를 확용한 정렬
    List<ProductEntity> findByProductNameContaining(String name, Sort sort);

    //페이징 처리하기
    List<ProductEntity> findByProductPriceGreaterThan(Integer price, Pageable pageable);


}
