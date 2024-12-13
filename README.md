# クリーンアーキテクチャを基にした在庫管理アプリケーション

## dev

```
./gradlew bootRun
```

## docgen

doxygen と graphvizがインストールされている必要あり([参考](https://qiita.com/ryotaro76/items/6730c6d8572e713e79b9)).

```
doxygen
```


**注記**
以下の文章はAIによって書かれています。
https://chatgpt.com/share/675ae52b-b1f8-8001-8153-4fe5ed9f41e8

---

このドキュメントでは、本アプリケーションの設計思想、ディレクトリ構成、各層の役割について説明します。

---

## 設計思想

本アプリケーションは、クリーンアーキテクチャの原則に基づいて設計されています。主な目標は以下の通りです。

- **独立性の確保**:
  - ビジネスロジックをフレームワークやデータベース技術に依存させない。
  - ユースケースやドメインロジックが簡単にテスト可能である。
- **責務の分離**:
  - 各層が明確な責務を持ち、それぞれが独立して進化できる。

クリーンアーキテクチャは以下の4つの層で構成されます。

1. **Enterprise Business Rules (Domain層)**: ビジネスロジックやエンティティを定義する。
2. **Application Business Rules (UseCase層)**: ビジネスルールを適用し、ユースケースを実装する。
3. **Interface Adapters**: ユースケースと外部インターフェースを接続する。
4. **Frameworks & Drivers (Infrastructure層)**: 外部フレームワークやデータベースとの連携を行う。

---

## ディレクトリ構成

```plaintext
├── src/main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── cleanarchitecture
│   │               ├── application
│   │               │   └── order
│   │               ├── core
│   │               │   ├── domain
│   │               │   ├── exception
│   │               │   └── transaction
│   │               ├── domain
│   │               │   ├── inventory
│   │               │   │   ├── exception
│   │               │   │   ├── gateway
│   │               │   │   └── model
│   │               │   └── order
│   │               │       ├── gateway
│   │               │       └── model
│   │               ├── infrastructure
│   │               │   ├── config
│   │               │   │   ├── db
│   │               │   │   │   ├── mapper
│   │               │   │   │   ├── repository
│   │               │   │   │   └── schema
│   │               │   │   ├── exception
│   │               │   │   └── web
│   │               │   ├── inventory
│   │               │   │   ├── controller
│   │               │   │   ├── dto
│   │               │   │   ├── gateway
│   │               │   │   └── validation
│   │               │   └── order
│   │               │       ├── controller
│   │               │       ├── dto
│   │               │       └── gateway
│   │               └── usecase
│   │                   ├── inventory
│   │                   │   └── dto
│   │                   └── order
│   │                       └── dto
│   └── resources
│       ├── static
│       └── templates
└── test
    └── java
        └── com
            └── example
                └── cleanarchitecture
```

---

## 各層の役割

### **1. Domain層**
- **責務**:
  - ビジネスロジックやエンティティの定義。
  - 他の層に依存せず、純粋なJavaコードで構成される。
- **例**:
  - `Inventory`エンティティ: 在庫の不変条件（例: 名称が空であってはならない、数量が0以上である）を保持。

```java
public class Inventory {
    private final Long id;
    private final String name;
    private int quantity;

    public Inventory(Long id, String name, int quantity) throws DomainValidationException {
        if (name == null || name.isBlank()) {
            throw new DomainValidationException("Name must not be null or empty.");
        }
        if (quantity < 0) {
            throw new DomainValidationException("Quantity must be greater than or equal to 0.");
        }
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
```

### **2. UseCase層**
- **責務**:
  - ユースケースを実現するビジネスロジックを実装。
  - Gatewayインターフェースを利用してデータアクセスを抽象化。
- **例**:
  - `CreateInventoryUseCase`: 在庫を新規作成する。

```java
public class CreateInventoryUseCase {
    private final InventoryGateway inventoryGateway;

    public CreateInventoryUseCase(InventoryGateway inventoryGateway) {
        this.inventoryGateway = inventoryGateway;
    }

    public Inventory execute(IInventoryRegistrationData data) throws DomainValidationException {
        Inventory inventory = new Inventory(null, data.name(), data.quantity());
        return inventoryGateway.create(inventory);
    }
}
```

### **3. Application層**
- **責務**:
  - トランザクション管理や複数のユースケースの連携。
  - Springの`@Transactional`を使用し、トランザクション境界を定義。
- **例**:
  - `PlaceOrderService`: 注文の作成と在庫の更新をトランザクション内で実行。

```java
@Service
public class CreateOrderService {
    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Transactional
    public Order createOrder(Long inventoryId, IOrderRegistrationData data)
            throws InventoryNotFoundException, InventoryIrreducibleException {
        return createOrderUseCase.createOrder(inventoryId, data);
    }
}
```

### **4. Infrastructure層**
- **責務**:
  - データベースやフレームワークへの依存コードを実装。
  - コントローラー、DTO、リポジトリ実装など。
- **例**:
  - `CreateInventoryController`: HTTPリクエストを処理し、ユースケースを呼び出す。

```java
@RestController
public class CreateInventoryController {
    @Autowired
    private CreateInventoryUseCase createInventoryUseCase;

    @PostMapping("/inventories")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryPublicData createInventory(@Valid @RequestBody InventoryRegistrationData data) {
        return InventoryPublicData.fromInventory(createInventoryUseCase.create(data));
    }
}
```

---

## トランザクション管理

- トランザクション境界は**Application層**で定義し、`@Transactional`を使用します。
- 例: 複数のドメインモデルをまたぐ更新処理をトランザクション内で実行。

---

## 今後の拡張

- **Event-Driven設計**: ドメインイベントを導入し、変更を他のシステムに通知する。
- **認証・認可**: ユースケース層でビジネスロジックに基づく権限管理を追加。
- **マイクロサービス化**: 各ドメインを独立したサービスとして分割可能に。

---

以上が本アプリケーションの設計思想と構成の概要です。

