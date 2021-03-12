package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

  // localhost:8080/api/getMethod
  @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
  public String getRequest() {
    return "Hi getMethod";
  }

  // localhost:8080/api/getParameter?id=1234&password=abcd
  @GetMapping("/getParameter")
  public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) { // @RequestParam에 name으로 파라미터 이름을 지정할 수 있음
    String password = "bbbb";

    System.out.println("id : " + id);
    System.out.println("password : " + pwd);

    return id+pwd;
  }

  // localhost:8080/api/multiParameter?account=abcd&email=study@gmail.com&page=10
  @GetMapping("/getMultiParameter")
  public SearchParam getMultiParameter(SearchParam searchParam) { // @RequestParam으로 받지않고 객체 자체로 받아도 된다.
    System.out.println(searchParam.getAccount());
    System.out.println(searchParam.getEmail());
    System.out.println(searchParam.getPage());

    // { "account" : "", "email" : "", "page" : 0 } 따로 설정하지 않아도 jackson 라이브러리로 인해서 JSON 형식으로 반환
    return searchParam;
  }

}
