package pl.szkozielek.sales_funnel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CompanyDTO {
    @NotEmpty
    @Size(max = 255, message = "Nazwa nie może przekraczać 255 znaków")
    private String name;
    @NotEmpty
    @Size(max = 100, message = "Krótka nazwa nie może przekraczać 100 znaków")
    private String shortname;
    private Float shareCapital;
    private String identificationNumber;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortname() {
        return shortname;
    }
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public Float getShareCapital() {
        return shareCapital;
    }
    public void setShareCapital(Float shareCapital) {
        this.shareCapital = shareCapital;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
