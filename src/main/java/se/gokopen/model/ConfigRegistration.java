package se.gokopen.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "configregistration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConfigRegistration {

    private Integer id;
    private Boolean allowPublicRegistration = false;
    private Date lastRegisterDay;
    private String registerInfo;
    private Integer maxPatrols;
    private String confirmMessage;
    private String registrationNotOpen;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lastregisterday")
    public Date getLastRegisterDay() {
        return lastRegisterDay;
    }

    public void setLastRegisterDay(Date lastRegisterDay) {
        this.lastRegisterDay = lastRegisterDay;
    }

    @Column(name = "registerinfo", length = 600)
    public String getRegisterInfo() {
        return registerInfo;
    }

    public void setRegisterInfo(String registerInfo) {
        this.registerInfo = registerInfo;
    }

    @Column(name = "maxpatrols")
    public Integer getMaxPatrols() {
        return maxPatrols;
    }

    public void setMaxPatrols(Integer maxPatrols) {
        this.maxPatrols = maxPatrols;
    }

    @Column(name = "confirmmessage", length = 600)
    public String getConfirmMessage() {
        return confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage = confirmMessage;
    }

    @Column(name = "allowpublicregistration")
    public Boolean getAllowPublicRegistration() {
        return allowPublicRegistration;
    }

    public void setAllowPublicRegistration(Boolean allowPublicRegistration) {
        this.allowPublicRegistration = allowPublicRegistration;
    }

    @Column(name = "regnotopenmsg")
    public String getRegistrationNotOpen() {
        return registrationNotOpen;
    }

    public void setRegistrationNotOpen(String msg) {
        this.registrationNotOpen = msg;
    }
}