package pl.szkozielek.sales_funnel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/")
    public String index(Model model)
    {
        return "pages/dashboard";
    }
}
