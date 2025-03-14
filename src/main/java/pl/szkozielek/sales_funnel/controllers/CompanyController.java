package pl.szkozielek.sales_funnel.controllers;

import java.util.Date;
import java.util.Optional;

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

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import pl.szkozielek.sales_funnel.dto.CompanyDTO;
import pl.szkozielek.sales_funnel.dto.FilterDTO;
import pl.szkozielek.sales_funnel.models.Company;
import pl.szkozielek.sales_funnel.repositories.CompanyRepository;
import pl.szkozielek.sales_funnel.services.CompanyStageService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CompanyController {

    @Autowired 
    private CompanyRepository repo;

    @Autowired
    private CompanyStageService stageAssignService;

    @GetMapping("/companies")
    public String index(Model model, Authentication authentication, @RequestParam(required = false) String filter)
    {
        FilterDTO filterData = new FilterDTO();
        filterData.setFilter(filter);
        model.addAttribute("auth_username", authentication.getName());
        model.addAttribute("filterDTO", filterData);
        if(filterData.getFilter() != null){
            model.addAttribute("companies", repo.findAllByFilter("%" + filterData.getFilter() + "%"));
        }
        else{
            model.addAttribute("companies", repo.findAll());
        }
        
        return "pages/companies/index";
    }

    @GetMapping("/companies/create")
    public String create(Model model, Authentication authentication)
    {
        CompanyDTO companyData = new CompanyDTO();
        model.addAttribute("companyDTO", companyData);
        model.addAttribute("auth_username", authentication.getName());
        return "pages/companies/create";
    }

    @GetMapping("/companies/{id}/edit")
    public String edit(Model model, Authentication authentication, @PathVariable("id") Integer id)
    {
        Optional<Company> myCompany = repo.findById(id);
        if(!myCompany.isPresent()){
            throw new EntityNotFoundException("Company was not found");
        }

        CompanyDTO companyData = new CompanyDTO();
        companyData.setName(myCompany.get().getName());
        companyData.setShortname(myCompany.get().getShortname());
        companyData.setIdentificationNumber(myCompany.get().getIdentificationNumber());
        companyData.setShareCapital(myCompany.get().getShareCapital());

        model.addAttribute("company_id", id);
        model.addAttribute("company_shortname", myCompany.get().getShortname());
        model.addAttribute("companyDTO", companyData);
        model.addAttribute("auth_username", authentication.getName());
        return "pages/companies/edit";
    }

    @PostMapping("/companies")
    public String store(
        Model model, 
        @Valid @ModelAttribute CompanyDTO companyData,
        BindingResult result
    ) {
        if(result.hasErrors())
        {
            return "pages/companies/create";
        }
        try{
            Company newCompany = new Company();
            newCompany.setName(companyData.getName());
            newCompany.setShortname(companyData.getShortname());
            newCompany.setIdentificationNumber(companyData.getIdentificationNumber());
            newCompany.setShareCapital(companyData.getShareCapital());
            newCompany.setCreatedAt(new Date());
            repo.save(newCompany);

            model.addAttribute("companyDTO", new CompanyDTO());
            model.addAttribute("success", true);
        }catch(Exception except){
            result.addError(
                new FieldError("companyDTO", "name", except.getMessage())
            );
            return "pages/companies/create";
        }
        
        return "redirect:/companies";
    }

    @PutMapping("/companies/{id}")
    public String update(
        @PathVariable String id,
        Model model, 
        @Valid @ModelAttribute CompanyDTO companyData,
        BindingResult result
    ) {
        if(result.hasErrors())
        {
            return String.format("pages/companies/%d/edit", id);
        }
        try{
            Optional<Company> tryCompany = repo.findById(Integer.parseInt(id));
            if(!tryCompany.isPresent()){
                throw new EntityNotFoundException("Company was not found");
            }
            Company updatedCompany = tryCompany.get();
            updatedCompany.setName(companyData.getName());
            updatedCompany.setShortname(companyData.getShortname());
            updatedCompany.setIdentificationNumber(companyData.getIdentificationNumber());
            updatedCompany.setShareCapital(companyData.getShareCapital());
            repo.save(updatedCompany);

            model.addAttribute("companyDTO", new CompanyDTO());
            model.addAttribute("success", true);
        }catch(Exception except){
            result.addError(
                new FieldError("companyDTO", "name", except.getMessage())
            );
            return "pages/companies/edit";
        }
        
        return "redirect:/companies";
    }

    @DeleteMapping("/companies/{id}")
    public String destroy(@PathVariable("id") Integer id)
    {
        stageAssignService.detachAllStagesFromCompany(id);
        repo.deleteById(id);
        return "redirect:/companies";
    }
}
