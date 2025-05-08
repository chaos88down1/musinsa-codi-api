1. 구현 기능
  
1-1. 카테고리별 최저가 브랜드 및 가격, 총액 조회
- 'GET /api/products/lowest-price-per-category'

1-2. 단일 브랜드로 전체 상품 구매 시 최저가 브랜드 조회
- 모든 카테고리 상품을 가지고 있는 브랜드만 체크해서 최저가 계산
- 'GET /api/products/lowest-total-brand'

1-3. 특정 카테고리의 최저가 / 최고가 브랜드 조회
- 카테고리명을 한글로 입력
- 'GET /api/products/min-max-price-by-category?category=상의'

1-4. 브랜드 및 상품 추가 / 수정 / 삭제
- REQUEST BODY 는 JSON 이라 가정함
- 해당 프로젝트에서는 추가 및 수정을 하나의 api 로 작성함

- 만약 api 분리할 경우
- 장점: api 목적이 명확해짐. 보안 정책 관리 용이함(생성/수정 권한을 별도로 지정) 등
- 단점: 클라이언트가 상황에 맞게 호출 필요. 여러 잘못된 요청에 대해 명시적 대응 필요

- 현업에서 장기적 관점으로는 분리해서 작성하는게 유지보수 측면에서 좋음

- 브랜드 추가 및 수정: 'POST /api/brands/upsert' {"oldBrandName": "ZZ","newBrandName": "ZZZZ"}
- oldBrandName 는 optional (없으면 생성, 있으면 수정)

- 브랜드 삭제: 'DELETE /api/brands' {"brandName": "ZZZZ"}
- 브랜드 삭제시 매핑되어 있는 모든 상품도 삭제하도록 구성 (상황에 따라 불필요)

- 상품 추가 및 수정: 'POST /api/products/upsert' {"brandName": "ZZZZ","categoryName": "양말","price": 1700}
- 브랜드가 없는 경우 error 처리

- 상품 삭제: 'DELETE /api/brands' {"brandName": "ZZZZ", "categoryName": "양말"}



2. 실행 방법
2-1. 빌드 및 실행 (Java 17 필요)
- ./gradlew bootRun

2-2. H2 DB 콘솔 접속
- URL: 'http://localhost:8080/h2-console'
- JDBC URL: 'jdbc:h2:mem:testdb'
- Username: 'sa'
- Password: (null)



3. 테스트 방법
- ./gradlew test



4. 기술 스택
- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Gradle
- Lombok
- JUnit 5 + Mockito



5. 프로젝트 구조
src
 ├── main
 │    ├── java/com/musinsa
 │    │     ├── controller
 │    │     ├── domain
 │    │     ├── dto
 │    │     ├── repository
 │    │     ├── service
 │    │     └── MusinsaApplication.java
 │    └── resources
 │          ├── application.properties
 │          └── data.sql
 └── test
      └── java/com/musinsa
            ├── controller
            └── service
