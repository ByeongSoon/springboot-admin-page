springboot-admin-page
=====================

### Description

'SpringBoot Project - Admin Page 만들기' 학습 소스코드를 위한 저장소

회사에 입사를 한 신입 개발자들에게 미션으로 많이 주어지는 서비스에 대한 Admin Page를 만들어본다.

Admin Page 프로젝트를 진행하다보면 해당 서비스에 대한 전체적인 구조와 Entity나 domain을 예측할 수 있기때문에 첫 시작으로 아주 좋은 프로젝트 같다.

해당 프로젝트는 쇼핑몰에 관련된 Admin Page 이다.

###DataBase 

```
docker run -d --name admindb -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=adminpage -p 3306:3306 mysql:5.7
```

###build

```
빌드 방법 설명...
```

##study

###Spring Boot Project 생성/진행 과정 

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