package com.xmaquina.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String hello() {
        return "Aqui vai ser a API que o FrontEnd vai chamar.";
    }
}
