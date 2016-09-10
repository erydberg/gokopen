package se.gokopen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Config {
    private Integer id;
    private String name;
    private Boolean allowPublicResult;
    private String phone;
    
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

    @Column (name="phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  
}
