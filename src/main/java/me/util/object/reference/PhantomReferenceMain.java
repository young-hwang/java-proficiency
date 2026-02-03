package me.util.object.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceMain {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object o = new Object();

        PhantomReference<Object> objectPhantomReference = new PhantomReference<>(o, referenceQueue);

        // 강한 참조를 제거하여 객체가 GC 대상이 되게 함
        o = null;

        System.out.println("Before GC: " + objectPhantomReference.isEnqueued());

        System.gc();
        Thread.sleep(1000);

        // 팬텀 참조가 큐에 들어올 때까지 대기
        Reference<?> remove = referenceQueue.remove();

        if (remove == objectPhantomReference) {
            System.out.println("Phantom reference is enqueued");
        }
    }
}
