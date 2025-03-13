package pl.szkozielek.sales_funnel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @NotEmpty
    @Email
    private String email;
    @Size(min = 8, message = "Hasło powinno posiadać przynajmniej 8 znaków")
    private String password;
    private String confirmPassword;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
