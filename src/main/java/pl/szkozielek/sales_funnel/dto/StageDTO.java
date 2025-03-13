package pl.szkozielek.sales_funnel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class StageDTO {
    @NotEmpty
    @Size(max = 255, message = "Nazwa nie może przekraczać 255 znaków")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
