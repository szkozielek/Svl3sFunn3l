package pl.szkozielek.sales_funnel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @GetMapping("/login")
    public String index(){
        return "pages/auth/login";
    }
}
