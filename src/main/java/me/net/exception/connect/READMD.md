# 네트워크 예외1 - 연결 예외

네트워크 연결 시 발생할 수 있는 예외 정리

> net.exception.connect.ConnectMain 참조

## java.net.UnknownHostException

호스트를 알 수 없음

- `999.999.999.999` : 이런 IP는 존재하지 않음
- `google.gogo` : 이런 도메인 이름은 존재하지 않음

## java.net.ConnectException: Connection refused

`Connection refused` 메시지는 연결이 거절되었다는 뜻

- 연결이 거절되었다는 것은, 우선은 네트워크를 통해 해당 IP의 서버 컴퓨터에 접속은 했다는 뜻
- 그런데 해당 서버 컴퓨터가 45678 포트를 사용하지 않기 때문에 TCP 연결을 거절
- IP에 해당하는 서버는 켜져있지만, 사용하는 PORT가 없을 때 주로 발생
- 네트워크 방화벽 등에서 무단 연결로 인지하고 연결을 막을 때도 발생
- 서버 컴퓨터의 OS는 이때 TCP RST(Reset)라는 패킷을 보내서 연결을 거절
- 클라이언트가 연결 시도 중에 RST 패킷을 받으면 이 예외가 발생

## 윈도우 OS

윈도우의 경우 다음과 같이 `Connection refused` 뒤에 `connect` 라는 메시지가 하나 더 붙는 차이가 있음

- `java.net.ConnectException: Connection refused: connect`

## TCP RST(Reset) 패킷

TCP 연결에 문제가 있다는 뜻

- 이 패킷을 받으면 받은 대상은 바로 연결을 해제해야 함
