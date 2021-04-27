# Oiyokan DemoSite

This is a demo site for Oiyokan.

Oiyokan is an OData v4 server (provider) SDK for RDB.
You can use Oiyokan to turn RDBMS into OData v4 services.

- Based on Apache Olingo. Build with Spring Boot, Java, h2.
- Oiyokan uses JDBC to provide OData v4 access to the RDB.
- Source code at github, license : Apache License.

## Supported target RDBMS

- h2 database (1.4)
- PostgreSQL (13)
- MySQL (8)
- SQL Server (2008)
- Oracle XE (18c)

## Supported OData Method

- GET
- POST
- PATCH
- DELETE

## Supported OData system query options

- $select
- $count
- $filter
- $orderby
- $top
- $skip

## Sample implementation using Oiyokan

- Oiyokan provides OData server sample of Sakila DVD rental.

# Try the Oiyokan OData v4 sample server

## Heroku

You can find the running OData v4 sample server at Heroku.

- https://oiyokan.herokuapp.com/

## Local

You can try OData v4 sample server at your computer.

Check out source code repository and you can run it as Spring Boot Web Server.

```sh
mvn clean install spring-boot:run
```

# How to setup OData v4 setting

## oiyokan-settings.json の設定を更新

- 最も重要なのは Oiyokan の設定ファイルを変更して、接続したいデータベース情報を記述することです。
- より詳しい内容はソースコードから読み込むことにより得られます。

```sh
src/main/resources/oiyokan/oiyokan-settings.json
```

## 設定変更後は Spring Boot を再起動

Spring Boot を再起動することにより設定情報の更新を反映。

# Oiyokan 関連情報

## Oiyokan 関連リポジトリ 

- [Oiyokan 本体ライブラリ - github](https://github.com/igapyon/oiyokan)
- [Oiyokan デモサイト - github](https://github.com/igapyon/oiyokan-demosite)
- [Oiyokan デモサイトのテスト - github](https://github.com/igapyon/oiyokan-demosite-test)

## OData v4 server のサンプル(simple-odata4) を祖先

oiyokan プロジェクトは、OData v4 server のシンプルなサンプル(https://github.com/igapyon/simple-odata4) を祖先に作成されたものです。

## Oiyokanに関連するその他情報

### Olingo による OData v4 server チュートリアル

OData v4 / Apache Olingo そのものの学習には Apache Olingo サイトの参照が有益です。

- https://olingo.apache.org/doc/odata4/index.html

### 参考: h2情報

- http://www.h2database.com/html/functions.html
