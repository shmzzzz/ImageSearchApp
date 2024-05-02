# ImageSearchApp

- UnsplashAPIを使用した画像検索アプリ
- システムテーマ(ライト/ダーク)ごとに色を変更する

## 使用技術
![](https://img.shields.io/badge/jetpack_compose-FF6441)
![](https://img.shields.io/badge/viewmodel-FF6441)
![](https://img.shields.io/badge/coroutine-FF6441)
![](https://img.shields.io/badge/flow-FF6441)
![](https://img.shields.io/badge/clean_architecture-FF6441)
![](https://img.shields.io/badge/material3-FF6441)
<br/>
![](https://img.shields.io/badge/retrofit-v2.9.0-blue)
![](https://img.shields.io/badge/moshi-v1.14.0-blue)
![](https://img.shields.io/badge/hilt-v2.44-blue)
![](https://img.shields.io/badge/navigation_compose-v2.7.6-blue)
<br/>
![](https://img.shields.io/badge/hilt_navigation-v1.0.0-blue)
![](https://img.shields.io/badge/navigation_compose-v2.7.6-blue)
![](https://img.shields.io/badge/coil-v2.5.0-blue)
![](https://img.shields.io/badge/accompanist-v0.30.1-blue)

## 構成
![アーキテクチャ](https://github.com/shmzzzz/ImageSearchApp/assets/85086833/04e0c791-903a-4f95-9e20-fc1e84644a4e)

## 画面仕様

**検索画面**

- 検索バー
    - キーワードで検索可能
- 各アイテムに表示する要素
    - 画像
    - 画像の説明
    - アップロードユーザー
    - いいね数
- アイテムタップで詳細画面に遷移する

 画面表示時                      | 検索時                      
----------------------------|--------------------------
 ![](readmefiles/画面表示時.png) | ![](readmefiles/検索時.png) 

---

**詳細画面**

- 各項目を表示する
    - 画像
    - 画像の説明
    - アップロードユーザー名
    - いいね数
    - シェア数
    - カメラ
    - ロケーション

詳細画面

<img width="50%" src="readmefiles/詳細画面.png">
