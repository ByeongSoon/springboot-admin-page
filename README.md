springboot-admin-page
=====================

### Description

'SpringBoot Project - Admin Page 만들기' 학습 소스코드를 위한 저장소

회사에 입사를 한 신입 개발자들에게 미션으로 많이 주어지는 서비스에 대한 Admin Page를 만들어본다.

Admin Page 프로젝트를 진행하다보면 해당 서비스에 대한 전체적인 구조와 Entity나 domain을 예측할 수 있기때문에 첫 시작으로 아주 좋은 프로젝트 같다.

해당 프로젝트는 쇼핑몰에 관련된 Admin Page 이다.

### DataBase 

```
docker run -d --name admindb -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=adminpage -p 3306:3306 mysql:5.7
```

### build

```
빌드 방법 설명...
```

## study

### Spring Boot Project 생성/진행 과정 

1. ERD 설계
2. Table 생성
3. Entity 생성
4. Repository 생성
5. Repository Test
6. 연관관계 설정
7. 필요한 Query Method 생성(나중에 코딩하면서 추가해도 됨)
8. request/response 시 사용할 클래스 생성(model/network/*)
9. Controller 생성
10. Service Logic 생성

## 프로젝트 후기

Spring 공부만 하다가 Spring Boot를 통해서 빠르게 개발을 시작할 수 있는 여러 기능들을 학습했고,

백엔드에서 자주 사용하는 구조인 Controller, Repository, Entity, Service를 패키지를 이용해 분할하여 프로젝트 구조의 가독성을 높일 수 있었다.

역시나 중요한 테스트 코드 작성에 대해서도 Junit5를 사용해볼 수 있는 경험이 되었는데 아무리 바쁘더라도 Repository가 정상적으로 연결되고 동작하는지 CRUD 정도의 테스트는 진행해야겠다.

마지막으로 리팩토링 과정은 사실 처음해봤는데 중복되는 코드들 위주로 추상화하면서 효율적으로 코드를 변경한다는 점에서 아주 매력적이었다.
