package solve.easy;

/**
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，
 * 判断这个机器人在完成移动后是否在 (0, 0) 处结束。
 * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。
 * <p>
 * 机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，
 * 则返回 true。否则，返回 false。
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。
 * 此外，假设每次移动机器人的移动幅度相同。
 *  
 *
 * @author minwei
 */
public class E657_RobotReturnToOrigin {

    /**
     * 常规法  99.12%
     */
    public boolean judgeCircle(String moves) {
        if (moves == null || (moves.length() & 1) == 1) {
            return false;
        }
        int horizontal = 0;
        int vertical = 0;
        char[] chars = moves.toCharArray();
        for (char ele : chars) {
            switch (ele) {
                case 'L':
                    horizontal--;
                    break;
                case 'R':
                    horizontal++;
                    break;
                case 'U':
                    vertical++;
                    break;
                case 'D':
                    vertical--;
                    break;
                default:
            }
        }
        return horizontal == 0 && vertical == 0;
    }
}
