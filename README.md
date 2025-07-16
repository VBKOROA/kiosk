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
*   **💰 유연한 할인 옵션**: ��제 시 국가유공자, 학생 등 다양한 유형의 할인을 적용하여 실시간으로 계산된 최종 금액을 확인할 수 있습니다.
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

## 🛠️ 기술 스택 및 아키텍처

이 프로젝트는 현대적이고 깔끔하며 유지보수 가능한 코드를 지향합니다.

| 카테고리 | 기술 / 패턴 |
|---|---|
| **언어** | ![Java](https://img.shields.io/badge/Java%2017-ED8B00?style=flat&logo=openjdk) |
| **빌드 도구**| ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle) |
| **핵심 Java** | `records` (불변 데이터 모델), `Stream API` (데이터 처리), `BigDecimal` (정확한 금융 계산) |
| **디자인** | **빌더 패턴** (`MenuItem`의 유연한 객체 생성) |
| **구조** | **관심사 분리**: 로직을 `UI`, `Service`, `Managers`, `Models`로 분리하여 응집도 향상 |
| **입력 처리** | **함수형 인터페이스** (`ValidationFilter`)를 통한 재사용 가능한 선언적 입력 검증 |

---

## 📂 프로젝트 구조

코드베이스는 탐색과 확장이 용이하도록 논리적으로 구성되었습니다.

```
kiosk/
├── App.java                # 🏁 애플리케이션의 메인 진입점
├── enums/                  # 🏷️ 카테고리 Enum (Menu, Sale)
├── managers/               # 🧠 핵심 데이터 관리자 (Cart, Menu)
├── models/                 # 📦 데이터 모델 (MenuItem as a Record)
├── service/                # ⚙️ 메인 비즈니스 로직 (Kiosk service)
├── ui/                     # 🖥️ 사용자 인터페이스 처리 (KioskUI)
└── utils/                  # 🛠️ 유틸리티 클래스 (IntScanner)
```