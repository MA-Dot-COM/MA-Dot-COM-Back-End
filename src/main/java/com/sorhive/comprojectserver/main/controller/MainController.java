package com.sorhive.comprojectserver.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * Class : MainController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-10-18       부시연           최초 생성
 * </pre>
 *
 * @author 부시연(최초 작성자)
 * @version 1(클래스 버전)
 */
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping(value = {"", "/main"})
    public String main() {

        return "main/main";
    }

    @PostMapping(value="")
    public String redirectMain() {

        return "redirect:/";
    }
}
