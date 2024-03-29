/**
 * ビジネスルールのテスト
 *
 * テストすべきビジネスルール
 * ・貸出制限
 * ・所蔵品の状態遷移
 * ・予約の状態遷移
 *
 * 貸出制限は、ドメインモデルのテスト（サービスクラスのテストは不要）
 *
 * 状態遷移は、コーディネータクラスのメソッドを使ってテストする
 * - 必要であればサービスクラスのメソッドを使ってもよい
 * - データベースの記録・参照まで含む
 * - プレゼンテーション層のクラスのテストは対象外
 * - 遷移不可のテストケースをどうするか？
 */
package library.domain;
