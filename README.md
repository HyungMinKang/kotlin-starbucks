# 스타벅스 클론  

<div align="center">
    
[![Android](https://img.shields.io/badge/android%20studio-2021.1.1%20Patch%202-%233DDC84?logo=android-studio)]() [![Kotlin](https://img.shields.io/badge/kotlin-1.5.30-%237F52FF?logo=kotlin)]()
    
[![DI](https://img.shields.io/badge/DI-Koin-%2358C8AE)]() [![Pattern](https://img.shields.io/badge/Pattern-MVVM-%2358C8AE)]()
[![](https://img.shields.io/badge/Library-jetpack--navigation-brightgreen)]() [![Retrofit](https://img.shields.io/badge/Library-Retrofit-%23%234285F4)]() [![Coil](https://img.shields.io/badge/Library-Coil-brightgreen)]()  [![Room](https://img.shields.io/badge/DB-Room-brightgreen)]() 
 [![ViewPager2](https://img.shields.io/badge/Library-Viewpager2-brightgreen)]() 

</div>
 
<br>

## 🖥 주요 화면
![image](https://user-images.githubusercontent.com/58967292/174077518-204bd678-5311-48ca-b8e1-13584cbd8fb5.png)
![image](https://user-images.githubusercontent.com/58967292/174077609-d181886c-e3d3-44f9-8a8c-7cd081334012.png)
![image](https://user-images.githubusercontent.com/58967292/174077678-d773699b-2750-49c0-aa53-b8f20209ec10.png)
![image](https://user-images.githubusercontent.com/58967292/174077732-7b421bf3-f591-4c1e-9f75-398ab97225fe.png)
![image](https://user-images.githubusercontent.com/58967292/174077830-a77f61fd-60bc-4e0f-abcc-7042d9a90866.png)
![i![image](https://user-images.githubusercontent.com/58967292/174078169-bc1580b9-519d-4f06-9d5e-1da2314018fd.png)

<br>

## 🌟 화면 별 기능

### 이벤트 화면
- 이벤트 배너 보여주기
- 다시보여주지 않기 클릭시 24시간동안 해당 페이지 보여주지 않음

### 홈 화면
- 상단 AppBar
- 사용자 정보와 사용자 추천 메뉴 리스트 보여주기
- 1시간 마다 현재 시간 추천 메뉴 보여주기
- 현재 진행중인 이벤트 정보 보여주기 + See All 버튼 클릭시 Detail 화면으로 이동

### 주문 화면
- ### 주문 카테고리
   -  ViewPager을 통해 주문 카테고리(음료,푸드,상품) 표현
   -  카테고리에서 아이템 클릭시 카테고리 디테일 페이지로 이동

- ### 주문 카테고리 디테일
  - 선택한 카테고리에 해당하는 제품 리스트를 보여줌
  - 아이템 클릭시 제품 디테일 화면으로 이동

- ### 제품 디테일 
  - 제품에 대한 이미지, 정보를 보여줌
  - 하트 버튼 클릭을 통해 즐겨찾기 등록 가능
  - 주문하기 버튼은 주문가능 ( 알람 매니저를 통해 일정시간 지난후 알림이 오게됨)
 
### 즐겨 찾기 화면
- 사용자의 즐겨찾기 메뉴 리스트를 보여줌
- 하튼 버튼 클릭시 즐겨찾기 해제
- 주문하기 버튼 클릭시 제품 디테일 화면으로 이동 


<br>




## ✨ 기술 스택

- LiveData, Flow 
- DI framework : Koin 
- Coil
- Room Database, Shared Preference
- Alrarm Manger, Notification
- RecyclerView 



<br>

## 💡 핵심 키워드

1️⃣ 알람 매니저  
  
2️⃣ Room DataBase

3️⃣ Flwo Throttle/Debounce





<br>

## 📂 Project Folder
```
📂airbnb
  ├─common
  ├─data
  │  ├─dto
  │  ├─remote
  │  └─repository
  |   
  ├─di
  ├─domain
  |  ├─model
  │  └─repository.kt
  |
  ├─room
  │  ├─dao
  │  ├─entity
  │  └─database
  └─ui
     ├─comon
     ├─event
     ├─favorite
     ├─home
     ├─home
     ├─order
     ├─pay
     └─product
```

