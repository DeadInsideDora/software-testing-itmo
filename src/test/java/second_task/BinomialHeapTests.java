package second_task;

import org.dallvoro.second_task.BinomialHeap;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class BinomialHeapTests {


    @Test
    void testEmptyExtract() {
        assertThrows(NoSuchElementException.class, () -> new BinomialHeap().pop());
    }

    @Test
    void testEmptyFront() {
        assertThrows(NoSuchElementException.class, () -> new BinomialHeap().first());
    }

    @Test
    void testIsEmpty() {
        assertTrue(new BinomialHeap().isEmpty());
    }

    @Test
    void testIsEmptyInNonEmpty() {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    void testSmallInReverseOrder() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = 20; i >= 1; --i) {
            heap.insert(i);
        }
        for (int i = 1; i <= 20; ++i) {
            assertEquals(heap.pop(), i);
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testSmallInNormalOrder() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = 1; i <= 20; ++i) {
            heap.insert(i);
        }
        for (int i = 1; i <= 20; ++i) {
            assertEquals(heap.pop(), i);
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testBigInReverseOrder() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = 10_000_000; i >= 1; --i) {
            heap.insert(i);
        }
        for (int i = 1; i <= 10_000_000; ++i) {
            assertEquals(i, heap.pop());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testBigInNormalOrder() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = 1; i <= 10_000_000; ++i) {
            heap.insert(i);
        }
        for (int i = 1; i <= 10_000_000; ++i) {
            assertEquals(heap.pop(), i);
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDuplicate() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = 0; i < 100; ++i) {
            heap.insert(12);
        }
        for (int i = 0; i < 100; ++i) {
            assertEquals(heap.pop(), 12);
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testRandomNumbers() {
        Random r = new Random(Double.doubleToLongBits(Math.random()));
        BinomialHeap heap = new BinomialHeap();
        SortedSet<Integer> expected = new TreeSet<>();
        for (int i = 0; i < 10_000; ++i) {
            int newValue = r.nextInt();
            while (expected.contains(newValue)) {
                newValue = r.nextInt();
            }
            expected.add(newValue);
            heap.insert(newValue);
        }
        for (var val : expected) {
            assertEquals(val, heap.pop());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testNegateNumbers() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = -10_000_000; i <= 10_000_000; i += 7) {
            heap.insert(i);
        }
        for (int i = -10_000_000; i <= 10_000_000; i += 7) {
            assertEquals(i, heap.pop());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testPopAfterInsert() {
        BinomialHeap heap = new BinomialHeap();
        for (int i = 0; i <= 2_000; ++i) {
            assertTrue(heap.isEmpty());
            heap.insert(i);
            assertEquals(i, heap.first());
            assertFalse(heap.isEmpty());
            heap.pop();
            assertTrue(heap.isEmpty());
        }
        assertTrue(heap.isEmpty());
    }

    @Test
    void testConstructorOfAnArray() {
        int[][] first = {{1, -10, 100, 90, 20},
                        {192, 394, 99192, 94492, 39491, 39491, 4995},
                        {12, 49492, 193491, 488492, 1}};
        for (var arr : first) {
            BinomialHeap heap = new BinomialHeap(arr);
            Arrays.sort(arr);
            for (var element : arr) {
                assertEquals(element, heap.pop());
            }
            assertTrue(heap.isEmpty());
        }
    }
}