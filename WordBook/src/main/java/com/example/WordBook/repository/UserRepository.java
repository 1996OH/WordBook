package com.example.WordBook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WordBook.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // ユーザー名とパスワードでユーザーを検索
    Optional<User> findByNameAndPassword(String name, String password);

    // ユーザー名でユーザーを検索
    Optional<User> findByName(String name);

    // ユーザー名の存在確認
    boolean existsByName(String name);

    
}