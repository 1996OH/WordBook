package com.example.WordBook.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WordBook.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {

    // 真偽値取得（ 単語 AND NOT ID）
    boolean existsByTermAndIdNot(String term, Long id);

    // カテゴリごとの単語リストを取得
    List<Word> findByCategory(String category);
}
