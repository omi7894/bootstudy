package com.example.bootstudy.data.entity;

import com.example.bootstudy.data.dto.ProductDto;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {

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
