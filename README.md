<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185889664-d7f33692-92c2-4a4b-bc8d-8100c6dc9789.png" width=15% height=15%>
</p>

<div align="center">
  <h1>Recipe Book</h1>
</div>

<div align="center">
  500개의 요리 레시피들을 카테고리별로 볼 수 있고, 원하는 레시피들을 북마크해서 필요할 때마다 볼 수 있는 레시피 앱 
</div>

## Project Type
+ 개인 프로젝트

## Development Environment
+ Android Studio Chipmunk
+ Kotlin 1.6.10

## Application Version
+ minSdkVersion 26
+ targetSdkVersion 33

## Libraries
+ __Jetpack__
  + Compose, Navigation, ViewModel, DataStore 
+ __Asynchronous programming__
  + Coroutine 
+ __DI framework__
  + Hilt
+ __Database__
  + Firebase
+ __Network__
  + Retrofit
+ __Image__
  + [Landscapist](https://github.com/skydoves/landscapist)
  
## Functions
__1. 회원가입 & 로그인__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185891352-bace8b56-5880-472e-8385-19f185b28202.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185891498-4477241f-297e-45ef-a8e9-4a13d07e604a.png" width=25% height=25%>
</p>

+ Firebase Authentication 사용
+ 이메일과 비밀번호로 회원가입 및 로그인

__2. 홈 화면__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185894122-adf2bcd8-d408-45ae-87fb-3f79dd553e04.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185894149-30acecdf-553e-4c34-90a5-9f0c8bc3e87f.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185894156-18e79d54-97fd-436a-a736-488669ce4a29.png" width=25% height=25%>
</p>

+ 카테고리별 & 북마크한 레시피 조회 가능
+ 상단의 메뉴 버튼을 클릭하여 정보 수정, 로그아웃, 회원 탈퇴 가능

__3. 정보 수정__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185892484-773a1fec-465b-4991-8194-6c5f3aff271e.png" width=25% height=25%>
</p>

+ 로그인한 사용자의 정보(닉네임, 비밀번호) 수정 가능

__3. 레시피 목록 조회__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185893392-8a68cc99-15a3-4a69-bc73-8ee72e588edc.png" width=25% height=25%>
</p>

+ 500개의 레시피들을 카테고리별로(밥, 국&찌개, 반찬, 일품, 후식) 조회 가능
+ Open API 사용 [식품의약품안전처_조리식품의 레시피 DB](https://www.foodsafetykorea.go.kr/api/newDatasetDetail.do#)
+ 원하는 레시피를 북마크 버튼으로 추가 또는 제거 가능

__4. 레시피(조리 방법, 재료) 조회__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185893416-ddecac0e-0fc8-4da3-b929-4d940bcb8235.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185893427-446a21ae-5978-4e8f-8849-6768121460d1.png" width=25% height=25%>
</p>

+ 레시피 목록에서 재료 또는 방법 버튼 클릭 시 해당 정보 확인 가능

__5. 북마크한 레시피 조회__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185896359-79d59212-f941-4b66-b726-ac9a6ab13718.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185896371-94edd1c4-2f0f-46d5-bed3-68cc522e0901.png" width=25% height=25%>
</p>

+ 북마크 카테고리에서 북마크한 레시피들을 카테고리별로 조회 가능

## Version Test
|API Level|Test|
|------|---|
|33|OK|
|32|OK|
|31|OK|
|30|OK|
|29|OK|
|28|OK|
|27|OK|
|26|OK|





