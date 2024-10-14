<h1>[미션1] 테이블 설계하기</h1>
<h2>MyPet</h2>

![ERD](https://github.com/user-attachments/assets/c1074208-5a0c-43c3-837e-7fc7b12b8051)


<p>해당 테이블은 반려동물과 주인의 관계 N:M을 가지는테이블 입니다.</p>
<p>한 가구에서 반려동물은 여러명의 주인이 있다고 가정하였고 한 명의 주인은 여러마리의 반려동물이 있다고 가정하였습니다.</p>
<p>PK는 AI를 사용합니다.</p>

<h3> 사용자 기능 </h3>
<p>* 사용자는 회원을 등록하면 회원조회, 수정, 탈퇴가 가능합니다.</p>
<p>* 등록된 사용자는 자신의 반려동물을 등록, 수정, 삭제, 조회가 가능합니다.</p>
<p>* 다른 회원의 반려동물을 확인할 수 있습니다.</p>

<hr>
<h2>REST API 설계해보기</h2>

<b>반려동물</b>
<p>반려동물 등록 &nbsp; POST /api/v1/pets</p>
<p>반려동물 목록 조회 &nbsp; GET /api/v1/pets</p>
<p>반려동물 상세 조회 &nbsp; GET /api/v1/pet/{pet-id}</p>
<p>반려동물 정보 수정 &nbsp; PUT /api/v1/pet/{pet-id}</p>
<p>반려동물 삭제 &nbsp; DELETE /api/v1/pet/{pet-id} </p>
<p>타인 반려동물 조회 &nbsp; GET /api/v1/pet/{member-id}/{pet-id}</p>

<b>회원</b>
<p>회원정보 등록 &nbsp; POST /api/v1/member</p>
<p>회원정보 상세 조회 &nbsp; GET /api/v1/member/{member-id}</p>
<p>회원정보 정보 수정 &nbsp; PUT /api/v1/member/{member-id}</p>
<p>회원탈퇴 &nbsp; DELETE /api/v1/member/{member-id}</p>





