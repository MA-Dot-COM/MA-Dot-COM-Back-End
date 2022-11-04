//package com.sorhive.comprojectserver.config.web;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * <pre>
// * Class : WebMvcConfig
// * Comment: 클래스에 대한 간단 설명
// * History
// * ================================================================
// * DATE             AUTHOR           NOTE
// * ----------------------------------------------------------------
// * 2022-11-03       부시연           최초 생성
// * </pre>
// *
// * @author 부시연(최초 작성자)
// * @version 1(클래스 버전)
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/", "/home");
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/error/forbidden").setViewName("error/forbidden");
//        registry.addViewController("/error/notFound").setViewName("error/notFound");
//        registry.addViewController("/my/main").setViewName("my/myMain");
//        registry.addViewController("/admin/main").setViewName("admin/adminMain");
//        registry.addViewController("/loggedOut").setViewName("loggedOut");
//    }
//
//}
