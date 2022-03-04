package recursive;


import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    public static void main(String[] args) {
        LetterCombinations test = new LetterCombinations();
        System.out.println(test.letterCombinations("23"));
    }

    String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return res;
        backtrack(digits, 0);
        return res;
    }
    StringBuilder path = new StringBuilder();
    public void backtrack(String digits, int index) {
       if(index == digits.length()){
           res.add(path.toString());
           return;
       }

       String str = numString[digits.charAt(index)- '0'];
        for (int i = 0; i < str.length(); i++) {
            path.append(str.charAt(i));
            backtrack(digits, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}