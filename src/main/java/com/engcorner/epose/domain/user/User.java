package com.engcorner.epose.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 16)
    private String username; // 用户名

    @Column(nullable = false)
    private String password; // 密码

    @Column(nullable = false, length = 16)
    private String name; // 名称

    private Date expdate; // 有效期

    private Boolean islock; // 锁定

    @OneToOne(fetch = FetchType.EAGER)
    private UserPose userPose;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpdate() {
        return expdate;
    }

    public void setExpdate(Date expdate) {
        this.expdate = expdate;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        if(expdate == null) return true;
        Date date = new Date();
        return date.before(expdate);
    }

    @Override
    public boolean isAccountNonLocked() {
        if(islock == null) return true;
        return !islock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserPose getUserPose() {
        return userPose;
    }

    public void setUserPose(UserPose userPose) {
        this.userPose = userPose;
    }
}
