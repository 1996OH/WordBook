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

    // 全行取得
    public List<Word> getAllWords() {
        return repo.findAll();
    } 
    
    // ID指定行取得
    public Word get(Long id) {
        return repo.findById(id).orElse(null);
    }

    // カテゴリ絞り込み行取得
    public List<Word> getWordsByCategory(String category) {
        return repo.findByCategory(category);
    }

    // 保存
    public void save(Word word) {
        repo.save(word);
    }

    // ID指定削除
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 真偽値確認（単語 AND NOT ID）
    public boolean existsByTermAndIdNot(String term,Long id) {
        return repo.existsByTermAndIdNot(term,id);
    }

}