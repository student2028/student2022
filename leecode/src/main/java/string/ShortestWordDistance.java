package string;

/***
 * leecode 243 最短单词距离
 * 给定一个字符串数组 wordDict 和两个已经存在于该数组中的不同的字符串 word1 和 word2 。返回列表中这两个单词之间的最短距离。
 *
 * 示例 1:
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * 输出: 1
 *
 * 提示:
 *
 *     1 <= wordsDict.length <= 3 * 104
 *     1 <= wordsDict[i].length <= 10
 *     wordsDict[i] 由小写英文字母组成
 *     word1 和 word2 在 wordsDict 中
 *     word1 != word2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-word-distance
 */
public class ShortestWordDistance {

    public static void main(String[] args) {

    }

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        //思路 遍历一次即可
        int n = wordsDict.length;
        int p1 = -1; //指向word1
        int p2 = -1;//指向word2
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n ; i++) {
            if(word1.equals(wordsDict[i])) {
              p1 = i;
              if(p2 >-1)  res = Math.min(res, Math.abs(p2 - p1));
            }
            if(word2.equals(wordsDict[i])) {
                p2 = i;
                if(p1 >-1)  res = Math.min(res, Math.abs(p2 - p1));
            }
        }
        return res;
    }
}
