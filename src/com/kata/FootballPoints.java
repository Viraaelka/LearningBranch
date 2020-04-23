package com.kata;

import java.util.Arrays;
import java.util.stream.Stream;

public class FootballPoints {

    // ================================================================================================

    public static int pointsBest1(String[] games) {
        int total = 0;
        for (String s : games) {
            int x = Integer.parseInt(s.split(":", 2)[0], 10);
            int y = Integer.parseInt(s.split(":", 2)[1], 10);
            if (x > y) {
                total += 3;
            }
            if (x == y) {
                total += 1;
            }
        }
        return total;
    }

    // ================================================================================================

    public static int pointsBest2(String[] games) {
        return Stream.of(games)
                .mapToInt(FootballPoints::scoreMatchResult)
                .sum();
    }

    private static int scoreMatchResult(String score) {
        String[] split = score.split(":");
        int comparison = split[0].compareTo(split[1]);
        if (comparison > 0)
            return 3;
        else if (comparison == 0)
            return 1;
        else
            return 0;
    }

    // ================================================================================================

    public static int pointsBest3(String[] games) {
        return Arrays.stream(games)
                .mapToInt(score -> score.charAt(0) - score.charAt(2))
                .map(match -> match > 0 ? 3 : match == 0 ? 1 : 0)
                .sum();
    }

    // ===================================MY SOLUTION===================================================

    public static int pointsMine(String[] games) {
        return Arrays.stream(games).mapToInt(game -> {
            int counter = 0;
            int pointX = Integer.parseInt(game.split(":")[0]);
            int pointY = Integer.parseInt(game.split(":")[1]);
            if (pointX > pointY)
                counter += 3;
            else if (pointX == pointY)
                counter += 1;
            System.out.println(counter);
            return counter;
        }).sum();
    }
}
