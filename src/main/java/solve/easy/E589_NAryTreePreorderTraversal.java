package solve.easy;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 * @see solve.middle.M144_BinaryTreePreorderTraversal
 */
public class E589_NAryTreePreorderTraversal {

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
        List<Integer> preorder = preorder(nan1);
        System.out.println(preorder);
    }

    @Test
    public void test2() {
        List<Integer> preorder = preorder2(nan1);
        System.out.println(preorder);
    }

    private List<Integer> result = new LinkedList<>();

    /**
     * 递归法
     *
     * @param root 根节点
     * @return 前序遍历结果
     */
    public List<Integer> preorder(NAryNode root) {
        if (root != null) {
            result.add(root.val);
            if (root.children != null) {
                for (NAryNode child : root.children) {
                    preorder(child);
                }
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
    public List<Integer> preorder2(NAryNode root) {

        Stack<NAryNode> stack = new Stack<>();

        if (root == null) {
            return result;
        }

        stack.add(root);
        // 只要栈不为空
        while (!stack.isEmpty()) {
            NAryNode node = stack.pop();
            result.add(node.val);
            // 倒着添加
            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }

        return result;
    }
}

class NAryNode {
    public int val;
    public List<NAryNode> children;


    public NAryNode() {
    }

    public NAryNode(int _val) {
        val = _val;
    }

    public NAryNode(int _val, List<NAryNode> _children) {
        val = _val;
        children = _children;
    }
}