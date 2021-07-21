package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// lombok을 이용한 constructor, getter, setter 이용하기
@Data
@AllArgsConstructor
public class SearchParam {

  private String account;

  private String email;

  private int page;

}
