package pl.szkozielek.sales_funnel.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import pl.szkozielek.sales_funnel.dto.CompanyStageDTO;
import pl.szkozielek.sales_funnel.models.Company;
import pl.szkozielek.sales_funnel.models.Stage;
import pl.szkozielek.sales_funnel.repositories.CompanyRepository;
import pl.szkozielek.sales_funnel.repositories.StageRepository;
import pl.szkozielek.sales_funnel.services.CompanyStageService;

@Controller
public class AssignCompanyToStageController {

    @Autowired 
    private StageRepository stageRepo;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired 
    private CompanyStageService relationService;

    @PatchMapping("/stages/{stage_id}/companies")
    public String assignCompanyToStage(
        @Valid @ModelAttribute CompanyStageDTO assignData,
        @PathVariable("stage_id") Integer stageId
    )
    {
        Optional<Company> myCompany = companyRepo.findById(assignData.getCompany_id());
        Optional<Stage> myStage = stageRepo.findById(stageId);
        if(!myCompany.isPresent()){
            return "redirect:/stages";
        }
        if(!myStage.isPresent()){
            return "redirect:/stages";
        }
        Stage stage = myStage.get();
        relationService.detachAllStagesFromCompany(myCompany.get().getId());
        stage.getCompanies().add(myCompany.get());
        stageRepo.save(stage);
        return "redirect:/stages";
    }

    @PatchMapping("/companies/{company_id}/stages")
    public String assignStageToCompany(
        @Valid @ModelAttribute CompanyStageDTO assignData,
        @PathVariable("company_id") Integer companyId
    )
    {
        Optional<Company> myCompany = companyRepo.findById(companyId);
        Optional<Stage> myStage = stageRepo.findById(assignData.getStage_id());
        if(!myCompany.isPresent()){
            return "redirect:/stages";
        }
        if(!myStage.isPresent()){
            return "redirect:/stages";
        }
        Stage stage = myStage.get();
        relationService.detachAllStagesFromCompany(myCompany.get().getId());
        stage.getCompanies().add(myCompany.get());
        stageRepo.save(stage);
        return "redirect:/stages";
    }
}
