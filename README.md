![main_banner_top_133](https://user-images.githubusercontent.com/17138123/230913056-9f8285b3-25c3-4c34-9b7b-94254f8a0b27.png)

# GBTW Reminder Client

GBTW Reminder Client는 건쉽배틀 : 토탈워페어 홈페이지와 공식 유튜브에서 업데이트된 내용이 있을 경우, 해당 업데이트에 대한 알림을 서버로부터 수신하여 모바일에 Notification을 표시해주는 안드로이드 전용 모바일 앱입니다.
<br><br>

<p align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/java-007396?style=flat-square&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Firebase Cloud Messaging-FFCA28?style=flat-square&logo=firebase&logoColor=black"/>
  <br>
  <a href = "https://github.com/ILoveGameCoding/GBTW-Reminder-Server"><img src="https://img.shields.io/badge/SERVER REPO-GBTW REMINDER SERVER-informatoinal?style=for-the-badge"/></a>
  <br>
</p>

<br>

# 동작 원리
#### 1) 처음 앱 실행 시 앱에서 필요한 설정값들을 SharedPreferences를 이용하여 초기화 및 저장합니다.

<br>

#### 2) 설정 화면에서 카테고리 체크를 통해 알림에 대해 구독 및 취소를 합니다.
알림에 변경이 있을 경우 해당 값을 SharedPreferences를 이용하여 내부에 저장하고, Firebase Cloud Messaging에 구독 및 취소 설정을 보냅니다.
 
<br>

#### 3) 서버로부터 알림이 왔을 경우에 Firebase Cloud Messaging을 수신하기 위해 등록한 서비스에서 처리를 합니다.
```
- 홈페이지 관련 Notification Message 구조
- title: 알림 제목(ex. 공지사항, 업데이트 완료, 정기점검 완료 등)
- body: 변경된 게시물 제목(ex. 2월 15일 정기 점검 안내)
- link: 해당 게시물의 세부정보 링크

- Youtube 관련 Notification Message 구조
- title: 유튜브 신규 영상
- body: 유튜브 동영상 제목(ex. [건쉽배틀 토탈워페어] 전투지휘사관학교 특강_연합과 연합혜택 모음.zip)
- link: 해당 유튜브 영상 링크
```

<br>

#### 4) 사용자가 Notification을 누를 경우 해당 Notification에 전달된 link 데이터를 이용해 건쉽배틀 홈페이지 또는 공식 Youtube로 이동합니다.
<br>
