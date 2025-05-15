package com.example.WordBook.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.WordBook.model.QuizQuestion;
import com.example.WordBook.model.Word;
import com.example.WordBook.repository.WordRepository;

@Service
public class QuizService {

    private final WordRepository repo;

    // コンストラクタ
    public QuizService(WordRepository repo) {
        this.repo = repo;
    }

    // クイズ問題を1問生成（book単位）
    public QuizQuestion generateQuestion(String book) {

        // 指定されたbookに該当する単語を取得
        List<Word> allWords = repo.findByBook(book);

        // 単語数が4未満の場合はnullを返す
        if (allWords.size() < 4) {
            return null;
        }
        // 正解の単語をランダムに選択
        Word correctWord = allWords.get(new Random().nextInt(allWords.size()));

        // 正解以外の単語の意味を取得
        List<String> wrongOptions = allWords.stream()
            .filter(w -> !w.getId().equals(correctWord.getId()))
            .map(Word::getMeaning)
            .filter(s -> s != null && !s.trim().isEmpty()) 
            .distinct()
            .toList();

        // 不正解候補が足りない場合はnullを返す
        if (wrongOptions.size() < 3) {
            return null; 
        }

        // 不正解候補をランダムに3つ選択
        List<String> list = new ArrayList<>(wrongOptions);
        Collections.shuffle(list);

        // 正解の意味と不正解の意味を組み合わせて選択肢を作成
        List<String> options = new ArrayList<>();
        options.add(correctWord.getMeaning());
        options.addAll(list.subList(0, 3));
        Collections.shuffle(options);

        return new QuizQuestion(correctWord.getTerm(), options, correctWord.getMeaning());
    }
}