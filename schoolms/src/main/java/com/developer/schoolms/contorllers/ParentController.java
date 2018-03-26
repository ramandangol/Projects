package com.developer.schoolms.contorllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParentController {

    @GetMapping("/")
    public String indexPage(){
        return "parents/index";
    }
}
