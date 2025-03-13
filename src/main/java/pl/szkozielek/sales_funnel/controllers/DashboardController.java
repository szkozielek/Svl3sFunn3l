package pl.szkozielek.sales_funnel.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/")
    public String index(Model model, Authentication authentication)
    {
        model.addAttribute("auth_username", authentication.getName());

        return "pages/dashboard";
    }
}
