package com.example.study.controller.test;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PatchController {

  @RequestMapping("/patchMethod")
  public SearchParam patchMethod(@RequestBody SearchParam searchParam) {
    return searchParam;
  }

}
