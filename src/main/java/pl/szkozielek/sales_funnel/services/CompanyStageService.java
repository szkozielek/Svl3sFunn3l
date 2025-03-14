package pl.szkozielek.sales_funnel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.szkozielek.sales_funnel.repositories.CompanyRepository;

@Service
@Transactional
public class CompanyStageService {
    @Autowired
    private CompanyRepository companyRepo;


    public void detachAllStagesFromCompany(Integer companyID)
    {
        companyRepo.detachAllStages(companyID);
    }
}
