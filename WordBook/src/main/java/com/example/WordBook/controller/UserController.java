package com.example.WordBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WordBook.model.User;
import com.example.WordBook.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository repo;

    // ログイン画面表示
    @GetMapping({ "/" })
    public String showLoginForm() {
        return "login"; // ログインフォームを表示
    }

    // ログイン処理
    @PostMapping("/customLogin")
    public String login(@RequestParam String name,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        return repo.findByName(name)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    session.setAttribute("loginUser", user); // セッションにユーザー情報を保存
                    return "redirect:/wordBook"; // ログイン成功時
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "ユーザー名またはパスワードが違います"); 
                    return "login"; // エラー時に再度ログインページを表示
                });
    }

    // ログアウト処理
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // セッションを無効化
        return "redirect:/"; // ログインページにリダイレクト
    }

    // ユーザー登録画面表示
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());

        return "register"; // ユーザー登録フォームを表示
    }
    
    // ユーザー登録処理
    @PostMapping("/insertUser")
    public String register(@RequestParam String name,
            @RequestParam String password,
            @RequestParam String book,
            Model model) {

        // 入力内容に空欄がある場合
        if(name.isEmpty() || password.isEmpty() || book.isEmpty()) {
            model.addAttribute("error", "すべてのフィールドを入力してください");
            return "register"; 
        }
        // ユーザー名がすでに存在する場合
        if (repo.existsByName(name)) {
            model.addAttribute("error", "ユーザー名はすでに使用されています");
            return "register"; // エラー時に再度登録ページを表示
        }
        // ユーザーを保存
        repo.save(new User(name, password, book)); 
        
        return "redirect:/wordBook"; // 登録成功時にログインページにリダイレクト
    }
}