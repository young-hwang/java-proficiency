# 숫자 표기법

```java
int decVal = 1106;
int octVal = 02122;
int hexVal = 0x452;
int binVal = 0b10001010010; // 이진수 표현 java 7 추가

1106 으로 동일
```

단위 표시를 위하여 _를 사용 할 수 있습니다.
다만 숫자 사이에만 가능하므로 1b_000 이나 111_L 같은 표기는 안됩니다.

```java
int binVal = 0b100_0101_0010;
int decVal = 1_000_000;
```

# switch 문장 확장

java 6 까지는 switch-case 문장에 정수형만 사용할 수 있었지만 java 7 부터는 String 도 사용할 수 있습니다.

```java
swtch (employee) {
    case "CEO":
        return 10;
    case "Manager":
        return 15;
    case "Engineer";
    case "Developer":
        return 10;
}
```

