# Generic <> 사용

Generic 사용시 반드시 생성자에도 해당 타입들을 상사하게 명시하여야 하였습니다.
하지만 Java 7 부터는 생성자에는 일일이 타입들을 명시해 줄 필요가 없습니다.

```java
// java 6 이전
Map<String, Integer> map = new HashMap<String, Integer>();
Map<String, List<String>> map2 = new HashMap<String List<String>>();
ArrayList<String> list = new ArrayList<String>();

// java 7
Map<String, Integer> map = new HashMap<>();
Map<String, List<String>> map2 = new HashMap<>();
ArrayList<String> list = new ArrayList<>();
```

제약 사항
- <> 미 지정시 컴파일 경고 발생
- <> 생성시 유의 사항 - 메소드 내에서 객체 생성시
- <> 생성시 유의 사항 - 제네릭하면서도 제레릭하지 않은 객체 생성시

## 제네릭하면서도 제레릭하지 않은 객체 생성시

```java

public class GenericClass<X> {
    private X x;
    private Object o;

    public <T> GenericClass(T t) {
        this.o = t;
        System.out.println("T type=" + t.getClass().getName());
    }

    public void setValue(X x) {
        this.x = x;
        System.out.println("X type=" + x.getClass().getName());
    }
}

public class TypeInterface {
    public static void main(String args[]) {
        TypeInterface typeInterface = new TypeInterface();
        typeInterface.makeObject1();
    }

    public void makeObject1() {
        GenericClass<Integer> generic1 = new GenericClass<>("String");
        generic1.setValue(999);
    }
}

// result
T type=java= java.lang.String
X type=java= java.lang.Integer

public class TypeInterface {
    public static void main(String args[]) {
        TypeInterface typeInterface = new TypeInterface();
        typeInterface.makeObject1();
    }

    public void makeObject1() {
        // 명시적인 T 타입 선언 상태에서 타입 X 에서 <> 선언을 하면 캄파일러 오류 발생
        GenericClass<Integer> generic1 = new <String> GenericClass<>("String"); 
        generic1.setValue(999);
    }
}

// result
error
```

# non reifiable varargs 타입

제네릭을 사용하면서 발생 가능한 문제 중 하나가 'reifiable 하지 않은 varargs 타입' 입니다.
이 문제는 자바에서 제네릭을 사용하자 않는 버전과의 호환성을 위해서 제공했던 기능 때문이 발생합니다.

reifiable: 실행시에도 타입 정보가 남아있는 타입을 의미
non reifiable: 컴파일시 타입 정보가 손실되는 타입을 의미

```java
    private void addData() {
        ArrayList<String> list = new ArrayList<>();
        boolean result = Collections.addAll(list, "a", "b");
        System.out.println(list);
    }
```

큰문제는 발생하지 않지만 안전하지 못하다는 메시지가 나타난다.

```java
public static <T> boolean addAll(Collection<? super T> c, T... elements)
```

위 소스와 같이 가변 매개 변수 사용할 경우 내부적으로 다음과 같이 Object 배열로 처리 됩니다.

```java
public static boolean addAll(java.uitl.Collection c, java.lang.Object[] elements)
```

Object 배열로 처리되면 큰 문제는 발생하지는 않지만 잠재적으로 문제가 발생할 수도 있습니다.
이러한 경고를 없애기 위해서는 @SafeVarargs 라는 어노테이션을 메소드 선언부에 추가하면 됩니다.

addAll() 메소드에도 java 7부터 이 어노테이션이 추가되었습니다.
어노테이션은 다음의 경우에만 사용 가능합니다.

- 가변 매개 변수를 사용하고 final 이나 static으로 선언 된 경우

해당 어노테이션은 가변 매개 변수가 reifiable 타입이고 메소드 내에서 매개 변수를 다른 변수에 대입하는 작업을 수행하는 경우 컴파일러에서 경고가 발생합니다.




