# Spring Boot + Doma2 サンプルプロジェクト

Spring BootでDoma2を使用したCRUD操作のサンプルプロジェクト

## 使用技術

- Spring Boot 3.2.1
- Doma2 2.58.0
- MySQL 8.0
- Docker Compose

## 起動方法

### 1. MySQLコンテナの起動

```bash
cd spring-boot-doma2-sample
docker-compose up -d
```

### 2. アプリケーションの起動

```bash
./gradlew bootRun  
```

## API

### 全ユーザー取得
```bash
curl [http://localhost:8080/api/users](http://localhost:8080/api/users/with-emails)
```


## プロジェクト構成

```
spring-boot-doma2-sample/
├── docker-compose.yml          # MySQL設定
├── init.sql                    # データベース初期化SQL
├── pom.xml                     # Maven設定
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java      # メインクラス
│   │   │   ├── entity/
│   │   │   │   └── User.java             # エンティティ
│   │   │   ├── dao/
│   │   │   │   └── UserDao.java          # DAOインターフェース
│   │   │   └── controller/
│   │   │       └── UserController.java  # REST API
│   │   └── resources/
│   │       ├── application.properties    # アプリケーション設定
│   │       └── META-INF/com/example/demo/dao/UserDao/
│   │           ├── selectAll.sql         # 全件取得SQL
│   │           ├── selectById.sql        # ID指定取得SQL
│   │           └── selectByEmail.sql     # Email指定取得SQL
│   └── test/
└── README.md
```

## 注意事項

- Doma2では、SQLファイルをMETA-INF以下の特定パスに配置する必要があります
- エンティティクラスのフィールドは`public`にする必要があります
- DAOインターフェースには`@ConfigAutowireable`アノテーションが必要です
