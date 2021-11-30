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


    public static boolean isPalin(@NotNull String word) {
        return word.toLowerCase(Locale.ROOT).equals(new StringBuilder(word).reverse().toString().toLowerCase(Locale.ROOT));

    }

}
