package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "orderDetailList")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer price;

  private String content;

  // 1 : N

  // LAZY = 지연로딩 , EAGER = 즉시로딩(데이터 타입이 많은 테이블에 대해서 조건을 걸어줄경우 모든 속성에 대해 조인을 하기때문에 성능저하 우려)

  // LAZY => SELECT * FROM item where id = ?
  // EAGER => SELECT * FROM item .....-> 1:1 일때 사용
  // item_id = order_detail.item_id
  // user_id = order_detail.user_id
  // where item.id = ?
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
  private List<OrderDetail> orderDetailList;

}
