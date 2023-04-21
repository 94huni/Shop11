# Shop Project Api

# 쇼핑몰 회원가입 상품등록, 장바구니 결제에대한 api입니다

# 사용기술
 - 사용언어 : JAVA 11
 - 데이터베이스 : Maria DB
 - 프레임워크 : Spring boot 2.7.10
 - 기타기술 : Jpa, Lombok, Spring Security, Swagger 3.0.0

# GET /api/user/{id}
사용자의 고유식별번호를 받아 해당 사용자의 정보를 조회하는 GET 요청의 대한 응답

# GET /api/user/admin/{userid}
관리자가 사용자아이디를 받아 해당 사용자의 정보를 조회하는 응답

# GET /api/user/admin/allUser
모든 유저의 정보를 조회하는 응답

# GET /api/user/current
현재 접속중인 유저의 정보를 조회하는 GET 요청의 대한 응답
