package se.gokopen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
    private Integer id;
    private String username;
    private String password;
    private boolean enabled = true;
    private String role;

    
    public User(){
        
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "USER_SEQUENCE")
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
