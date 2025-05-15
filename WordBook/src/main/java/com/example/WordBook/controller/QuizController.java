package com.example.WordBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WordBook.model.QuizQuestion;
import com.example.WordBook.model.User;
import com.example.WordBook.service.QuizService;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {
    
    // サービスクラス
    @Autowired
    private QuizService quizService;

     // クイズ生成機能、画面表示
     @GetMapping("/quiz")
     public String quiz(Model model, HttpSession session) {
        // セッションに保存されたユーザー情報を取得
         User loginUser = (User) session.getAttribute("loginUser");
         if (loginUser == null) {
            System.err.println("未認証ユーザー");
             return "redirect:/login";
         }
     
         // book単位でクイズ問題を生成
         QuizQuestion question = quizService.generateQuestion(loginUser.getBook());
     
         // クイズが生成できなかった場合（単語が不足など）
         if (question == null) {
            System.out.println("クイズ生成失敗");
             model.addAttribute("error", "クイズを生成できるだけの単語が登録されていません（最低4単語必要）");
             return "wordBook";
         }
     
         model.addAttribute("questionWord", question.getQuestion());
         model.addAttribute("options", question.getOptions());
         model.addAttribute("correctAnswer", question.getCorrectAnswer());
         return "quiz";
     }
}
