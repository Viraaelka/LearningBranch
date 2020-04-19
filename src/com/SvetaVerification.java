package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SvetaVerification {

//    public String findStep(String step, Map<String, String> rowToFind, CustomStepDef.FindStrategy strategy)
//        Pattern groupPattern = Pattern.compile("\\{(.*?)}");
//        Matcher matcher = groupPattern.matcher(step);
//        List<String> actualSteps = new ArrayList<>();
//        while (matcher.find()) {
//            actualSteps.add(matcher.group());
//        }
//        StringBuilder expectedStep = new StringBuilder("\\{");
//        if (strategy.equals(EQUALS)) {
//            for (String key : rowToFind.keySet())
//                expectedStep = expectedStep.append("\"").append(key).append("\":\"").append(rowToFind.get(key)).append("\"(.*)");
//
//        } else if (strategy.equals(CONTAINS)) {
//            for (String key : rowToFind.keySet())
//                expectedStep = expectedStep.append("\"").append(key).append("\":\"").append(rowToFind.get(key)).append("(.*)");
//        }
//
//        for (String actualStep : actualSteps) {
//            if (actualStep.matches(expectedStep.toString()))
//                return actualStep;
//        }
//        throw new AutotestError("В транзакции не найден шаг " + expectedStep);
//    }
}
