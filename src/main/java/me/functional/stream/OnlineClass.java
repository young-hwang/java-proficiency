package me.functional.stream;

import java.util.Optional;

public class OnlineClass {
    Integer id;
    String title;
    Boolean closed;
    Progress progress;

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

    public Optional<Progress> getProgress() {
        // of(value) value 가 null 일 수 없는 경우
        // ofNullable(value) value 가 null 가능
        return Optional.ofNullable(this.progress);

        // optional을 사용하면서 null을 리턴하지 말자
        // 사용자가 Optional.ifpresent 같은  optional 사용시 null 로 인한 에러 발생
        // Optional.empty() 를 대신 사용
        // return null;
        // return Optional.empty();
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    // 파라미터로 optional 사용하지 말것
    public void setProgress(Optional<Progress> progress) {
        // Optional<optional.Progress> 에 대한 null check 필요
        if (progress != null) {
            progress.ifPresent(p -> this.progress = p);
        }
    }
}


