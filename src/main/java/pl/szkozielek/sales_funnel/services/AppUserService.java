package pl.szkozielek.sales_funnel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.szkozielek.sales_funnel.models.AppUser;
import pl.szkozielek.sales_funnel.repositories.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService{
    @Autowired
    private AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        AppUser appUser = repo.findByEmail(email);
        if(appUser != null) {
            return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles("user")
                .build();

        }
        throw new UsernameNotFoundException("user not founded " + email);
    }
}
