package pl.szkozielek.sales_funnel.dto;

import jakarta.validation.constraints.NotNull;

public class CompanyStageDTO {
    @NotNull
    private Integer company_id;

    @NotNull
    private Integer stage_id;

    public Integer getCompany_id() {
        return company_id;
    }
    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
    public void setCompany_id(String company_id) {
        this.company_id = 0;
    }
    public Integer getStage_id() {
        return stage_id;
    }
    public void setStage_id(Integer stage_id) {
        this.stage_id = stage_id;
    }
    public void setStage_id(String stage_id) {
        this.stage_id = 0;
    }
}
