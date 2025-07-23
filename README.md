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

### 1. 아키텍처 개요: 상태 기반 흐름 제어

애플리케이션은 명확한 **관심사 분리(Separation of Concerns)** 원칙에 따라 여러 계층으로 구성됩니다. 핵심 아키텍처는 **상태 머신(State Machine)**과 유사하게 동작하며, UI와 비즈니스 로직이 철저하게 분리되어 있습니다.

1.  **`service.Kiosk` (중앙 컨트롤러)**: 애플리케이션의 메인 루프를 실행합니다. 현재 상태를 나타내는 `KioskAction` 객체를 관리하며, 이 `Action`에 맞는 `Handler`를 동적으로 찾아 실행합니다.
2.  **`handler` 패키지 (로직 계층)**: 모든 비즈니스 로직과 흐름 제어를 담당합니다. UI로부터 사용자의 선택(`Choice` 객체)을 전달받아, 이를 해석하고 다음에 수행할 `KioskAction`을 결정하여 반환합니다.
3.  **`ui` 패키지 (표현 계층)**: 사용자 인터페이스(CLI)를 전담합니다. 오직 사용자에게 정보를 **보여주고**, 사용자의 입력을 받아 약속된 데이터 객체(`Choice`)로 만들어 **반환**하는 역할만 수행합니다. 비즈니스 로직에 대해서는 전혀 알지 못합니다.
4.  **`model` 패키지 (데이터 계층)**: 애플리케이션의 모든 데이터 구조를 정의합니다. `KioskAction` (상태), `Choice` (선택), `MenuItem` (메뉴) 등 순수한 데이터 객체들이 위치합니다.
5.  **`manager` 패키지 (상태 관리)**: `CartManager`, `MenuManager` 등 장바구니나 메뉴 목록처럼 여러 곳에서 공유되어야 하는 애플리케이션의 상태를 관리합니다.

### 2. 주요 디자인 패턴

| 패턴 | 적용 클래스/인터페이스 | 설명 |
|---|---|---|
| **전략 (Strategy)** | `ActionHandler` 및 구현체 | 각 사용자 액션을 독립적인 `Handler` 객체로 캡슐화하여, `Kiosk` 서비스가 동적으로 액션을 선택하고 실행할 수 있게 합니다. |
| **상태 (State)** | `KioskAction` (Sealed Interface) | 애플리케이션의 현재 상태를 객체로 표현하고, 상태에 따라 행동이 결정되도록 하여 복잡한 흐름을 체계적으로 관리합니다. |
| **DTO (Data Transfer Object)** | `...Choice` (Sealed Records) | UI 계층과 로직 계층 간에 데이터를 전송하기 위해 사용됩니다. `record`를 사용하여 불변의 순수 데이터 객체를 정의합니다. |
| **팩토리 (Factory)** | `UIFactory` | 다양한 UI 컴포넌트들을 생성하는 책임을 한 곳에 집중시켜, 클라이언트 코드(`Handler`)와 UI 구현체의 결합도를 낮춥니다. |
| **빌더 (Builder)** | `MenuItem.Builder` | `MenuItem`처럼 여러 속성을 가진 복잡한 객체를 단계적으로 생성하여 가독성과 유연성을 높입니다. |

---

## 📂 프로젝트 구조

코드베이스는 역할과 책임에 따라 명확하게 분리되어 있어, 탐색과 확장이 용이하도록 설계되었습니다.

- `kiosk/`: 애플리케이션의 루트 패키지입니다.
  - `App.java`: 🏁 **애플리케이션 진입점**. 의존성(매니저, UI 팩토리 등)을 설정하고 `Kiosk` 서비스를 실행합니다.
  - `service/`: ⚙️ **핵심 서비스 계층**.
    - `Kiosk.java`: **상태 머신** 역할을 하며, 현재 `KioskAction`에 따라 적절한 핸들러를 호출하여 전체 애플리케이션 흐름을 제어합니다.
  - `handler/`: 🔄 **비즈니스 로직 및 흐름 제어 계층**.
    - 각 `Handler`는 `ActionHandler` 인터페이스를 구현하며, 특정 `KioskAction`에 대한 처리를 담당합니다.
    - UI로부터 `Choice` 객체를 받아, 다음에 수행할 `KioskAction`을 결정하고 반환합니다.
    - `HandlerDependencies.java`: 모든 핸들러가 공유하는 의존성(UI 팩토리, 매니저 등)을 묶어서 전달하기 위한 `record`입니다.
  - `ui/`: 🖥️ **사용자 인터페이스(표현) 계층**.
    - `UIFactory.java`: UI 컴포넌트 생성을 전담하는 팩토리 클래스입니다.
    - `choice/`: **양방향 UI**. 사용자에게 선택지를 보여주고 입력을 받아, 그 결과를 `Choice` 데이터 객체로 반환합니다. (e.g., `MainMenuUI`)
    - `display/`: **단방향 UI**. 사용자에게 특정 정보를 화면에 보여주기만 합니다. (e.g., `CompleteOrderUI`)
    - `common/`: `Displayable` 등 UI에서 공통으로 사용되는 인터페이스를 정의합니다.
  - `model/`: 📦 **데이터 모델 계층**.
    - `action/`: 시스템의 모든 가능한 **상태(State)**를 정의하는 `KioskAction` `sealed interface`와 구현체 `record`들이 위치합니다.
    - `choice/`: UI와 Handler 간에 주고받는 **데이터(DTO)**인 `Choice` `sealed interface`와 구현체 `record`들이 위치합니다.
    - `MenuItem.java`: 메뉴 항목을 나타내는 핵심 데이터 `record`입니다. (Builder Pattern 적용)
  - `manager/`: 🧠 **상태 관리 계층**.
    - `MenuManager`, `CartManager` 등 애플리케이션 전역에서 사용되는 상태를 관리합니다.
  - `category/`: 🏷️ 메뉴(`MenuCategory`) 및 할인(`SaleCategory`)과 같이 분류에 사용되는 `enum`을 정의합니다.
  - `exception/`: ❗ `InvalidInputException`, `RidiculousException` 등 사용자 정의 예외를 정의합니다.
  - `util/`: 🛠️ **유틸리티 계층**.
    - `ScannerProvider.java`, `IntScanner.java`: 사용자 입력을 안전하고 편리하게 받기 위한 클래스입니다.
    - `validator/`: `XToYFilter` 등 입력값의 유효성을 검증하는 필터 클래스들입니다.