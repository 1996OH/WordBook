package com.example.WordBook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WordBook.model.Word;
import com.example.WordBook.service.WordService;

@Controller
public class WordController {

    // サービスクラス
    @Autowired
    private WordService service;

    // 単語一覧画面(トップページ)表示
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listWords", service.getAllWords());
        return "index";
    }

    // 新規単語追加画面表示
    @GetMapping("/new")
    public String showNewWordForm(Model model) {
        model.addAttribute("word", new Word());
        return "create";
    }

    // 新規登録実行
    @PostMapping("/save")
    public String saveWord(
            @ModelAttribute Word word,
            @RequestParam String action,
            Model model) {

        // すでに同じ単語が登録されているか確認
        boolean exists = service.existsByTermAndIdNot(word.getTerm(),word.getId());  

        // ダブりがなければ保存
        if (!exists) {
            service.save(word);
        }

        // 「続けて追加」が押されている場合入力フォーム画面に戻る
        if ("continue".equals(action)) {
            model.addAttribute("word", new Word());     // フォームクリア
            return "create"; 
        } else {
            return "redirect:/";
        }
    }

    // 編集画面呼び出し
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Word word = service.get(id);
        model.addAttribute("word", word);
        return "edit";
    }

    // 削除処理実行
    @GetMapping("/delete/{id}")
    public String deleteWord(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    // 更新処理実行
    @PostMapping("/update")
    public String updateWord(@ModelAttribute Word word,
                         @RequestParam String action,
                         Model model) {

        // すでに同じ単語が登録されているか確認
        boolean exists = service.existsByTermAndIdNot(word.getTerm(),word.getId());

        // ダブりがなければ保存
        if (!exists) {
            service.save(word); 
            return "redirect:/";
        }else{
            model.addAttribute("word", word);
            return "edit";
        }
    }

    // 絞り込み検索機能
    @GetMapping("/words/filter")
    public String filterByCategory(@RequestParam("category") String category, Model model) {
        List<Word> words;

        // 「すべて」の場合、全行を読み込み。その他は絞り込み検索を実行
        if ("all".equals(category)) {
            words = service.getAllWords();
        } else {
            words = service.getWordsByCategory(category);
        }

        model.addAttribute("listWords", words);
        model.addAttribute("selectedCategory", category);
        return "index";
    }
}
