package pl.szkozielek.sales_funnel.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="companies")
public class Company {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(nullable = true)
    private String shortname;

    @Column(nullable = true)
    private Float shareCapital;

    @Column(nullable = true)
    private String identificationNumber;

    private Date createdAt;

    @ManyToMany(mappedBy = "companies")
    private List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
