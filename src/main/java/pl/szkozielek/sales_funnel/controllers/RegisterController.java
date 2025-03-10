package pl.szkozielek.sales_funnel.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import pl.szkozielek.sales_funnel.models.AppUser;
import pl.szkozielek.sales_funnel.models.RegisterDTO;
import pl.szkozielek.sales_funnel.repositories.AppUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;



@Controller
public class RegisterController {
    @Autowired 
    private AppUserRepository repo;

    @GetMapping("/register")
    public String show(Model model)
    {
        RegisterDTO registerData = new RegisterDTO();
        model.addAttribute(registerData);
        model.addAttribute("success", false);
        return "pages/register";
    }

    @PostMapping("/register")
    public String store(
        Model model, 
        @Valid @ModelAttribute RegisterDTO registerData,
        BindingResult result
    ) {
        if(!registerData.getPassword().equals(registerData.getConfirmPassword()))
        {
            result.addError(
                new FieldError("registerDTO", "confirmPassword", "Password and confirm password do not match.")
            );
        }

        AppUser appUser = repo.findByEmail(registerData.getEmail());
        if(appUser != null){
            result.addError(
                new FieldError("registerDTO", "email", "Email address is already used")
            );
        }

        if(result.hasErrors())
        {
            return "pages/register";
        }

        try{
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setEmail(registerData.getEmail());
            newUser.setPassword(bCryptEncoder.encode(registerData.getPassword()));
            newUser.setCreatedAt(new Date());
            repo.save(newUser);

            model.addAttribute("registerDTO", new RegisterDTO());
            model.addAttribute("success", true);
        }catch(Exception except){
            result.addError(
                new FieldError("registerDTO", "email", except.getMessage())
            );
        }
        
        return "pages/register";
    }
    
}
