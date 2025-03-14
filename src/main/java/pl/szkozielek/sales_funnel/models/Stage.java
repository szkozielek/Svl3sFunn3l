package pl.szkozielek.sales_funnel.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="stages")
public class Stage {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    private Date createdAt;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "company_stage", 
      joinColumns = @JoinColumn(name = "stage_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "company_id", 
      referencedColumnName = "id"))
    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
