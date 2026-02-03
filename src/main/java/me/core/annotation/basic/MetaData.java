package me.core.annotation.basic;

@AnnoMeta
public class MetaData {

//    @AnnoMeta // 필드에 적용 - 컴파일 오류
    private String id;

    @AnnoMeta
    public String getId() {
        return this.id;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnoMeta annotation = MetaData.class.getAnnotation(AnnoMeta.class);
        System.out.println(annotation);

        AnnoMeta annotation1 = MetaData.class.getMethod("getId").getAnnotation(AnnoMeta.class);
        System.out.println("annotation1 = " + annotation1);
    }
}
