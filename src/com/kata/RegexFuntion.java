package com.kata;

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
}
