# 単語帳アプリケーション

## 📄 概要

複数分野の単語を効率よく管理・確認するためのシンプルな構成のアプリケーション
- **URL**：[https://www.wordbookapp.hiroyuki-o.jp](https://www.wordbookapp.hiroyuki-o.jp)
- **動作確認用アカウント**：
  - ユーザー名：`test`
  - パスワード：`test`

## ⭐️ 作成目的

Webアプリを公開する流れを経験すること

## 🛠️ 機能

- **単語の登録**  
  ユーザーが新しい単語をアプリに追加できる。
  - 単語名（一意）
  - 正式名称（任意）
  - カテゴリ（単一選択）
  - 意味（任意）

- **単語の編集**  
  登録済みの単語情報を編集できる。

- **単語の削除**  
  不要な単語を削除できる。

- **登録内容の一覧表示**  
  登録された単語を一覧で表示できる。

- **カテゴリ別の一覧表示**  
  登録された単語をカテゴリごとにグループ化して表示できる。
  
- **単語テスト**  
 登録済みの単語を使用して4択の確認テストを行える。
 (最低4単語以上の単語登録が必要)

- **アカウント登録**  
 アカウント登録で任意のアカウント名で作成できる。
 (ユーザー名は重複して登録できない)

## 💻 技術スタック

- **フロントエンド**  
  - HTML, CSS, JavaScript  
  - テンプレートエンジン：Thymeleaf  

- **バックエンド**  
  - Java（Spring Boot / Spring Security）  

- **データベース**  
  - PostgreSQL  

- **インフラ・公開環境**  
  - AWS EC2（Amazon Linux）  
  - 独自ドメイン（お名前.com）  
  - HTTPS対応（Let's Encrypt + DNS設定）  

- **開発環境**  
  - macOS  
  - Visual Studio Code  

- **使用ライブラリ・ツール**  
  - Lombok  

## 🖥️ 画面構成

1. ログイン画面
2. 単語一覧画面（トップページ）
3. 編集画面
4. 新規登録画面
5. 単語テスト画面
6. アカウント登録画面
   
## 📘 詳細設計書

🔗 [シーケンス図](https://github.com/1996OH/WordBook/tree/main/WordBook/docs/%E3%82%B7%E3%83%BC%E3%82%B1%E3%83%B3%E3%82%B9%E5%9B%B3)  
🔗 [ER図](https://github.com/1996OH/WordBook/blob/main/WordBook/docs/ER%E5%9B%B3.md)  
🔗 [クラス図](https://github.com/1996OH/WordBook/blob/main/WordBook/docs/%E3%82%AF%E3%83%A9%E3%82%B9%E5%9B%B3.md)  
🔗 [画面遷移図](https://github.com/1996OH/WordBook/blob/main/WordBook/docs/%E7%94%BB%E9%9D%A2%E9%81%B7%E7%A7%BB%E5%9B%B3.md)  
