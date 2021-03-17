package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeleteController {

  @DeleteMapping("/deleteMethod")
  public String deleteMethod(@RequestBody SearchParam searchParam) {
    return "Delete OK! " + searchParam.toString();
  }

}
