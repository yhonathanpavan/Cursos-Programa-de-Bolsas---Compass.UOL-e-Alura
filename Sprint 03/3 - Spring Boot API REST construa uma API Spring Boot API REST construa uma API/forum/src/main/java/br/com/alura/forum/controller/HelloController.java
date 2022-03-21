package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/") //Qual a url
    @ResponseBody //Essa notação permite que o spring devolva a mensagem direto pro navegador
    public String hello(){
        return "Hello World!";
    }
}
