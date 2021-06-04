package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

  // api 통신시간
//  @JsonProperty("transaction_time")
  private String transactionTime;

  // api 응답 코드
  private String resultCode;

  // api 부가 설명
  private String description;

  // body 부분 재네릭을 사용
  private T data;

}
