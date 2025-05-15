package com.example.WordBook.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WordBook.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {

    // ユーザー絞り込み検索
    List<Word> findByBook(String book);// 単語をbookでフィルタして取得
    
    // 真偽値取得（ term AND NOT ID）新規登録用
    boolean existsByTermAndBook(String term, String book);

    //  真偽値取得（ term AND book AND NOT ID）編集用
    boolean existsByTermAndBookAndIdNot(String term, String book, Long id);

    // カテゴリ検索
    List<Word> findByCategoryAndBook(String category, String book);// カテゴリごとの単語リストを取得

    // カテゴリごとの単語リストを取得(admin用)
    List<Word> findByCategory(String category);
    
}
