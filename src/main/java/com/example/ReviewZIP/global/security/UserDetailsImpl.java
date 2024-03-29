package com.example.ReviewZIP.global.security;

import com.example.ReviewZIP.domain.user.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    // UserDetails 계약에 사용자 세부 정보 매핑
    // Users 엔티티가 반드시 필요하다는 것을 지정하기 위해 final로 지정
    private final Users user;

    public UserDetailsImpl(Users user) {

        this.user = user;
    }

    // 현재 권한은 user만 있음
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword(){
        return user.getPassword();
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

    // 사용자의 ID를 가져오는 메소드
    public Long getUserId() {
        return user.getId();  // Users 엔티티의 getId() 메소드 호출
    }
}
