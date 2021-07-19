package com.example.study.controller.page;

import com.example.study.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path = {""})
    public ModelAndView index() {
        return new ModelAndView("/pages/main")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }
    // 고객관리 상세페이지에서 다시 Admin logo를 클릭하면 /pages/main 경로로 변경되므로 추가
    @RequestMapping(path = {"/main"})
    public ModelAndView mainIndex() {
        return new ModelAndView("/pages/main")
            .addObject("menuList", adminMenuService.getAdminMenu())
            .addObject("code", "main")
            ;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user")
                ;
    }
}
