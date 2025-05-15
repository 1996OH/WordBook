package com.example.WordBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "word")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;             // ID

    private String term;         // 単語  cme
    private String formal_term;  // 正式名称
    private String category;     // カテゴリ
    private String meaning;      // 意味
    private String book;         // 単語帳名
}