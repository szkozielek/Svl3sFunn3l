package pl.szkozielek.sales_funnel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.szkozielek.sales_funnel.models.Stage;

public interface StageRepository extends JpaRepository<Stage, Integer> 
{

}