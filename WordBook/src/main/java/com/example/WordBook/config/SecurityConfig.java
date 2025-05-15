package com.example.WordBook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.WordBook.model.User;
import com.example.WordBook.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository repo;

    // コンストラクタ
    public SecurityConfig(UserRepository repo) {
        this.repo = repo;
    }

    // セキュリティフィルターチェーンの設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/**").permitAll()  // ログインページと静的リソースを許可
                .anyRequest().authenticated()  // 他のページは認証が必要
            )
            .formLogin(form -> form
            .loginPage("/login")
            .successHandler((request, response, authentication) -> {
                String name = authentication.getName();
                User user = repo.findByName(name).orElseThrow();
                request.getSession().setAttribute("loginUser", user); // セッションに保存
                response.sendRedirect("/wordBook");
            })
        
        )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")  // ログアウト後のリダイレクト先
                .permitAll()  // ログアウトも許可
            );

        return http.build();
    }
}