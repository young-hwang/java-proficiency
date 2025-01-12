package me.object.reference;

import java.lang.ref.ReferenceQueue;

public class BigObjectReference <T extends BigObject> {
    public BigObjectReference(BigObject strongReference, ReferenceQueue<Object> referenceQueue) {
    }
}
