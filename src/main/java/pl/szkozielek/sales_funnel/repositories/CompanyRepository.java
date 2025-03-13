package pl.szkozielek.sales_funnel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.szkozielek.sales_funnel.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> 
{
    @Query(value = "SELECT * FROM companies WHERE name LIKE ?1 OR shortname LIKE ?1", nativeQuery = true)
    List<Company> findAllByFilter(String filter);
}
