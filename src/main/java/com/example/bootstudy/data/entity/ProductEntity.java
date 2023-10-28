package com.example.bootstudy.data.entity;

import com.example.bootstudy.data.dto.ProductDto;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity extends BaseEntity {
//BaseEntity에 있는 컬럼 추가됨(@MappedSuperclass)
    @Id
    String productId;

    String productName;

    Integer productPrice;

    Integer productStock;
    

  /*
  @Column
  String sellerId;

  @Column
  String sellerPhoneNumber;
   */

    public ProductDto toDto(){
        return ProductDto.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
