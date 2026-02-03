package me.concurrency.forkjoin;

import java.util.Arrays;
import java.util.Random;

/**
 * Arrays.parallelSort() 예제
 *
 * parallelSort()는 Fork-Join 프레임워크를 사용하여 배열을 병렬로 정렬합니다.
 * 대용량 배열(약 5,000개 이상)에서 sort()보다 빠른 성능을 보입니다.
 */
public class ParallelArraySortingExample {

    public static void main(String[] args) {
        int[] sizes = {1_000, 10_000, 100_000, 1_000_000};

        for (int size : sizes) {
            compareSort(size);
        }
    }

    private static void compareSort(int size) {
        int[] array1 = generateRandomArray(size);
        int[] array2 = array1.clone();

        // Arrays.sort() - 단일 스레드
        long start1 = System.nanoTime();
        Arrays.sort(array1);
        long end1 = System.nanoTime();
        long sortTime = (end1 - start1) / 1_000_000;

        // Arrays.parallelSort() - Fork-Join 기반
        long start2 = System.nanoTime();
        Arrays.parallelSort(array2);
        long end2 = System.nanoTime();
        long parallelSortTime = (end2 - start2) / 1_000_000;

        System.out.printf("Size: %,10d | sort(): %4dms | parallelSort(): %4dms | diff: %+dms%n",
                size, sortTime, parallelSortTime, parallelSortTime - sortTime);
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random(42);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}
