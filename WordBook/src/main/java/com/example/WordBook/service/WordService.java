package com.example.WordBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WordBook.model.Word;
import com.example.WordBook.repository.WordRepository;

@Service
public class WordService {

    @Autowired
    private WordRepository repo;

    // ID指定行取得
    public Word get(Long id) {
        return repo.findById(id).orElse(null);
    }

    // カテゴリ絞り込み行取得
    public List<Word> getWordsByCategoryAndBook(String category, String book) {
        if(book.equals("admin")) {
            return repo.findByCategory(category);
        }
        return repo.findByCategoryAndBook(category, book);
    }

    // 保存
    public void save(Word word) {
        repo.save(word);
    }

    // ID指定削除
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 真偽値確認（word AND NOT ID）
    public boolean existsByTermAndBook(String term, String book) {
        return repo.existsByTermAndBook(term, book);
    }

    // 真偽値確認（word AND book AND NOT ID）
    public boolean existsByTermAndBookAndIdNot(String term, String book, Long id) {
        return repo.existsByTermAndBookAndIdNot(term, book, id);
    }

    // ユーザーごとの単語帳を抽出
    public List<Word> getWordsByBook(String book) {
        if(book.equals("admin")) {
            return repo.findAll();
        }
        return repo.findByBook(book);
    }
}