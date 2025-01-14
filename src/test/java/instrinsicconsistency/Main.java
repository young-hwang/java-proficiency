package instrinsicconsistency;

public class Main {
    public static void main(String[] args) {
        Parent child = new Child(); // 대체 가능성
        child.print(); // 내적 일관성에 의해 "child" 출력
    }
}
