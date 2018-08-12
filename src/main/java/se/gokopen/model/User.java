package se.gokopen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {
    private Integer id;
    private String username;
    private String password;
    private boolean enabled = true;
    private String role;

    
    public User(){
        
    }
    
    
    @Id
    @SequenceGenerator(name = "userSeqGen", sequenceName = "USER_SEQ", initialValue = 100, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeqGen")
    @Column(name="userid", nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="username", length=45,nullable=false)
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password", length=45,nullable=false)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="enabled")
    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString(){
        return "Aktell användare: " + id + username + " lösen: " + password + " enabled " + enabled;
    }

    @Column(name="role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
