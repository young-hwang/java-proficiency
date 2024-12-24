package me.annotation.basic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {
    String value() default "";
    int count() default 0;
    String[] tags() default {};

    // 사용자가 구현한 일반적인 Class는 안됨
    // MyLogger logger;

    // Class의 메타데이터 사용 가능
    Class<? extends String> clazz = null;
}
