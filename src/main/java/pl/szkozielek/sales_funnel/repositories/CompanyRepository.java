package pl.szkozielek.sales_funnel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import pl.szkozielek.sales_funnel.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> 
{
    @Query(value = "SELECT * FROM companies WHERE name LIKE ?1 OR shortname LIKE ?1", nativeQuery = true)
    List<Company> findAllByFilter(String filter);

    @Query(value = "SELECT companies.* FROM companies LEFT JOIN company_stage ON companies.id = company_stage.company_id WHERE company_stage.stage_id IS NULL", nativeQuery = true)
    List<Company> findAllWithoutStages();

    @Modifying
    @Query(value = "DELETE FROM company_stage WHERE company_id = ?1", nativeQuery = true)
    void detachAllStages(Integer companyID);
}
