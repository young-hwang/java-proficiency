package io.younghwang.java8;

public class OnlineClass {
    Integer id;
    String title;
    Boolean closed;

    public OnlineClass(Integer id, String title, Boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getClosed() {
        return closed;
    }
}

