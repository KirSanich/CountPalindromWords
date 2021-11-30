package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String original = "kir kit nick nit tit tit lol key";
        Pair pair = TaskPalindrom(original);
        System.out.println(pair.Info());

    }

    static class Pair {
        private final long countPalin;
        private final long countNonPalin;


        public Pair(Long countPalin, Long nonPalin) {
            this.countPalin = countPalin;
            this.countNonPalin = nonPalin;
        }

        public String Info() {
            return "Палиндромов:" +
                    countPalin +
                    " Не палиндромов:" + countNonPalin;
        }
    }

    public static Pair TaskPalindrom(String longString) {
        Pair pair = Stream.of(longString).flatMap(word -> Stream.of(word.split(" "))).
                collect(Collectors.teeing(Collectors.filtering(Main::isPalin, Collectors.counting()), Collectors.filtering(word -> !isPalin(word),
                        Collectors.counting()), Pair::new));
        return pair;
    }

    public static boolean isPalin(@NotNull String word) {
        return word.toLowerCase(Locale.ROOT).equals(new StringBuilder(word).reverse().toString().toLowerCase(Locale.ROOT));

    }

}
