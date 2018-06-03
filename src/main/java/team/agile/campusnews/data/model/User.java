package team.agile.campusnews.data.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Integer id;

    private String username;

    private String code;

    private String name;

    private String phone;

    private String sex;

    private Integer age;

    private List<String> role;

    private List<SchoolOs> schoolOs;

    private String wxName;

    //微信头像URL
    private  String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public List<SchoolOs> getSchoolOs() {
        return schoolOs;
    }

    public void setSchoolOs(List<SchoolOs> schoolOs) {
        this.schoolOs = schoolOs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
      //  this.password = password;
        //TODO
    }

    public List<String> getRole() {
        if(role == null ){
            this.role = new ArrayList<>();
        }
        return this.role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> ro = new ArrayList<>();
        this.role.forEach(v->{
            ro.add(new SimpleGrantedAuthority(v));
        });

        return ro;
    }

    @Override
    public String getPassword() {

        return "no password!";
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}