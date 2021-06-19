package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderDetailStatus {

  PRE_DELIVERY(0,"배송준비중","배송을 위한 물품 준비중"),
  ING_DELIVERY(1,"배송중","물품을 고객에게 배송중"),
  ARRIVE(2,"도착","물품을 고객에게 전달완료")
  ;

  private Integer id;
  private String title;
  private String description;

}
