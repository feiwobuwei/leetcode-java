package solve.hard;

import solve.middle.M102_BinaryTreeLevelOrderTraversal;
import org.junit.Test;
import prepared.BinaryTree;
import prepared.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化与反序列化
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * 你可以将以下二叉树：
 * <pre>
 *      1
 *     / \
 *    2   3
 *       / \
 *      4   5
 * </pre>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，
 * 你的序列化和反序列化算法应该是无状态的。
 *
 * @author minwei
 * @see solve.middle.M102_BinaryTreeLevelOrderTraversal
 */
public class H297_SerializeAndDeserializeBT {

    private M102_BinaryTreeLevelOrderTraversal tool = new M102_BinaryTreeLevelOrderTraversal();

    @Test
    public void test() {

        int[] nums = {1, 2, 3, -1, -1, 4, 5};
        BinaryTree binaryTree = new BinaryTree(nums, true);

        Codec codec = new Codec();
        String serialize = codec.serialize(binaryTree.getRoot());
        // 1,2,$,$,3,4,$,$,5,$,$,
        System.out.println(serialize);

        // 反序列化
        TreeNode deserialize = codec.deserialize(serialize);

        List<List<Integer>> lists = tool.levelOrder2(deserialize);
        System.out.println(lists);
    }

}

/**
 * 根据前序遍历来重建二叉树。对于NULL节点，用$符号来补。
 * <p>
 * serialize()和deserialize()的时间复杂度均是O(n)，其中n为树中的节点个数。
 * serialize()和deserialize()的空间复杂度均是O(h)，其中h为树的高度。
 */
class Codec {

    // Encodes a tree to a single string. 序列化
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    private void preOrderTraversal(TreeNode root, StringBuilder sb) {
        if (null == root) {
            sb.append("$,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        preOrderTraversal(root.left, sb);
        preOrderTraversal(root.right, sb);
    }

    // Decodes your encoded data to tree. 反序列化
    public TreeNode deserialize(String data) {
        String[] preOrder = data.split(",");
        // 只记录 节点值 和$。 LinkedList 动态数组方便增删
        List<String> list = new LinkedList<>();
        for (int i = 0; i < preOrder.length; i++) {
            if (preOrder[i].length() > 0) {
                list.add(preOrder[i]);
            }
        }
        return deserialize(list);
    }

    private TreeNode deserialize(List<String> list) {
        String string = list.get(0);
        // 每用掉一个元素 就移除掉
        list.remove(0);
        if (!string.equals("$")) {
            TreeNode treeNode = new TreeNode(Integer.valueOf(string));
            treeNode.left = deserialize(list);
            treeNode.right = deserialize(list);
            return treeNode;
        } else {
            return null;
        }
    }
}
