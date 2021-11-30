package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String original = "kir kit nick nit tit tit key";

        String string = Stream.of(original).flatMap(word -> Stream.of(word.split(" "))).
                collect(Collectors.teeing(Collectors.filtering(Main::isPalin, Collectors.counting()), Collectors.filtering(word -> !isPalin(word),
                        Collectors.counting()), Pair::new)).Info();

        System.out.println(string);
    }

    static class Pair {
        private final long countPalin;
        private final long nonPalin;


        public Pair(Long countPalin, Long nonPalin) {
            this.countPalin = countPalin;
            this.nonPalin = nonPalin;
        }

        public String Info() {
            return "Палиндромов:" +
                    countPalin +
                    " Не палиндромов:" + nonPalin;
        }
    }


    public static boolean isPalin(@NotNull String word) {
        char[] original = word.toCharArray();
        char[] reversed = new char[original.length];
        int j = 0;
        for (int i = original.length - 1; i >= 0; i--) {
            if (j < original.length) {
                reversed[j] = original[i];
                j++;
            }
        }
        String reverse = String.copyValueOf(reversed);
        return reverse.toLowerCase(Locale.ROOT).equals(word.toLowerCase(Locale.ROOT));

    }

}
