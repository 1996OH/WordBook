◆概要
１，このアプリは複数分野の単語を効率よく管理、確認するためのシンプルな構成のアプリである

◆機能
１，単語を登録できる
　　登録項目
　　・単語名（一意）
　　・正式名称（任意）
　　・カテゴリ（単一選択）
　　・意味（任意）
２，単語を編集できる
３，単語を削除できる
４，登録内容を一覧表示できる
５，登録内容をカテゴリ別に一覧表示ができる

◆技術
１，フロントエンド
　　・HTML/CSS
２，バックエンド
　　・Java
３，DB
　　・H2
４，フレームワーク
　　・SpringBoot/thymeleaf
５，環境
　　・Mac/VisualStadio

◆画面構成
１，単語一覧画面(トップページ)
２，編集画面
３，新規登録画面

◆構成
WordBook/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/wordbook/
│       │       ├── controller/
│       │       │   └── WordController.java
│       │       ├── domain/
│       │       │   └── Word.java
│       │       ├── repository/
│       │       │   └── WordRepository.java
│       │       ├── service/
│       │       │   └── WordService.java
│       │       └── WordBookApplication.java
│       └── resources/
│           ├── application.properties
│           ├── data.sql
│           ├── static/
│           │  ├── inputform.css
│           │  └── style.css
│           └── templates/
│               ├── add.html
│               ├── index.html
│               └── update.html
├── target/
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
