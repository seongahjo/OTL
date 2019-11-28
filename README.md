# Onair-waiTing-Line
![Logo](https://user-images.githubusercontent.com/10272119/69785738-29dcfa00-11fc-11ea-87bc-521dfe6d92f9.png)

Onair waiTing Line (OTL)


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
