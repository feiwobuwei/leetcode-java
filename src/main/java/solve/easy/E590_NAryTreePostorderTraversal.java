package solve.easy;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * 5,6,3,2,4,1
 *
 * @see E589_NAryTreePreorderTraversal
 * @see solve.hard.H145_BinaryTreePostorderTraversal
 */
public class E590_NAryTreePostorderTraversal {

    NAryNode nan1 = new NAryNode(1);
    NAryNode nan2 = new NAryNode(2);
    NAryNode nan3 = new NAryNode(3);
    NAryNode nan4 = new NAryNode(4);
    NAryNode nan5 = new NAryNode(5);
    NAryNode nan6 = new NAryNode(6);

    @Before
    public void prepare() {
        nan1.children = new LinkedList<>();
        nan1.children.add(nan3);
        nan1.children.add(nan2);
        nan1.children.add(nan4);

        nan3.children = new LinkedList<>();
        nan3.children.add(nan5);
        nan3.children.add(nan6);
    }

    @Test
    public void test() {
        List<Integer> postorder = postorder(nan1);
        System.out.println(postorder);
    }

    @Test
    public void test2() {
        List<Integer> postorder = postorder2(nan1);
        System.out.println(postorder);
    }

    private List<Integer> result = new LinkedList<>();

    /**
     * 递归法
     *
     * @param root 根节点
     * @return 前序遍历结果
     */
    public List<Integer> postorder(NAryNode root) {
        if (root != null) {
            if (root.children == null) {
                result.add(root.val);
            } else {
                for (NAryNode child : root.children) {
                    postorder(child);
                }
                result.add(root.val);
            }
        }
        return result;
    }

    /**
     * 迭代法
     *
     * @param root 根节点
     * @return 前序遍历结果
     */
    public List<Integer> postorder2(NAryNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        Stack<NAryNode> stack = new Stack<>();

        if (root == null) {
            return output;
        }

        stack.add(root);
        // 只要栈不为空
        while (!stack.isEmpty()) {
            // 取出队头的元素
            NAryNode node = stack.pop();
            // 结果每次都要添加到头部
            output.addFirst(node.val);
            if (node.children != null) {
                // 正序添加
                for (int i = 0; i <= node.children.size()-1; i++) {
                    stack.push(node.children.get(i));
                }
            }
        }

        return output;
    }
}
