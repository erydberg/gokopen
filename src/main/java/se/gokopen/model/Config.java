package se.gokopen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="config")
public class Config {
    private Integer id;
    private String name;
    private Boolean allowPublicResult;
    
    public Config(){
        
    }

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column (name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name="allowpublicresult")
    public Boolean getAllowPublicResult() {
        return allowPublicResult;
    }

    public void setAllowPublicResult(Boolean allowPublicResult) {
        this.allowPublicResult = allowPublicResult;
    }

  
}
