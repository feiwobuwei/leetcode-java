package solve.easy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * @author minwei
 */
public class E155_MinStack {

    @Test
    public void test() {

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        // 返回 -3
        System.out.println(minStack.getMin());
        minStack.pop();
        // 返回 0
        System.out.println(minStack.top());
        // 返回 -2
        System.out.println(minStack.getMin());
    }


}

class MinStack {

    private LinkedList<Integer> stack;
    private List<Integer> array;
    private int min;

    public MinStack() {
        stack = new LinkedList<>();
        array = new ArrayList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        stack.push(x);
        array.add(x);
        min = Math.min(min, x);
    }

    public void pop() {
        stack.pop();
        array.remove(array.size() - 1);
        if (array.size() > 0) {
            min = array.get(0);
            for (Integer integer : array) {
                if (min > integer) {
                    min = integer;
                }
            }
        } else {
            min = Integer.MAX_VALUE;
        }
    }

    public int top() {
        if (stack.size() != 0) {
            return stack.peek();
        }
        return 0;
    }

    public int getMin() {
        return min;
    }
}
