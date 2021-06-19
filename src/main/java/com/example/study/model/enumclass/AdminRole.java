package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdminRole {

  MASTER(0,"관리자","모든 권한을 가진 관리자"),
  DEVELOPER(1,"개발자","개발에 필요한 권한을 가진 개발자"),
  GUEST(2,"게스트","테스트를 위한 권한을 가진 게스트")
  ;

  private Integer id;
  private String title;
  private String description;

}
