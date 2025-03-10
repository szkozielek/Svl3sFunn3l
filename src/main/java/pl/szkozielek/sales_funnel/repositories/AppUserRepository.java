package pl.szkozielek.sales_funnel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.szkozielek.sales_funnel.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> 
{
    public AppUser findByEmail(String email);
}
