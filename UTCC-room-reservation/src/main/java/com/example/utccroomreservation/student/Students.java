package com.example.utccroomreservation.student;

import com.example.utccroomreservation.booking.Booking;
import com.example.utccroomreservation.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Students implements UserDetails {

    @Id
    private Long studentId;

    private String studentNumber;

    private String email;

    private String password;

    private String name;

    private String phone;

    private String faculty;

    private String major;

    private Set<Role> roles;

    private Set<Permission> permissions;

    @JsonIgnore
    @OneToMany(mappedBy = "students")
    private List<Booking> bookingList;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(Role role : roles){
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        for(Permission permission : permissions){
            authorityList.add(new SimpleGrantedAuthority(permission.name()));
        }
        return authorityList;

    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return studentNumber;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}

