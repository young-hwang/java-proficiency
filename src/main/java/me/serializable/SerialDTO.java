package me.serializable;

import java.io.Serializable;

public class SerialDTO implements Serializable {
    static final long serialVersionUID = 1L;
    private String bookName;
    private transient int bookOrder;
    private boolean bestSeller;
    private long soldPerDay;
    private String bookType = "IT"; // serialVersionUID 지정 시 데이터 저장 시 bookType이 없는 상태에서 저장 후 읽어 들일 때 bookType이 있어도 에러 발생하지 않음

    public SerialDTO(String bookName, int bookOrder, boolean bestSeller, long soldPerDay) {
        this.bookName = bookName;
        this.bookOrder = bookOrder;
        this.bestSeller = bestSeller;
        this.soldPerDay = soldPerDay;
    }

    @Override
    public String toString() {
        return "SerialDTO{" +
                "bookName='" + bookName + '\'' +
                ", bookOrder=" + bookOrder +
                ", bestSeller=" + bestSeller +
                ", soldPerDay=" + soldPerDay +
                ", bookType=" + bookType +
                '}';
    }
}
