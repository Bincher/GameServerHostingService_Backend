# 게임 호스팅 서비스 - 서버
![Image](https://github.com/user-attachments/assets/3f8af7ee-d588-472e-9fda-dfcabe3733da)
- https://www.notion.so/bincher/485d7480dc5d4df581d346f9f769d5c6

    - 명세서 페이지(http://주소/swagger-ui/index.html#/)

- 4학년 교양 '창업 마케팅'에서 기말과제로 냈던 창업계획서를 바탕으로 실제 앱을 구현 하는 것이 목표

    - 해당 프로젝트는 스프링, 리액트네이티브, 배포 공부를 위한 프로젝트로 실제로 서비스할 계획은 없다

    - 실제로 서비스하려면 aws나 gcp로 서버를 만드는 과정을 게임마다 공부할 필요가 존재

    - 구글 플레이 스토어에 제출해볼 의향은 있으나 당장은 계획에 없음

- 사용자는 게임 서버에 대한 제작을 운영자에게 맡기는 시스템

    - 사용자는 게임 목록에서 게임을 선택해 서버를 생성할 수 있다

    - 운영자는 사용자로부터 받은 서버를 제작하고 관리한다

    - 사용자는 원하는 때에 서버에 대한 수정과 삭제가 가능하다

    - 사용자는 각 서버에 대한 자세한 문의를 신청할 수 있다

    - 위 기능들은 로그인이 필수이다

## 설명

- 대략적인 기능(자세한 API 명세서 : 제작중)

    - 인증 API (AuthController)

        - 중복된 아이디 확인

        - 이메일 인증

        - 인증 번호 확인

        - 회원 가입

        - 로그인

        - 회원탈퇴

    - 게임 목록 API (GameController)

        - 게임 목록 출력

        - 게임 추가(admin 전용)

    - 게임 서버 API (GameController)

        - 게임 서버 생성

        - user의 게임 서버 목록(user)

        - 전체 게임 서버 목록(admin 전용)

        - 게임 서버 수정(user)

        - 게임 서버 관리(admin 전용)

        - 게임 서버 삭제

    - 문의 API (SupportController, NotificationController)

        - admin to user 이메일 전송

        - admin to user 알림 전송

        - user to admin 알림 전송

    - 프로필 제공 API (UserController)

        - 다른 사용자가 보는 나의 프로필

            - 현재 사용 X

        - 내가 보는 나의 프로필

    - 프로필 수정 API (UserController)

        - 비밀번호 확인

        - 프로필 이미지 수정

        - 이메일 수정

        - 비밀 번호 수정

    - 파일 업로드 API (FileController)

        - 파일 업로드

        - 파일(이미지) 출력

- 사용 방법

    - spring : local에서는 BoardBackApplication.java 실행
