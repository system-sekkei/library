
-- <editor-fold desc="資料_所蔵品目スキーマ">
CREATE SCHEMA 資料_所蔵品目;
CREATE TABLE 資料_所蔵品目.所蔵品目
(
  所蔵品目番号  int PRIMARY KEY,
  タイトル VARCHAR(100) NOT NULL,
  著者   VARCHAR(40) NOT NULL,
  所蔵品目種別 VARCHAR(10) NOT NULL,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- </editor-fold>

-- <editor-fold desc="資料_所蔵品目スキーマ">
CREATE SCHEMA 資料_所蔵品;
CREATE TABLE 資料_所蔵品.所蔵品
(
  所蔵品番号 VARCHAR(40) PRIMARY KEY,
  所蔵品目番号  int   NOT NULL REFERENCES 資料_所蔵品目.所蔵品目,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE 資料_所蔵品._貸出可能
(
  所蔵品番号 VARCHAR(40) PRIMARY KEY REFERENCES 資料_所蔵品.所蔵品,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE 資料_所蔵品._貸出中
(
  所蔵品番号 VARCHAR(40) PRIMARY KEY REFERENCES 資料_所蔵品.所蔵品,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE 資料_所蔵品._取置中
(
  所蔵品番号 VARCHAR(40) PRIMARY KEY REFERENCES 資料_所蔵品.所蔵品,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- </editor-fold>


-- <editor-fold desc="貸出スキーマ">
CREATE SCHEMA 貸出;
CREATE SEQUENCE 貸出.貸出番号;

CREATE TABLE 貸出.貸出
(
  貸出番号 int PRIMARY KEY,
  所蔵品番号 VARCHAR(40) NOT NULL REFERENCES 資料_所蔵品.所蔵品,
  貸出日  DATE        NOT NULL,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE 貸出.返却
(
  貸出番号 int PRIMARY KEY REFERENCES 貸出.貸出,
  返却日  DATE      NOT NULL,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- </editor-fold>

-- <editor-fold desc="予約スキーマ">
CREATE SCHEMA 予約;
CREATE SEQUENCE 予約.予約連番;

CREATE TABLE 予約.予約
(
  予約番号 int PRIMARY KEY,
  所蔵品目番号  int   NOT NULL REFERENCES 資料_所蔵品目.所蔵品目,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE 予約._未準備
(
  予約番号 int PRIMARY KEY REFERENCES 予約.予約,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE 予約.予約取消
(
  予約番号 int PRIMARY KEY REFERENCES 予約.予約,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- </editor-fold>

-- <editor-fold desc="取置スキーマ">
CREATE SCHEMA 取置;
CREATE SEQUENCE 取置.取置番号;

CREATE TABLE 取置.取置
(
  取置番号 int PRIMARY KEY,
  予約番号 int NOT NULL REFERENCES 予約.予約,
  所蔵品番号 VARCHAR(40) NOT NULL REFERENCES 資料_所蔵品.所蔵品,
  取置日  DATE        NOT NULL,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 準備完了
CREATE TABLE 取置._準備完了
(
  取置番号 int PRIMARY KEY REFERENCES 取置.取置,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 取置を貸し出した記録
CREATE TABLE 取置.取置解放
(
  取置番号 int PRIMARY KEY REFERENCES 取置.取置,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- 取置の期限切れ
CREATE TABLE 取置.取置期限切れ
(
  取置番号 int PRIMARY KEY REFERENCES 取置.取置,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- </editor-fold>


-- <editor-fold desc="会員スキーマ">
CREATE SCHEMA 会員;

CREATE TABLE 会員.会員
(
  会員番号 int PRIMARY KEY,
  氏名   VARCHAR(40) NOT NULL,
  会員種別 VARCHAR(10)  NOT NULL,
  登録日時 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 貸出と会員の関連テーブル
CREATE TABLE 会員._貸出と会員
(
  貸出番号 int     PRIMARY KEY REFERENCES 貸出.貸出,
  会員番号 int     NOT NULL REFERENCES 会員.会員,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 予約と会員の関連テーブル
CREATE TABLE 会員._予約と会員
(
  予約番号 int     PRIMARY KEY REFERENCES 予約.予約,
  会員番号 int     NOT NULL REFERENCES 会員.会員,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 取置と会員の関連テーブル
CREATE TABLE 会員._取置と会員
(
  取置番号 int     PRIMARY KEY REFERENCES 取置.取置,
  会員番号 int     NOT NULL REFERENCES 会員.会員,
  登録日時 DATETIME   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- </editor-fold>
