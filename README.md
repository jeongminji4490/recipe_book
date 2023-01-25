<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185958682-d25e9eb1-b41f-4df5-ad27-d7eec77f9329.png">
</p>

<div align="center">
  <h1>Recipe Book</h1>
</div>

<div align="center">
  Recipe app you can view 500 cooking recipes by categories and bookmark the recipes you want 
</div>

## Project Type
+ Toy project

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
__1. Sign up & Sign in__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185891352-bace8b56-5880-472e-8385-19f185b28202.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185891498-4477241f-297e-45ef-a8e9-4a13d07e604a.png" width=25% height=25%>
</p>

+ Using Firebase Authentication
+ Users can Sign up & Sign in with their email and password

__2. Home screen__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185894122-adf2bcd8-d408-45ae-87fb-3f79dd553e04.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185894149-30acecdf-553e-4c34-90a5-9f0c8bc3e87f.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185894156-18e79d54-97fd-436a-a736-488669ce4a29.png" width=25% height=25%>
</p>

+ Users can view the recipes by categories
+ Users can view the recipes they bookmarked
+ Users can edit your personal information, sign out, and withdraw your account by clicking the menu button at the top

__3. Edit personal information__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185892484-773a1fec-465b-4991-8194-6c5f3aff271e.png" width=25% height=25%>
</p>

+ Users can edit their information(nickname, password)

__3. View the list of recipes__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185893392-8a68cc99-15a3-4a69-bc73-8ee72e588edc.png" width=25% height=25%>
</p>

+ Users can view the 500 recipes by categories
+ [Open API](https://www.data.go.kr/data/15060073/openapi.do)
+ Users can add or delete the recipe by clicking bookmark button

__4. View the recipe(methods, ingredients)__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185893416-ddecac0e-0fc8-4da3-b929-4d940bcb8235.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185893427-446a21ae-5978-4e8f-8849-6768121460d1.png" width=25% height=25%>
</p>

+ Users can check the ingredients and cooking methods by clicking the button in the list of recipes

__5. View the bookmarked recipe__
<p align="center">
  <img src = "https://user-images.githubusercontent.com/62979330/185896359-79d59212-f941-4b66-b726-ac9a6ab13718.png" width=25% height=25%>
  <img src = "https://user-images.githubusercontent.com/62979330/185896371-94edd1c4-2f0f-46d5-bed3-68cc522e0901.png" width=25% height=25%>
</p>

+ Users can view the bookmarked recipes by categories on ‘Bookmark’ menu

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





