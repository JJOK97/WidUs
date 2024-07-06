
## 📺 Preview


###### *이미지를 클릭하면 유튜브로 이동합니다.*

### [![Youtube Preview](https://github.com/pmk1111/Widus/assets/133585698/348c205d-a191-45cf-8d6b-1ac752de5825)](https://youtu.be/crBx-FLHPOk)

<br>

## 목차

##### 1. [개발 목적](#-개발-목적)
##### 2. [WidUs 주요 기능](#-widus-주요-기능)
##### 3. [주요 기술 스택](#-주요-기술-스택)
##### 4. [주요 작업](#-주요-작업)
##### 5. [테스트](#-테스트)
##### 6. [리팩토링](#-리팩토링)
##### 7. [기타 사항](#-기타-사항)

<br>

## 📖 개발 목적

Widus 프로젝트는 JIRA와 Flow와 같은 협업 툴을 벤치마킹하여 회사 단위의 대규모 프로젝트 관리를 가능하게 하는 ERP & 협업 툴을 구현하는 것을 목표로 합니다.

각 회사별 프로젝트 및 직원 관리, 이슈 트래킹이 가능한 웹사이트를 개발하는 것이 주요 목적입니다.

<br>

## 🚀 WidUs 주요 기능

### WidUs 주요 기능

- **프로젝트 관리**: 프로젝트 생성, 수정, 삭제 및 조회 기능.
- **직원 관리**: 직원 정보 관리 및 역할 배정 기능.
- **이슈 트래킹**: 프로젝트 이슈 생성 및 트래킹 기능.
- **대시보드**: 프로젝트와 이슈 현황을 한눈에 볼 수 있는 대시보드 제공.
- **실시간 협업**: 실시간 업데이트, 알림, 채팅 기능 제공.

<br>

### 비즈니스 로직

- 프로젝트와 직원, 이슈 간의 관계 관리.
- 실시간 알림 및 업데이트 기능 구현.

<br>

## 📚 주요 기술 스택

- **설계 패턴**: <img src="https://img.shields.io/badge/SpringBoot%20MVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
- **프로그래밍 언어**: <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
- **프레임워크**: <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
- **웹 화면**: <img src="https://img.shields.io/badge/jsp-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/ajax-2E77BC?style=for-the-badge&logo=ajax&logoColor=white">
- **데이터베이스**: <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/mybatis-000000?style=for-the-badge&logo=mybatis&logoColor=white">
- **보안**: <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/jwt-000000?style=for-the-badge&logo=jwt&logoColor=white">
<br>

### 기타 도구

- **CI/CD**: <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
- **버전 관리 시스템**: <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
- **클라우드 서비스**: <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<br>

## 🔧 주요 작업

### 백엔드

#### 1. 서버 사이드 로직 구현

> Spring Framework와 JSP를 활용하여 전통적인 MVC 아키텍처로 복잡한 비즈니스 로직을 효율적으로 구현

1. **복잡한 로직 처리**: 대규모 프로젝트와 이슈 관리에서 발생하는 복잡한 로직을 처리하기 위해, Spring Framework의 **트랜잭션 기능을 활용하여 데이터 일관성을 유지**

    - **개발 방법**: Spring의 @Transactional 어노테이션을 사용하여 트랜잭션 경계를 설정하고, 트랜잭션이 성공적으로 완료될 때까지 데이터베이스 변경 사항을 보류하여 데이터 일관성을 보장

2. **효율적인 데이터 처리**: MyBatis를 사용하여 데이터베이스와의 효율적인 연동을 구현

    - **개발 방법**: SQL 매퍼 파일을 활용하여 복잡한 쿼리를 분리하고, **MyBatis의 캐싱 기능을 사용**하여 반복적인 데이터베이스 호출을 최소화

**구체적인 성과**

- **성능 향상**: JMeter를 사용하여 성능 최적화 전후의 쿼리 실행 시간을 측정하고 비교한 결과, 쿼리 최적화와 캐싱작업을 통해 **데이터베이스 응답을 30% 이상 단축**
- **안정성 확보**: 트랜잭션 관리를 통해 데이터베이스의 무결성을 유지하며, **다중 사용자 환경에서도 안정적인 성능을 보장**

<br>

#### 2. 데이터베이스 관리

> Oracle DB와 MyBatis를 사용하여 대용량 데이터의 효율적인 처리와 보안을 보장

1. **데이터 모델링**: 프로젝트의 전반적인 데이터베이스 스키마 설계와 ERD 작성

    - **개발 방법**: **정규화** 과정을 통해 데이터 중복을 최소화하고, 인덱스를 설정하여 검색 성능을 향상

