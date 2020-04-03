package solve.middle;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。
 * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
 * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
 * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 * <p>
 * 示例 1：
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 * <p>
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 * @author minwei
 */
public class M621_TaskScheduler {

    private char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};

    @Test
    public void test() {
        int i = leastInterval(tasks, 2);
        System.out.println(i);

        // A -> B -> (待命) ->(待命)-> A -> B -> (待命)-> (待命) -> A -> B.
        i = leastInterval(tasks, 3);
        System.out.println(i);
    }


    @Test
    public void test2() {
        int i = leastInterval2(tasks, 2);
        System.out.println(i);

        i = leastInterval2(tasks, 3);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = leastInterval3(tasks, 2);
        System.out.println(i);

        i = leastInterval3(tasks, 3);
        System.out.println(i);
    }


    /**
     * 常规方法 -- 29.27%
     * <p>
     * time O(nS) S是数组长度
     * space O(1)
     */
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        // O(n)
        for (char task : tasks) {
            count[task - 'A']++;
        }
        // count大小是固定的 所以这个是O(1)
        Arrays.sort(count);
        int result = 0;
        while (count[25] > 0) {
            int i = 0;
            // 控制间隔内 不会执行相同的任务
            while (i <= n) {
                if (count[25] == 0) {
                    break;
                }
                if (i < 26 && count[25 - i] > 0) {
                    count[25 - i]--;
                }
                result++;
                i++;
            }
            Arrays.sort(count);
        }
        return result;
    }

    /**
     * 最大优先队列 -- 10.04%
     * <p>
     * PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
     *
     * @see M215_KthLargestElementInAnArray#findKthLargest2(int[], int)
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(i -> -i));

        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                priorityQueue.add(count[i]);
            }
        }
        int result = 0;
        while (!priorityQueue.isEmpty()) {
            int i = 0;
            List<Integer> list = new ArrayList<>();
            while (i <= n) {
                if (!priorityQueue.isEmpty()) {
                    if (priorityQueue.peek() > 1) {
                        list.add(priorityQueue.poll() - 1);
                    } else {
                        priorityQueue.poll();
                    }
                }
                result++;
                if (priorityQueue.isEmpty() && list.size() == 0) {
                    break;
                }
                i++;
            }
            priorityQueue.addAll(list);
        }
        return result;
    }


    /**
     * 直接求出待命次数 -- 100.00%
     */
    public int leastInterval3(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        Arrays.sort(count);
        int maxVal = count[25] - 1, idleSlots = maxVal * n;
        for (int i = 24; i >= 0 && count[i] > 0; i--) {
            idleSlots -= Math.min(count[i], maxVal);
        }
        if (idleSlots > 0) {
            return idleSlots + tasks.length;
        } else {
            return tasks.length;
        }
    }
}
