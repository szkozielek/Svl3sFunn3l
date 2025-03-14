package pl.szkozielek.sales_funnel.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import pl.szkozielek.sales_funnel.dto.CompanyStageDTO;
import pl.szkozielek.sales_funnel.dto.FilterDTO;
import pl.szkozielek.sales_funnel.dto.StageDTO;
import pl.szkozielek.sales_funnel.models.Stage;
import pl.szkozielek.sales_funnel.repositories.CompanyRepository;
import pl.szkozielek.sales_funnel.repositories.StageRepository;

@Controller
public class StageController {

    @Autowired 
    private StageRepository repo;

    @Autowired
    private CompanyRepository companyRepo;

    @GetMapping("/stages")
    public String index(Model model, Authentication authentication, @RequestParam(required = false) String filter)
    {
        FilterDTO filterData = new FilterDTO();
        filterData.setFilter(filter);
        StageDTO stageData = new StageDTO();
        model.addAttribute("auth_username", authentication.getName());
        model.addAttribute("filterDTO", filterData);
        model.addAttribute("stageDTO", stageData);
        model.addAttribute("stages", repo.findAll());
        model.addAttribute("freeCompanies", companyRepo.findAllWithoutStages());
        model.addAttribute("companyStageDTO", new CompanyStageDTO());
        
        return "pages/stages/index";
    }

    @PostMapping("/stages")
    public String store(
        Model model, 
        @Valid @ModelAttribute StageDTO stageData,
        BindingResult result
    ) {
        if(result.hasErrors())
        {
            return "redirect:/stages";
        }
        try{
            Stage newStage = new Stage();
            newStage.setName(stageData.getName());
            newStage.setCreatedAt(new Date());
            repo.save(newStage);

            model.addAttribute("stageDTO", new StageDTO());
            model.addAttribute("filterDTO", new FilterDTO());
            model.addAttribute("success", true);
        }catch(Exception except){
            result.addError(
                new FieldError("stageDTO", "name", except.getMessage())
            );
            return "redirect:/stages";
        }
        
        return "redirect:/stages";
    }

    @DeleteMapping("/stages/{id}")
    public String destroy(@PathVariable("id") Integer id)
    {
        repo.deleteById(id);
        return "redirect:/stages";
    }
}
