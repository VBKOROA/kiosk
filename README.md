# 🍔 ShakeShack Kiosk Simulator 🍔

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Gradle](https://img.shields.io/badge/Gradle-8.8-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org)
[![Made with Love](https://img.shields.io/badge/Made%20with-Love-ff69b4.svg?style=for-the-badge)](https://github.com/VBKOROA/kiosk.git)

> 🖥️ **ShakeShack 매장의 주문 경험을 시뮬레이션하는 모던한 CLI 기반 키오스크 애플리케이션입니다.**
>
> 이 프로젝트는 깔끔한 아키텍처, 최신 Java 기능, 그리고 사용자 친화적인 커맨드 라인 인터페이스를 통해 원활한 주문 프로세스를 구현합니다.

---

## ✨ 주요 기능

*   **📜 동적 메뉴 시스템**: 버거, 음료, 사이드 등 다양한 카테고리의 메뉴를 동적으로 불러와 탐색합니다.
*   **🛒 스마트 장바구니**: 원하는 메뉴를 장바구니에 추가하고, 실시간으로 합계 금액을 확인하며, 특정 항목이나 전체를 쉽게 비울 수 있습니다.
*   **💰 유연한 할인 옵션**: 결제 시 국가유공자, 학생 등 다양한 유형의 할인을 적용하여 실시간으로 계산된 최종 금액을 확인할 수 있습니다.
*   **🛡️ 안정적인 입력 처리**: 사용자의 입력을 검증하여 오류를 방지하고, 원활한 상호작용을 유도합니다.
*   **✅ 주문 확인 및 취소**: 주문을 확정하기 전 명확한 확인 단계를 거치며, 전체 프로세스를 취소할 수 있는 옵션을 제공합니다.

---

## 🚀 시작하기

다음 단계를 따라 로컬 컴퓨터에서 키오스크를 실행해 보세요.

### 사전 요구사항

- Java 17 이상
- Git

### 설치 및 실행

1.  **리포지토리 클론:**
    ```sh
    git clone https://github.com/VBKOROA/kiosk.git
    cd kiosk
    ```

2.  **Gradle로 애플리케이션 실행:**
    포함된 Gradle 래퍼(`gradlew`)가 모든 작업을 처리해 줍니다.

    *macOS/Linux:*
    ```sh
    ./gradlew run
    ```

    *Windows:*
    ```sh
    ./gradlew.bat run
    ```

---

## 🛠️ 기술 스택

| 구분 | 기술 | 설명 |
|---|---|---|
| **언어** | Java 17 | `sealed interface`, `record` 등 모던 Java 기능을 활용하여 안정성과 표현력을 높였습니다. |
| **빌드 도구** | Gradle | 의존성 관리 및 프로젝트 빌드를 자동화합니다. |
| **핵심 API** | Stream API, BigDecimal | 함수형 스타일의 데이터 처리와 정확한 소수점 연산을 위해 사용됩니다. |

## 🏛️ 아키텍처 및 디자인 패턴

이 프로젝트는 역할과 책임에 따라 코드를 분리하고, 디자인 패턴을 적용하여 유연하고 확장 가능한 구조를 지향합니다.

### 1. 아키텍처 개요

애플리케이션은 명확한 관심사 분리(Separation of Concerns) 원칙에 따라 여러 계층으로 구성됩니다.

-   **`service.Kiosk`**: 애플리케이션의 메인 로직을 관장하는 중앙 컨트롤러입니다. 사용자의 상태(`KioskAction`)를 기반으로 적절한 `Handler`를 호출하며, 전체 흐름을 관리하는 **상태 머신(State Machine)** 역할을 합니다.
-   **`handler` 패키지**: 사용자의 각기 다른 액션(메인 메뉴, 주문, 취소 등)을 처리하는 구체적인 로직을 캡슐화합니다. **(Strategy Pattern)**
-   **`ui` 패키지**: 사용자 인터페이스(CLI)를 담당하며, 사용자에게 정보를 표시하고 입력을 받습니다. `KioskUI`는 UI 관련 복잡성을 숨기는 **파사드(Facade)** 역할을 합니다.
-   **`manager` 패키지**: `MenuManager`, `CartManager` 등 핵심 데이터(메뉴, 장바구니)와 관련된 상태 및 비즈니스 로직을 관리합니다.
-   **`model` 패키지**: `MenuItem`, `KioskAction` 등 애플리케이션의 핵심 데이터 구조를 정의합니다.

### 2. 주요 디자인 패턴

| 패턴 | 적용 클래스/인터페이스 | 설명 |
|---|---|---|
| **전략 (Strategy)** | `ActionHandler` 및 구현체 | 각 사용자 액션을 독립적인 `Handler` 객체로 캡슐화하여, `Kiosk` 서비스가 동적으로 액션을 선택하고 실행할 수 있게 합니다. |
| **상태 (State)** | `KioskAction` (Sealed Interface) | 애플리케이션의 현재 상태를 객체로 표현하고, 상태에 따라 행동이 결정되도록 하여 복잡한 흐름을 체계적으로 관리합니다. |
| **팩토리 메서드 (Factory Method)** | `*.withParameter(...)` | 객체 생성 로직을 별도의 정적 메서드로 캡슐화하여, 생성자의 복잡성을 숨기고 일관된 방식으로 객체를 생성합니다. |
| **빌더 (Builder)** | `MenuItem.Builder` | `MenuItem`처럼 여러 속성을 가진 복잡한 객체를 단계적으로 생성하여 가독성과 유연성을 높입니다. |
| **파사드 (Facade)** | `KioskUI` | 다양한 UI 컴포넌트들을 감싸고 단순화된 인터페이스를 제공하여, 서비스 계층과의 결합도를 낮춥니다. |
| **DTO (Data Transfer Object)** | `*ParameterDto` (Records) | 계층 간 데이터 전송을 위해 `record`를 사용하여 불변의 데이터 객체를 정의하고, 파라미터를 체계적으로 관리합니다. |
| **템플릿 메서드 (Template Method)** | `AbstractChoiceable` | UI 로직의 공통적인 흐름(템플릿)은 슈퍼클래스에 정의하고, 구체적인 내용만 서브클래스에서 구현하도록 하여 코드 중복을 줄입니다. |

---

## 📂 프로젝트 구조

코드베이스는 역할과 책임에 따라 명확하게 분리되어 있어, 탐색과 확장이 용이하도록 설계되었습니다.

```
kiosk/
├── App.java                # 🏁 애플리케이션의 메인 진입점. 의존성 설정 및 주입 담당.
├── category/               # 🏷️ 메뉴(MenuCategory) 및 할인(SaleCategory) 종류를 정의하는 Enum.
├── exception/              # ❗ 사용자 정의 예외 클래스 (InvalidInputException, RidiculousException).
├── handler/                # 🔄 각 사용자 액션(상태)을 처리하는 핸들러 클래스. (Strategy Pattern)
│   └── HandlerFactory.java #   - KioskAction에 따라 적절한 핸들러를 생성하는 팩토리.
├── manager/                # 🧠 핵심 데이터와 비즈니스 로직을 관리하는 클래스.
│   ├── CartManager.java    #   - 장바구니 상태 관리 (추가, 삭제, 총액 계산).
│   └── MenuManager.java    #   - 전체 메뉴 아이템 관리.
├── model/                  # 📦 애플리케이션의 데이터 모델.
│   ├── KioskAction.java    #   - 시스템의 모든 상태를 정의하는 sealed interface. (State Pattern)
│   ├── MenuItem.java       #   - 메뉴 아이템을 정의하는 record. (Builder Pattern)
│   └── choice/             #   - 각 UI에서 사용자의 선택 결과를 나타내는 sealed interface.
├── service/                # ⚙️ 메인 비즈니스 로직.
│   └── Kiosk.java          #   - 상태 머신 역할을 하는 키오스크의 메인 실행 루프.
├── ui/                     # 🖥️ 사용자 인터페이스(CLI) 관련 클래스.
│   ├── KioskUI.java        #   - 🎭 UI 컴포넌트들을 모아 외부에 단일 창구를 제공하는 파사드(Facade).
│   ├── choice/             #   - ⌨️ 사용자에게 선택지를 제공하고 입력을 받는 UI.
│   ├── display/            #   - 📢 단순 정보를 화면에 출력하는 UI.
│   └── common/             #   - 📜 공통 UI 인터페이스 (Displayable, Choiceable).
└── util/                   # 🛠️ 유틸리티 클래스.
    ├── IntScanner.java     #   - 필터링 기능이 포함된 정수 입력 스캐너.
    └── validator/          #   - 입력값 검증 로직.
```