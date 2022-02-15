package string;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * <p>
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 */
public class RestoreIP {

    public static void main(String[] args) {
        RestoreIP restoreIP = new RestoreIP();
        System.out.println(restoreIP.restoreIP("25525511135"));
    }

    List<String> res = new ArrayList<>();

    public List<String> restoreIP(String s) {
        List<String> path = new ArrayList<>();
        backtracking(0, s, path);
        return res;
    }

    public void backtracking(int start, String s, List<String> path) {
        if (path.size() > 4) return;//剪枝
        if (start == s.length() && path.size() == 4) {
            //刚好划分完字符串的情况下满足条件
            res.add(toStr(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (!isValid(str)) continue;
            path.add(str);
            backtracking(i + 1, s, path);
            path.remove(path.size() - 1);

        }
    }

    public String toStr(List<String> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size() - 1; i++) {
            sb.append(path.get(i) + ".");
        }
        sb.append(path.get(path.size() - 1));
        return sb.toString();
    }

    public boolean isValid(String s) {
        if (s.length() == 1) return true;
        if (s.charAt(0) == '0') return false;
        if (s.length() > 3) return false;
        if (Integer.parseInt(s) > 255) return false;
        return true;
    }


}
