package com.example.WordBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;             // ID
    private String term;         // 単語  
    private String formal_term;  // 正式名称
    private String category;     // カテゴリ
    private String meaning;      // 意味

    public Word() {
    }
    
    public Word(String term, String formal_term, String category, String meaning) {
        this.term = term;
        this.formal_term = formal_term;
        this.category = category;
        this.meaning = meaning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    public String getFormal_term() {
        return formal_term;
    }

    public void setFormal_term(String formalTerm) {
        this.formal_term = formalTerm;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}