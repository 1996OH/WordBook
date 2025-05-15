package com.example.WordBook.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

// クイズ問題を表すモデルクラス
@Getter
@AllArgsConstructor 
public class QuizQuestion {
    // 都度生成して使用するのでfinalで定義
    private final String question;            // 問題となる単語
    private final List<String> options;       // 選択肢
    private final String correctAnswer;       // 回答
}