2. **정규화 및 최적화**: 데이터베이스 정규화를 통해 데이터의 중복을 최소화하고, 인덱스 최적화를 통해 쿼리 성능을 향상

    - **개발 방법**: **AWR 리포트를 활용**하여 쿼리 성능을 분석하고, 필요한 곳에 복합 인덱스를 추가하여 인덱스 추가 전후의 쿼리 응답 시간을 비교함으로써 데이터 검색 속도를 높임

**구체적인 성과**

- **데이터 정합성 유지**: 정규화된 데이터베이스 구조를 통해 **데이터의 정합성을 유지**
- **응답 시간 단축**: 쿼리 성능 테스트를 통해 인덱스 최적화를 적용한 전후의 응답 시간을 측정하여, **쿼리 응답 시간을 20% 이상 단축**

<br>

#### 3. 보안 구현

> Spring Security와 JWT를 사용하여 사용자 인증 및 권한 부여를 구현

1. **인증 및 권한 관리**: 애플리케이션의 **보안을 강화하기 위해 Spring Security를 사용**하여 인증 및 권한 관리를 구현

    - **개발 방법**: Widus의 보안 수준을 높이기 위해 JWT를 사용하여 사용자 세션을 관리하고, Spring Security와 통합하여 인증 및 권한 부여를 처리

**구체적인 성과**

- **보안 강화**: 인증 관련 보안 사고를 예방하기 위해 JWT 기반의 인증 시스템을 도입하여 세션 관리를 효율화하고, **사용자 데이터를 안전하게 보호**

<br>

#### 4. 롬복 사용

Lombok을 활용하여 Getter, Setter, ToString 등의 메서드를 자동 생성함으로써 **코드의 가독성을 높이고 유지보수를 용이하게 개발**

<br>

### 프론트엔드

1. **사용자 인터페이스 설계**: 메인이 되는 주요 화면들을 **JSP, HTML, CSS**를 활용하여 직관적이고 사용자 친화적인 UX/UI를 갖춘 웹 인터페이스로 개발
2. **동적 기능 구현**: JavaScript와 **jQuery / Ajax** 등을 사용하여 실시간 데이터 업데이트 및 사용자 인터랙션을 구현
3. **차트 및 그래프 구현**: **Chart.js**를 사용하여 프로젝트 진행 상황과 이슈 현황을 시각적으로 표시

<br>

## 🔄 리팩토링

### 리팩토링

1. **코드 정리 및 최적화**: 불필요한 코드와 중복된 로직을 제거하고, **성능 향상을 위해 일부 쿼리의 알고리즘을 최적화**

    - **구체적인 성과**
        - **코드 가독성 향상**: 불필요한 코드와 중복 로직을 제거함으로써 코드의 가독성과 유지보수성을 크게 향상
        - **성능 최적화**: 알고리즘을 최적화하여 코드 실행 성능을 개선

2. **모듈화**: 코드의 모듈화를 통해 유지보수성을 높이고, **재사용 가능한 컴포넌트를 분리하여 개발 효율성을 향상**

    - **구체적인 성과**
        - **유지보수성 향상**: 모듈화를 통해 코드의 재사용성을 높이고, 유지보수성을 향상
        - **개발 효율성 증대**: 재사용 가능한 컴포넌트를 분리함으로써 개발 효율성을 높임

<br>

### 프로젝트 종료 후 관리

1. **문서화**: 프로젝트의 주요 기능과 사용법을 문서화하여 신규 사용자와 개발자가 쉽게 이해할 수 있도록 문서화

    - **구체적인 성과**
        - **사용자 만족도 향상**: 상세한 문서화를 통해 사용자의 이해도를 높이고 공감을 이끌어 내 **프로젝트 경진대회에서 최우수 성적**을 받음
     
<br>

## 👥 기타 사항

### 협업 경험

- 팀원들과의 원활한 소통과 협업을 위해 Agile 방법론을 적용하여 주간 스프린트 회의를 진행하였고, 지속적인 코드 리뷰를 통해 코드 품질을 유지함

    - **스프린트 회의**: Github, JIRA, Notion 등을 사용하여 백로그를 작성하고, 매 주 월요일 스프린트 플래닝, 리뷰, 회고를 진행하여 프로젝트를 체계적으로 관리
<br>

### 버전 관리

- Git을 사용하여 버전 관리를 수행하였으며, 주요 기능 개발 시 브랜치 전략을 적용하여 안정적인 코드 관리를 유지

    ![Git Branch](https://github.com/pmk1111/Widus/assets/133585698/b04a3094-d97b-4b6c-991e-5f649e4afee3)

    **안정적인 브랜치 관리**: 체계적인 브랜치 관리를 위해 Git Flow 방식을 활용, release 브랜치를 활용하여 안정성과 품질을 동시에 확보하는 개발 프로세스를 구현
