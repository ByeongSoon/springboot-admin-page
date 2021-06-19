package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PartnerStatus {

  MATCHING(0,"매칭","카테고리와 매칭"),
  UNMATCHING(1,"비매칭","카테고리와 매칭 전")
  ;

  private Integer id;
  private String title;
  private String description;

}
