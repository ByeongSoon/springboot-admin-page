package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PutController {

  @PutMapping("/putMethod")
  public SearchParam putMethod(@RequestBody SearchParam searchParam) {
    return searchParam;
  }

}
