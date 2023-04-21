# Shop Project Api

# 쇼핑몰 회원가입 상품등록, 장바구니 결제에대한 api입니다

# 사용기술
 - 사용언어 : JAVA 11
 - 데이터베이스 : Maria DB
 - 프레임워크 : Spring boot 2.7.10
 - 기타기술 : Jpa, Lombok, Spring Security, Swagger 3.0.0
 

# USER
# GET /api/user/{id}
사용자의 고유식별번호를 받아 해당 사용자의 정보를 조회하는 GET 요청의 대한 응답

# GET /api/user/admin/{userid}
관리자가 사용자아이디를 받아 해당 사용자의 정보를 조회하는 응답

# GET /api/user/admin/allUser
모든 유저의 정보를 조회하는 응답

# GET /api/user/current
현재 접속중인 유저의 정보를 조회하는 GET 요청의 대한 응답

# POST /api/user/signup
회원가입 하는 요청

# POST /api/user/login
로그인 하는 요청 Basic 인증키 발급

# PUT /api/user/update/{id}
현재 사용하고 있는 사람이 직접 회원정보를 수정할수있는 요청

# PUT /api/user/admin/update/{id}
관리자가 임의로 다른 정보를 수정하는 요청

# PUT /api/user/admin/authority/{id}
관리자가 회원정보에서 ROLE_USER 권한을 ROLE_ADMIN으로 상승시키는 요청

# DELETE /api/user/delete/{id}
사용자의 정보를 삭제하는 요청

# Product
# GET /api/product/allProduct
현재 모든 상품정보를 조회하는 요청

# GET /api/product/{id}
고유식별번호를 사용하여 상품정보를 조회하는 요청

# GET /api/product/name/{name}
상품이름을 사용하여 상품정보를 조회하는 요청

# GET /api/product/company/{company}
상품의 제조사명을 사용하여 같은 제조사의 모든 상품정보를 조회하는 요청

# POST /api/product/admin/create
관리자가 상품을 등록하는 요청을 처리

# PUT /api/product/admin/{productId}
관리자가 기존의 상품을 수정하는 요청을 처리

# DELETE /api/admin/delete/{id}
관리자가 기존의 상품을 삭제하는 요청을 처리

# CATEGORY
# GET api/category/{id}
카테고리정보를 조회하는 요청

# GET /api/category/admin/create
카테고리를 생상하는 요청을 처리
