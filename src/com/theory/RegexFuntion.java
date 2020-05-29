package com.theory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFuntion {
    private static String applyRegexFunction(String inputString) {
        String pattern = "[\\d* [a-zA-Z]*[,*] ]*\\d* [a-zA-Z]*, \\d+ [a-zA-Z]+";
        Pattern patternModel = Pattern.compile(pattern);
        Matcher matcher = patternModel.matcher(inputString);
        String outputString = "";
        if (matcher.find()) {
            System.out.println("Yeeeeeeeeeeeeep, matched");
            outputString = inputString.replaceAll(", ", " and ");
        }
        return outputString;
    }

    public static String abbrevNameBest(String name) {
        return name.toUpperCase().replaceAll("(.).*\\s(.).*", "$1.$2");
    }

    // See "SquareArrays"
    public static String autoMorphicB(int number) {
        String sq = number * number + "";
        return sq.matches(".*" + number) ? "Automorphic" : "Not!!";
    }

    public static void regexExample() {
        String abc = "abc";
        String str = "zzzaaacccbbbzzz";
        System.out.println(Pattern.compile("your regex function").matcher(str).find()); // -> true
    }
}
