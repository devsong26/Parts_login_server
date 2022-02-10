# 로그인 서버
+ 로그인 처리를 공부하기 위해 만든 프로젝트입니다.  
  
----
### 스펙
+ Spring Boot: 2.4.12
+ spring-boot-starter-security: 2.4.12
+ spring-boot-starter-web: 2.4.12
+ spring-boot-starter-data-redis: 2.4.12
+ spring-session-data-redis: 2.4.12
+ mybatis-spring-boot-starter:2.1.4

----

### 기능
+ 로그인 성공, 실패 시 JSON 데이터 반환
+ 로그인 성공 시 레디스에 세션 저장

----

### 추가해야 할 기능
+ 로그인 이력 저장
+ 헤더 정보로 로그인 사용자의 디바이스 체크 / 해킹 유무 확인

