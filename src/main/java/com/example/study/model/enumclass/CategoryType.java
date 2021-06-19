package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {

  PHONE(0,"스마트폰","아이폰, 갤럭시를 포함한 스마트폰"),
  COMPUTER(1,"컴퓨터","데트크톱, 노트북을 포함한 컴퓨터"),
  TV(2,"티비","모든 종류의 티비를 포함한 티비")
  ;

  private Integer id;
  private String title;
  private String description;

}
