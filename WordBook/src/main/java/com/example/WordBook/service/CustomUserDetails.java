// ファイル：CustomUserDetails.java

package com.example.WordBook.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.WordBook.model.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    // ユーザー情報を取得するためのメソッド
    public String getBook() {
        return user.getBook();
    }

    // ユーザー情報取得
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    // ユーザーのパスワードを取得するためのメソッド
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // ユーザー名を取得するためのメソッド
    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
