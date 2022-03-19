package hard;

public class Num2Words {

    /**
     * 宫水三叶 谢谢叶姐
     * 首先，英文好的同学自然知道数字表示是每三位一组进行的，英文不好的可能需要通过样例来找找规律。
     * <p>
     * 由于是每三位一组进行表示，首要考虑实现一个 num2Str 函数，将十进制长度小于等于 333 位的数字表示出来，然后在后面配合 Billion、Million 和 Thousand
     * 即可表示出范围不超过 232−12^{32}-1232−1 的任意数字。
     * <p>
     * 从定义出发 num2Str 需要解决 [0,999][0, 999][0,999] 范围内的所有整数，但由于该函数需要复用到更大的位数来配合 Billion、Million 和 Thousand，
     * 而 Zero Billion 并不是一个合法的描述，因此我们需要将 000 抠出来特判，让 num2Str 对范围在 [1,999][1, 999][1,999] 的数值进行转换。
     * <p>
     * 考虑如何实现 num2Str，假设当前需要转换的数字为 xxx，我们可以对 xxx 的大小进行分情况讨论：
     * <p>
     * x>=100 ：此时首先需要表示成 ??? hundred，表示完后考虑更小的位数；
     * x>=20 ：此时需要表示成 ??? XXX-ty 的形式，表示完后考虑更小的位数；
     * x<20 ：直接描述成具体的单词。
     * <p>
     * 实现完 num2Str 后，剩下的只需要考虑如何将入参 numnumnum 拆分成每三位一组处理即可。
     */

    static String[] num2str_small = {
            "Zero",
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    static String[] num2str_medium = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    static String[] num2str_large = {
            "Billion", "Million", "Thousand", "",
    };

    //表示[0-999]之间的数字
    String num2Str(int x) {
        String res = "";
        if (x >= 100) {
            res += num2str_small[x / 100] + " Hundred ";
            x %= 100;
        }
        if (x >= 20) {
            res += num2str_medium[x / 10] + " ";
            x %= 10;
        }
        if (x > 0) res += num2str_small[x] + " ";
        return res;
    }

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder res = new StringBuilder();
        for (int i = 1000000000, j = 0; i >= 1; i /= 1000, j++) {
            if (num < i) continue;
            ;
            res.append(num2Str(num / i) + num2str_large[j] + " ");
            num %= i;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Num2Words test = new Num2Words();
        System.out.println(test.num2Str(999));
        System.out.println(test.num2Str(888));
        System.out.println(test.num2Str(666));
        System.out.println(test.num2Str(555));

        System.out.println(test.numberToWords(99999));

    }
}
