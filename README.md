# Shop Project Api

#### 쇼핑몰 회원가입 상품등록, 장바구니 결제에대한 api입니다

# 사용기술
 - 사용언어 : JAVA 11
 - 데이터베이스 : Maria DB
 - 프레임워크 : Spring boot 2.7.10
 - 기타기술 : Jpa, Lombok, Spring Security, Swagger 3.0.0
 

## USER
### GET /api/user/{id}
사용자의 고유식별번호를 받아 해당 사용자의 정보를 조회하는 GET 요청의 대한 응답



### GET /api/user/admin/{userid}
관리자가 사용자아이디를 받아 해당 사용자의 정보를 조회하는 응답<br>
curl -X GET "http://localhost:8080/api/user/admin/test" -H "accept: */*" -H "authorization: basic token"



### GET /api/user/admin/getAllUser
모든 유저의 정보를 조회하는 응답<br>
curl -X GET "http://localhost:8080/api/user/admin/getAllUser?authenticated=true&credentials=%7B%7D&details=%7B%7D&page=0&principal=%7B%7D&size=10" -H "accept: */*" -H "authorization: basic token"

### GET /api/user/current
현재 접속중인 유저의 정보를 조회하는 GET 요청의 대한 응답<br>
curl -X GET "http://localhost:8080/api/user/current?authenticated=true&credentials=%7B%7D&details=%7B%7D&principal=%7B%7D" -H "accept: */*" -H "authorization: Basic token"



### POST /api/user/signup
회원가입 하는 요청<br>
{<br>
  "address": "Testuser",<br>
  "email": "TestEmail@test.com",<br>
  "password": "1111",<br>
  "phone": "111-222-3333",<br>
  "userid": "test55",<br>
  "username": "testname"<br>
}<br>

curl -X POST "http://localhost:8080/api/user/signup?password2=1234" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"address\": \"Testuser\", \"email\": \"TestEmail@test.com\", \"password\": \"1234\", \"phone\": \"111-222-3333\", \"userid\": \"test55\", \"username\": \"testname\"}"<br>

요청과 사용자가 등록되었다는 response body 를 받을수있다


### POST /api/user/login
로그인 하는 요청 Basic 인증키 발급
{<br>
  "password": "1234",<br>
  "userid": "test"<br>
}<br>
curl -X POST "http://localhost:8080/api/user/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"1234\", \"userid\": \"test\"}"
<br>
http://localhost:8080/api/user/login<br>

authorization: BasiceW91cl91c2VyaWQ6eW91cl9wYXNzd29yZA== <br>
basic 코드를 받을수있다<br>

### PUT /api/user/update/{id}
현재 사용하고 있는 사람이 직접 회원정보를 수정할수있는 요청


### PUT /api/user/admin/update/{id}
관리자가 임의로 다른 정보를 수정하는 요청

### PUT /api/user/admin/authority/{id}
관리자가 회원정보에서 ROLE_USER 권한을 ROLE_ADMIN으로 상승시키는 요청<br><br>
curl -X PUT "http://localhost:8080/api/user/admin/authority/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"address\": \"string\", \"cart\": { \"id\": 0, \"products\": [ { \"category\": { \"id\": 0, \"name\": \"string\" }, \"company\": \"string\", \"detail\": \"string\", \"end\": \"2023-04-22T19:01:42.489Z\", \"id\": 0, \"image\": \"string\", \"name\": \"string\", \"price\": 0, \"start\": \"2023-04-22T19:01:42.489Z\", \"stock\": 0 } ] }, \"createTime\": \"2023-04-22T19:01:42.489Z\", \"email\": \"string\", \"id\": 0, \"orderList\": [ { \"amount\": 0, \"deliveryCompl\": \"2023-04-22T19:01:42.489Z\", \"deliveryStart\": \"2023-04-22T19:01:42.489Z\", \"id\": 0, \"orderTime\": \"2023-04-22T19:01:42.489Z\", \"trackingNumber\": \"string\" } ], \"password\": \"string\", \"phone\": \"string\", \"roles\": \"string\", \"userid\": \"string\", \"username\": \"string\"}"<br><br>

Request URL<br>
http://localhost:8080/api/user/admin/authority/1<br>

해당 식별번호의 사용자의 roles 데이터에서<br>

"roles": "ROLE_ADMIN" 데이터와 200 ok 


### DELETE /api/user/delete/{id}
사용자의 정보를 삭제하는 요청<br>

curl -X DELETE "http://localhost:8080/api/user/delete/5" -H "accept: */*"<br>

완료시 204 No Content를 받는다

## Product
### GET /api/product/allProduct
현재 모든 상품정보를 조회하는 요청

### GET /api/product/{id}
고유식별번호를 사용하여 상품정보를 조회하는 요청

### GET /api/product/name/{name}
상품이름을 사용하여 상품정보를 조회하는 요청

### GET /api/product/company/{company}
상품의 제조사명을 사용하여 같은 제조사의 모든 상품정보를 조회하는 요청

### POST /api/product/admin/create
관리자가 상품을 등록하는 요청을 처리

### PUT /api/product/admin/{productId}
관리자가 기존의 상품을 수정하는 요청을 처리

### DELETE /api/admin/delete/{id}
관리자가 기존의 상품을 삭제하는 요청을 처리

## CATEGORY
### GET api/category/{id}
카테고리정보를 조회하는 요청

### GET /api/category/admin/create
카테고리를 생상하는 요청을 처리
