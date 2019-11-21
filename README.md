# Onair-waiTing-Line
Onair waiTing Line (OTL)

## 주제선정 배경
국가대표 축구경기와 같이 traffic이 크게 상승되는 경우 수동으로 화질 가능을 조절하며(720p 제거 > 480p 제거 등) CDN traffic의 risk를 관리하고 있어 완전한 자동화방식에서 기존 시청자와 신규인입 사용자의 사용성이 보장될 필요가 있음

## 개발 요구사항 (필수)
- web개발 환경
- java로 기본적인 backend 개발능력 필요
- backend에서 개발된 정보를 web의  front에서 노출하고 제어하는 front-end 개발능력 필요
- web에서의 socket 통신(keepalive) 활용능력 필요
- Queuing개념을 이해하고 관련 open platform을 사용할 수 있는 능력
- 개발 요구사항 (선택)
- 타시스템의 api(xml 또는 json)를 처리할 수 있는 기술
- 가상의 traffic의 조절할 수 있는 simulation 능력

## 개발언어
- Java
- Javascript
- SQL

## 기술 스택
- server
  - Springboot (Spring Web MVC)
  - DB : H2DB
  - Java8
  - 화면
    - view template engine : handlebars
    - websocket
- MQ
  - RabbitMQ 3.8.1 (Java client)
- api
  - nodejs 12.x.x
- push
  - nodejs 12.x.x
  - websocket

## 버전관리
### branch 기본
- issue 별로 branch 분리
- 코드리뷰 후에 approve 되면 develop 머지
- master로 머지하여 배포

### commit 메시지 추천 동사
- Add : 새로운 기능추가
- Remove : 제거
- Improve : 기능, 성능의 향상
- Fix : 버그, 오타 등 수정
- Doc : 문서화
- Refactor : 리팩토링
- Polish : 잡다한 수정 묶음 (다른 적절한 문구가 없을 경우 사용)

### 사용 예시
- Add LiveApi 호출 로직 (#20)
- Remove 사용하지 않는 변수 (#15)
- Polish (#13, #14)

## 개발환경 IDE 설정
- 개발도구
  - Intellij IDEA 2019.2.4

- Line separator 설정
  - File > Settings > Editor > Code Style
  - General 탭
  - Line separator : Unix and OS X(\n)
- 탭설정
  - File > Settings > Editor > Code Style > Java
  - Tabs and Indents 탭
  - Use tab charactor : 체크
  - Tab size : 4
- 화이트 스페이스 보이게 하기
  - 탭과 스페이스를 IDE 안에서 쉽게 구분이 가능하도록 설정한다.
  - File > Settings > Editor > General > Appearance 메뉴로 이동한다.
  - Show whitespaces를 선택한다. 하위 분류에서 Leading, Inner,Trailing를 모두 선택한다.
- File Encoding
  - 모든 파일의 인코딩은 UTF-8

## 코딩 컨벤션
- java coding convention on a page
![JavaCodingConventionOnaPage](https://user-images.githubusercontent.com/25360239/68466694-44a0fc00-0258-11ea-9d8b-28d25483159a.gif)

## 가상서버 계정

- id : campus / campussu (sudo 사용 가능 계정, 필요할때만 접속해서 사용)
- pwd : 별도공유
- 접속 서버
  - 106.10.33.151:20191 (server-otl)
    - 공인 : 45.119.145.185
  - 106.10.33.151:20193 (push-otl)
    - 공인 : 45.119.146.240
  - 106.10.33.151:20194 (liveapi-otl)
    - 공인 : 106.10.50.177
  - 106.10.33.151:20195 (mq-otl)
    - 공인 : 106.10.34.82
  

## 세부 컴포넌트

### server
- 스포츠 경기페이지
- 영상 정보 요청/ 상황에 따른 처리
  - 성공시 영상 보여줌
  - 실패시 대기열 진입 (대기열 websocket 접속)


### liveapi
- liveapi 정보 리턴
  - 응답 포맷 예
{
  "success": true,
  "channel": {
  	"id": "100",
  	"name": "naver sports tv"
  }
}
{
	"success": false,
	"channel": {}
}
- data는 H2DB에 넣음
- data 수동관리 필요

### mq
- 대기열 진입시 mq로 진입
- 가용량 확보시 mq에 쌓인 순서대로 성공케이스로 처리

### push
- 실패시 대기열에 진입하고 대기열정보를 가지고 websocket으로 push 서버로 접근
- push 서버는 liveapi로 가용량이 확보되는지 주기적 체크
- 가용량 확보시 mq에서 꺼내와 클라이언트에게 접근 가능하도록 push

## 전체구성도
<img width="1157" alt="생중계대기열구성도" src="https://user-images.githubusercontent.com/25360239/68466525-ee33bd80-0257-11ea-897a-fa5d5f4287c3.png">
