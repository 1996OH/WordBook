package com.example.WordBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WordBook.model.User;
import com.example.WordBook.model.Word;
import com.example.WordBook.service.WordService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WordController {

    // サービスクラス
    @Autowired
    private WordService service;

    // 単語一覧画面(トップページ)表示
    @GetMapping("/wordBook")
    public String viewHomePage(HttpSession session, Model model) {
        System.out.println("単語一覧画面表示");
        User loginUser = (User) session.getAttribute("loginUser");
        // 未認証ユーザーの場合はログインページにリダイレクト
        if (loginUser == null) {
            System.err.println("未認証ユーザー");
            return "redirect:/login"; // ログインページにリダイレクト
        }
        // bookでフィルタ
        List<Word> userWords = service.getWordsByBook(loginUser.getBook()); 
        model.addAttribute("listWords", userWords);
        model.addAttribute("name", loginUser.getName());
        return "wordBook";
    }

    // 新規単語追加画面表示
    @GetMapping("/new")
    public String showNewWordForm(Model model) {
        model.addAttribute("word", new Word());
        return "create";
    }

    // 新規単語登録実行
    @PostMapping("/save")
    public String saveWord(
            @ModelAttribute Word word,
            @RequestParam String action,
            HttpSession session,
            Model model) {

        System.out.println("新規登録実行");
        // セッションに保存されたユーザー情報を取得
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            System.err.println("未認証ユーザー");
            return "redirect:/login";
        }

        // book名を設定
        word.setBook(loginUser.getBook());

        // すでに同じ単語が登録されているか確認
        boolean isSave = !service.existsByTermAndBook(
                word.getTerm(),
                word.getBook());

        // 既存の単語があった場合、フォームに戻る
        if (isSave == true) {
            System.out.println("正常処理");
            service.save(word);
        } else {
            System.out.println("重複あり");
            model.addAttribute("error", "この単語はすでに登録されています");
            model.addAttribute("word", word);
            return "create"; 
        }

        // 「続けて追加」が押されている場合入力フォーム画面に戻る
        if ("continue".equals(action)) {
            System.out.println("続けて追加");
            model.addAttribute("word", new Word()); // フォームクリア
            return "create";
        } else {
            return "redirect:/wordBook";
        }
    }

    // 編集画面呼び出し
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        System.out.println("編集画面呼び出し");
        Word word = service.get(id);
        model.addAttribute("word", word);
        return "edit";
    }

    // 削除処理実行
    @GetMapping("/delete/{id}")
    public String deleteWord(@PathVariable Long id) {
        System.out.println("削除処理実行");
        service.delete(id);
        return "redirect:/wordBook";
    }

    // 更新処理実行
    @PostMapping("/update")
    public String updateWord(@ModelAttribute Word word,
            @RequestParam String action,
            HttpSession session,
            Model model) {

        System.out.println("更新処理実行");
        // セッションに保存されたユーザー情報を取得
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            System.err.println("未認証ユーザー");
            return "redirect:/login";
        }

        // book名を設定
        word.setBook(loginUser.getBook());

        // すでに同じ単語が登録されているか確認
        boolean exists = service.existsByTermAndBookAndIdNot(
                word.getTerm(),
                word.getBook(),
                word.getId());
        // ダブりがなければ保存
        if (!exists) {
            System.out.println("正常処理");
            service.save(word);
            return "redirect:/wordBook"; // 更新後、単語一覧ページへリダイレクト
        } else {
            System.out.println("重複あり");
            model.addAttribute("error", "この単語はすでに登録されています");
            model.addAttribute("word", word);
            return "edit"; // 編集ページに戻る
        }
    }

    // 絞り込み検索機能
    @GetMapping("/words/filter")
    public String filterByCategory(
            @RequestParam("category") String category,
            Model model,
            HttpSession session) {

        // セッションに保存されたユーザー情報を取得
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        List<Word> words;
        // すべての場合はbook内の全ての単語を取得、そうでない場合はカテゴリでフィルタ
        if ("all".equals(category)) {
            words = service.getWordsByBook(loginUser.getBook());
        } else {
            words = service.getWordsByCategoryAndBook(category, loginUser.getBook());
        }

        model.addAttribute("listWords", words);
        model.addAttribute("selectedCategory", category);
        return "wordBook"; // 絞り込み後、再度wordBookページに表示
    }

   
}