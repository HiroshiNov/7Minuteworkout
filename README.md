# 7 Minute Workout

Androidアプリ「7 Minute Workout」のサンプルプロジェクトです。

## 必要なツール
- Android Studio (3.5以上を推奨)
- JDK8 以降
- Android SDK

## ビルド方法
1. リポジトリをクローンします。
2. Android Studio で開くか、以下のコマンドでビルドします。
   ```
   ./gradlew assembleDebug
   ```

ビルド完了後、標準のエミュレータや実機で動作を確認できます。

## 使い方
- Android Studio からビルド完了後、ランにアプリを開いて開始ボタンを押すと、7分間のワークアウトが始まります。
- CLI からは
   ```
   ./gradlew installDebug
   ```
  でデバイスにアプリをインストールできます。

## 主なディレクトリ構成
```
.
|— app/             アプリ本体のモジュール
|   — src/main/    コードやリソース
|— gradle/          ビルドスクリプト関連
|— build.gradle     プロジェクト全体の設定
|— settings.gradle  モジュール管理
```
