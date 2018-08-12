package se.gokopen.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Config {
    private Integer id;
    private String name;
    private Boolean allowPublicResult;
    private String phone;
    private Date lastRegisterDay;
    private String registerInfo;
    private Integer maxPatrols;
    private String confirmMessage;
    private Boolean allowPublicRegistration;

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
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column (name="lastregisterday")
    public Date getLastRegisterDay() {
        return lastRegisterDay;
    }

    public void setLastRegisterDay(Date lastRegisterDay) {
        this.lastRegisterDay = lastRegisterDay;
    }

    @Column (name="registerinfo", length=600)
    public String getRegisterInfo() {
        return registerInfo;
    }

    public void setRegisterInfo(String registerInfo) {
        this.registerInfo = registerInfo;
    }

    @Column (name="maxpatrols")
    public Integer getMaxPatrols() {
        return maxPatrols;
    }

    public void setMaxPatrols(Integer maxPatrols) {
        this.maxPatrols = maxPatrols;
    }

    @Column (name="confirmmessage", length=600)
    public String getConfirmMessage() {
        return confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage = confirmMessage;
    }


    @Column (name="allowpublicregistration")
    public Boolean getAllowPublicRegistration() {
        return allowPublicRegistration;
    }

    public void setAllowPublicRegistration(Boolean allowPublicRegistration) {
        this.allowPublicRegistration = allowPublicRegistration;
    }
}
